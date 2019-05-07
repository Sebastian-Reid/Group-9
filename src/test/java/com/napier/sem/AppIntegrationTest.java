package com.napier.sem;

import com.napier.sem.App;
import com.napier.sem.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();

        app.connect("localhost:33060");
    }

    // 1. Test for China
    @Test
    void getPopulationOrderTesting()
    {
        ArrayList<Country> countries = app.getPopulationOrder();
        assertEquals(countries .get(0). Population, 1277558000);
    }
    /*
    //2. Test for Continent (Asia - China)
    @Test
    void getContinentPopTesting()
    {
        ArrayList<Country> countries = app.getContinentPop();
        assertEquals(countries .get(0). Population, 1277558000);
    }

    // 3. Test for region
    @Test
    void getRegionPopTesting()
    {
        ArrayList<Country> countries = app.getRegionPop();
        assertEquals(countries.get(0). Population, actual 1277558000);
    }
    // 7. Test for World (Mumbai)
    @Test
    void getPopCityTesting()
    {
        ArrayList<City> cities = app.getPopCity();
        assertEquals(cities .get(0). Population, 10500000);
    }

    // 8. Test for Continent (Mumbai)
    @Test
    void getCityPopConTesting()
    {
        ArrayList<City> cities = app.getCityPopCon();
        assertEquals(cities .get(0). Population, 10500000);
    }
    // 9. Test for Region
    @Test
    void getPopCityRegTesting()
    {
        ArrayList<City> cities = app.getPopCityReg();
        assertEquals(cities .get(0). Population, 10500000);
    }
    // 10.
    */
    // 17. Test for Seoul
    @Test
    void getAllCapitalCityTesting()
    {
        ArrayList<City> capCity = app.getAllCapital();
        assertEquals(capCity .get(0). Population, 9981619);
    }
    // 18. Test for Asia (result: Seoul)
    @Test
    void getContinentCapitalCityTesting()
    {
        ArrayList<City> capCity  = app.getAllCapitalContinent("Asia");
        assertEquals(capCity .get(0). Population, 9981619);
    }

    // 19. Test for Caribbean (Result: ... )
    @Test
    void getRegionCapitalCityTesting()
    {
        ArrayList<City> capCity  = app.getRegionCapital("Caribbean");
        assertEquals(capCity .get(0). Population, 2256000);
    }

    // 23. Test for Asia
    @Test
    void getContinentPopulationTesting()
    {
        ArrayList<Country> countries = app.getContinentPopulation();
        assertEquals(countries .get(0). Continent, "Asia");
    }

    // 24. Test for ...
    @Test
    void getCountryRegionPopulationTesting()
    {
        ArrayList<Country> country = app.getCountryRegionPopulation();
        assertEquals(country .get(0). Region, "Asia");
    }
    /*
    // 25. Test for ...
    @Test
    void getCountryPopulationTesting()
    {
        ArrayList<Country> country = app.getCountryPopulation();
        assertEquals(country.get(0).Name, "Asia" );
    }
    */
}