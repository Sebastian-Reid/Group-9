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

        app.connect("localhost:35.234.140.108");
    }


    @Test
    void getCountryPopulationTesting()
    {
        ArrayList<Country> countries = app.getCountryPopulation();
        assertEquals(countries.get(0).Population, 103000 );

    }

    /*
    @Test
    void getCapitalCity()
    {
        ArrayList<Country> countries = app.getAllCapital();
        assertEquals(countries.get(0). Population, 103000);
    }
    */
}