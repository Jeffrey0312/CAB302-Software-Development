package tradingPlatform;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class NetworkDataSource implements TradingPlatformDataSource{
    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 10000;

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;



    public NetworkDataSource() {
        try {
            // Persist a single connection through the whole lifetime of the application.
            // We will re-use this same connection/socket, rather than repeatedly opening
            // and closing connections.
            socket = new Socket(HOSTNAME, PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // If the server connection fails, we're going to throw exceptions
            // whenever the application actually tries to query anything.
            // But it wasn't written to handle this, so make sure your
            // server is running beforehand!
            System.out.println("Failed to connect to server");
        }
    }

    @Override
    public OrganisationalUnit getOrganisation(String name) {
        return new OrganisationalUnit();
    }

    @Override
    public void addOrganisation(String name) {

    }

    @Override
    public void deleteOrganisation(String name) {

    }

    @Override
    public Set<String> getOrganisationsList() {
        //Set<String> orgs = new TreeSet<>();
        return new TreeSet<>();
    }

    @Override
    public void setOrganisationCredits(String name, int credits) {

    }

    @Override
    public void setOrganisationAssetAmount(String organisation, String asset, int amount) {

    }
}
