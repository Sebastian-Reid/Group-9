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

    // 17. Test for Seoul
    @Test
    void getAllCapitalCityTesting()
    {
        ArrayList<City> countries = app.getAllCapital();
        assertEquals(countries.get(0). Population, 9981619);
    }

    // 23. Test for Aruba
    @Test
    void getCountryPopulationTesting()
    {
        ArrayList<Country> countries = app.getCountryPopulation();
        assertEquals(countries.get(0).Population, 170115000 );

    }
}