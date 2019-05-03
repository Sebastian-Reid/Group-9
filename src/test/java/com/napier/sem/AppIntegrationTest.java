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
    //2. Test for Continent
    @Test
    void getContinentPopTesting()
    {
        ArrayList<Country> countries = app.getContinentPop();
        assertEquals(countries .get(0). Population, 1277558000);
    }
    // 3.
    @Test
    void getRegionPopTesting()
    {
        ArrayList<Country> countries = app.getRegionPop();
        assertEquals(countries.get(0). Population, actual 1277558000);
    }
    */
    // 7. Test for Mumbai
    @Test
    void getPopCityTesting()
    {
        ArrayList<City> cities = app.getPopCity();
        assertEquals(cities.get(0). Population, 10500000);
    }
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

    // 23. Test for Brazil
    @Test
    void getCountryPopulationTesting()
    {
        ArrayList<Country> countries = app.getCountryPopulation();
        assertEquals(countries.get(0).Population, 170115000 );
    }


}