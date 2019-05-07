package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()

    {
        app = new App();
    }

    // 1. All the countries in the world organised by largest population to smallest
    @Test
    void getPopulationOrder() {
        app.getPopulationOrder();
    }
    /*
    // 2. All the countries in the continent organised by largest population to smallest
    @Test
    void getContinentPop() {
        app.getContinentPop();
    }

    // 3. All the countries in the region organised by largest population to smallest
    @Test
    void getRegionPop() {
        app.getRegionPop();
    }

   @Test
    void getNWorldPop() {
        app.getNWorldPop();
    }
    @Test
    void getNContinentPop() {
        app.getNContinentPop();
    }
    @Test
    void getNRegionPop() {
        app.getNRegionPop();
    }
    */

    // 7. All the cities in the world organised by largest population to smallest
    @Test
    void getPopCity() {
        app.getPopCity();
    }

    // 8. All the cities in the continent organised by largest population to smallest
    @Test
    void getCityPopCon() {
        app.getCityPopCon();
    }

    // 9. All the cities in the region organised by largest population to smallest
    @Test
    void getPopCityReg() {
        app.getPopCityReg();
    }

    // 10. All the cities in the country organised by largest population to smallest
    @Test
    void getPopCityCount()
    {
        app.getPopCityCount();
    }

    // 11. All the cities in the district organised by largest population to smallest
    @Test
    void getDiscPop()
    {
        app.getDiscPop();
    }
    /*
    @Test
    void getNPopCity() {
        app.getNPopCity();
    }
    @Test
    void getNPopCityCon() {
        app.getNPopCityCon;
    }
    @Test
    void getNPopCityReg() {
        app.getNPopCityReg();
    }
    */
    // 17. All the capital cities in the world organised by largest population to smallest
    @Test
    void getAllCapital()
    {
        app.getAllCapital();
    }


    // 18. All the capital cities in a continent organised by largest population to smallest
    @Test
    void getAllCapitalContinent()
    {
        app.getAllCapitalContinent(null);
    }

    // 19. All the capital cities in a region organised by largest to smallest
    @Test
    void getRegionCapital()
    {
        app.getRegionCapital(null);
    }

    // 23. The population of people, people living in cities, and people not living in cities in each continent
    @Test
    void getContinentPopulation()
    {
        app.getContinentPopulation();
    }

    @Test
    void getCountryRegionPopulation()
    {
        app.getCountryRegionPopulation();
    }

    @Test
    void getCountryRegionPopTest()
    {
        app.getCountryPopulation();
    }

}
