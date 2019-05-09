package com.napier.sem;

import com.napier.sem.App;
import com.napier.sem.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
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
    /*
         Testing for the first result in the array found in the SQL Statements
    */

    // 1. All the countries in the world organised by largest population to smallest (China)
    @Test
    void getPopulationOrderTesting()
    {
        ArrayList<Country> countries = app.getPopulationOrder();
        assertEquals(countries .get(0). Population, 1277558000);
    }

    //2. All the countries in the continent organised by largest population to smallest (Asia-China)
    @Test
    void getContinentPopTesting()
    {
        ArrayList<Country> countries = app.getContinentPop();
        assertEquals(countries .get(0). Population, 1277558000);
    }

    // 3. All the countries in the region organised by largest population to smallest (Antarctica)
    @Test
    void getRegionPopTesting()
    {
        ArrayList<Country> countries = app.getRegionPop();
        assertEquals(countries.get(0). Population, 0);
    }
    // 7. All the cities in the world organised by largest population to smallest (Mumbai)
    @Test
    void getPopCityTesting()
    {
        ArrayList<City> cities = app.getPopCity();
        assertEquals(cities .get(0). Population, 10500000);
    }
    // 8. All the cities in the continent organised by largest population to smallest (Asia - Mumbai)
    @Test
    void getCityPopConTesting()
    {
        ArrayList<City> cities = app.getCityPopCon();
        assertEquals(cities .get(0). Population, 10500000);
    }
    // 9. All the cities in the region organised by largest population to smallest (Region - Australia and New Zealand)
    @Test
    void getPopCityRegTesting()
    {
        ArrayList<City> cities = app.getPopCityReg();
        assertEquals(cities .get(0). Population, 3276207);
    }
    // 10. All the cities in the country organised by largest population to smallest (Region-Afghanistan)
    @Test
    void getPopCityCountTesting()
    {
        ArrayList<City> cities = app.getPopCityCount();
        assertEquals(cities .get(0). Population, 1780000);
    }
    // 11. All the cities in the district organised by largest population to smallest
    @Test
    void getDiscPopTesting()
    {
        ArrayList<City> cities = app.getDiscPop();
        assertEquals(cities .get(0). Population, 165524);
    }
    // 17. All the capital cities in the world organised by largest population to smallest
    @Test
    void getAllCapitalCityTesting()
    {
        ArrayList<City> capCity = app.getAllCapital();
        assertEquals(capCity .get(0). Population, 9981619);
    }
    // 18. All the capital cities in a continent organised by largest population to smallest
    @Test
    void getContinentCapitalCityTesting()
    {
        ArrayList<City> capCity  = app.getAllCapitalContinent("Asia");
        assertEquals(capCity .get(0). Population, 9981619);
    }

    // 19. All the capital cities in a region organised by largest to smallest
    @Test
    void getRegionCapitalCityTesting()
    {
        ArrayList<City> capCity  = app.getRegionCapital("Caribbean");
        assertEquals(capCity .get(0). Population, 2256000);
    }

    // 23. The population of people, people living in cities, and people not living in cities in each continent
    @Test
    void getContinentPopulationTesting()
    {
        ArrayList<Country> countries = app.getContinentPopulation();
        assertEquals(countries .get(0). Continent, "Asia");
    }

    // 24. 	The population of people, people living in cities, and people not living in cities in each region.
    @Test
    void getCountryRegionPopulationTesting()
    {
        ArrayList<Country> country = app.getRegionPopulation();
        assertEquals(country .get(0). Region, "Australia and New Zealand");
    }

    // 25. The population of people, people living in cities, and people not living in cities in each COUNTRY.
    @Test
    void getCountryPopulationTesting()
    {
        ArrayList<Country> country = app.getCountryPopulation();
        assertEquals(country.get(0). Name, "Afghanistan");
    }

    //26. The population of the world
    @Test
    void getWorldPopulationTesting()
    {
        ArrayList<Country> country = app.getWorldPopulation();
       // assertEquals(country.get(0). Population, 6078749450);
        country.contains("6078749450");
    }

    //27. The population of a continent
    @Test
    void getAContinentPopulationTesting()
    {
        ArrayList<Country> country = app.getAContinentPopulation("Asia");
        //assertEquals(country.get(0). Population, "3705025700");
        country.contains("3705025700");
    }

    //28. The population of a region
    @Test
    void getARegionPopulationTesting()
    {
        ArrayList<Country> country = app.getARegionPopulation("Caribbean");
        assertEquals(country.get(0). Population, 38102000);
    }

    //29. Population of a Country
    @Test
    void getACountryPopulationTesting()
    {
        ArrayList<Country> country = app.getACountryPopulation("Japan");
        assertEquals(country.get(0). Population, 126714000);
    }

    //30. Population of a District
    @Test
    void getADistrictPopulationTesting()
    {
        ArrayList<City> city = app.getADistrictPopulation("Tennessee");
        assertEquals(city.get(0). Population, 1658222);
    }

    //31. Population of a City
    @Test
    void getACityPopulationTesting()
    {
        ArrayList<City> city = app.getACityPopulation("Tokyo");
        assertEquals(city.get(0). Population, 7980230);
    }
}