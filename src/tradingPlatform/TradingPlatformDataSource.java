package tradingPlatform;

import java.util.Set;

/**
 * Provides functionality needed by any data source for the Trading Platform
 * application.
 */
public interface TradingPlatformDataSource {

    /**
     *
     */
    void getOrganisation(String name);

    /**
     *
     */
    void addOrganisation(OrganisationalUnit o);

    /**
     *
     */
    void getOrganisation(OrganisationalUnit o);

    /**
     *
     */
    void deleteOrganisation(OrganisationalUnit o);
}
