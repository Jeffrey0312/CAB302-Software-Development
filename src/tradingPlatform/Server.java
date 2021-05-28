package tradingPlatform;

import tradingPlatform.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private static final int PORT = 10000;

    /**
     * this is the timeout inbetween accepting clients, not reading from the socket itself.
     */
    private static final int SOCKET_ACCEPT_TIMEOUT = 100;

    /**
     * This timeout is used for the actual clients.
     */
    private static final int SOCKET_READ_TIMEOUT = 5000;

    private AtomicBoolean running = new AtomicBoolean(true);

    /**
     * The connection to the database where everything is stored.
     */
    private TradingPlatformDataSource database;

    private void handleConnection(Socket socket) {
        try {
            /*
             * We create the streams once at connection time, and re-use them until the client disconnects.
             * This **must** be in the opposite order to the client, because creating an ObjectInputStream
             * reads data, and an ObjectOutputStream writes data.
             */
            final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            /*
             * while(true) here might seem a bit confusing - why do we have an infinite loop?
             * That's because we don't want to exit until the client disconnects, and when they do, readObject()
             * will throw an IOException, which will cause this method to exit. Another option could be to have
             * a "close" message/command sent by the client, but if the client closes improperly, or they lose
             * their network connection, it's not going to get sent anyway.
             */
            while (true) {
                try {
                    /*
                     * Read the command, this tells us what to send the client back.
                     * If the client has disconnected, this will throw an exception.
                     */
                    final Command command = (Command) inputStream.readObject();
                    handleCommand(socket, inputStream, outputStream, command);
                } catch (SocketTimeoutException e) {
                    /*
                     * We catch SocketTimeoutExceptions, because that just means the client hasn't sent
                     * any new requests. We don't want to disconnect them otherwise. Another way to
                     * check if they're "still there would be with ping/pong commands.
                     */
                }
            }
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            System.out.printf("Connection %s closed%n", socket);
        }
    }

    /**
     * Handles a request from the client.
     * @param socket socket for the client
     * @param inputStream input stream to read objects from
     * @param outputStream output stream to write objects to
     * @param command command we're handling
     * @throws IOException if the client has disconnected
     * @throws ClassNotFoundException if the client sends an invalid object
     */
    private void handleCommand(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream,
                               Command command) throws IOException, ClassNotFoundException {
        /*
         * Remember this is happening on separate threads for each client. Therefore access to the database
         * must be thread-safe in some way. The easiest way to achieve thread safety is to just put a giant
         * lock around all database operations, in this case with a synchronized block on the database object.
         */
        switch (command){
            case ADD_ORGANISATION:{
                // User is sending the name of a new organisation
                final String orgName = (String) inputStream.readObject();
                synchronized (database){
                    database.addOrganisation(orgName);
                }
                System.out.printf("Added organisation '%s' to database from client %s%n", orgName, socket.toString());
            }
            break;

            case GET_ORGANISATION:{
                // User is sending the name of the organisation they are looking for
                final String orgName = (String) inputStream.readObject();
                synchronized (database){
                    final OrganisationalUnit org = database.getOrganisation(orgName);

                    outputStream.writeObject(org);

                    if(org != null){
                        System.out.printf("Sent organisation '%s' to client %s%n",
                                org.getName(), socket.toString());
                    }
                }
                outputStream.flush();
            }
            break;

            case DELETE_ORGANISATION:{
                final String orgName = (String) inputStream.readObject();
                synchronized (database){
                    database.deleteOrganisation(orgName);
                }

                System.out.printf("Deleted organisation '%s' on behalf of client %s%n",
                        orgName, socket.toString());
            }
            break;

            case GET_ORGANISATIONS_LIST:{
                synchronized (database){
                    outputStream.writeObject(database.getOrganisationsList());
                }
                outputStream.flush();

                System.out.printf("Sent organisation name set to client %s%n", socket.toString());
            }
            break;

            case SET_ORGANISATION_CREDITS:{
                final String orgName = (String) inputStream.readObject();
                final int creditAmount = (int) inputStream.readObject();
                synchronized (database){
                    database.setOrganisationCredits(orgName,creditAmount);
                }
                System.out.printf("Updated the credits of %s to %d on behalf of client %s%n",
                        orgName, creditAmount, socket.toString());
            }
            break;

            case SET_ORGANISATION_ASSET_AMOUNT:{
                final String orgName = (String) inputStream.readObject();
                final String asset = (String) inputStream.readObject();
                final int amount = (int) inputStream.readObject();
                synchronized (database){
                    database.setOrganisationAssetAmount(orgName,asset,amount);
                }
                System.out.printf("Updated the %s of %s to %d on behalf of client %s%n",
                        asset, orgName, amount, socket.toString());
            }
            break;

            case DELETE_ASSET:{
                final String assetName = (String) inputStream.readObject();
                synchronized (database){
                    database.deleteAsset(assetName);
                }
                System.out.printf("Deleted Asset '%s' on behalf of client %s%n", assetName, socket.toString());
            }
            break;

            case ADD_USER:{
                // User is sending the name of a new organisation
                final User userName = (User) inputStream.readObject();
                synchronized (database){
                    database.addUser(userName);
                }
                System.out.printf("Added user '%s' to database from client %s%n", userName, socket.toString());
            }
            break;

            case GET_USER:{
                // User is sending the name of the organisation they are looking for
                final String userName = (String) inputStream.readObject();
                synchronized (database){
                    final User user = database.getUser(userName);

                    outputStream.writeObject(user);

                    if(user != null){
                        System.out.printf("Sent user '%s' to client %s%n",
                                user.getUsername(), socket.toString());
                    }
                }
                outputStream.flush();
            }
            break;

            case DELETE_USER:{
                final String userName = (String) inputStream.readObject();
                synchronized (database){
                    database.deleteUser(userName);
                }

                System.out.printf("Deleted user '%s' on behalf of client %s%n",
                        userName, socket.toString());
            }
            break;

            case GET_USERS_LIST:{
                synchronized (database){
                    outputStream.writeObject(database.getUsersList());
                }
                outputStream.flush();

                System.out.printf("Sent user name set to client %s%n", socket.toString());
            }
            break;

            case LOGIN:{
                // User is sending the name of the organisation they are looking for
                final String username = (String) inputStream.readObject();
                final String password = (String) inputStream.readObject();
                synchronized (database){
                    final User user = database.login(username,password);

                    outputStream.writeObject(user);

                    if(user != null){
                        System.out.printf("Sent User '%s' to client %s%n",
                                user.getUsername(), socket.toString());
                    }
                }
                outputStream.flush();
            }
            break;

            case SET_USER_ORGANISATION:{
                final String username = (String) inputStream.readObject();
                final String orgName = (String) inputStream.readObject();
                synchronized (database){
                    database.setUserOrganisation(username,orgName);
                }
                System.out.printf("Updated the organisation of '%s' to '%s' on behalf of client %s%n",
                        username, orgName, socket.toString());
            }
            break;

            case SET_USER_PASSWORD:{
                final String username = (String) inputStream.readObject();
                final String password = (String) inputStream.readObject();
                synchronized (database){
                    database.setUserPassword(username,password);
                }
                System.out.printf("Updated the password of '%s' on behalf of client %s%n",
                        username, socket.toString());
            }
            break;
        }
    }

    /**
     * Returns the port the server is configured to use
     *
     * @return The port number
     */
    public static int getPort() {
        return PORT;
    }

    /**
     * Starts the server running on the default port
     */
    public void start() throws IOException {
        // Connect to the database.
        database = new JDBCTradingPlatformDataSource();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(SOCKET_ACCEPT_TIMEOUT);
            for (;;) {
                if (!running.get()) {
                    // The server is no longer running
                    break;
                }
                try {
                    final Socket socket = serverSocket.accept();
                    socket.setSoTimeout(SOCKET_READ_TIMEOUT);

                    // We have a new connection from a client. Use a runnable and thread to handle
                    // the client. The lambda here wraps the functional interface (Runnable).
                    final Thread clientThread = new Thread(() -> handleConnection(socket));
                    clientThread.start();
                } catch (SocketTimeoutException ignored) {
                    // Do nothing. A timeout is normal- we just want the socket to
                    // occasionally timeout so we can check if the server is still running
                } catch (Exception e) {
                    // We will report other exceptions by printing the stack trace, but we
                    // will not shut down the server. A exception can happen due to a
                    // client malfunction (or malicious client)
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // If we get an error starting up, show an error dialog then exit
            e.printStackTrace();
            System.exit(1);
        }

        // Close down the server
        System.exit(0);
    }

    /**
     * Requests the server to shut down
     */
    public void shutdown() {
        // Shut the server down
        running.set(false);
    }
}
