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

    @Test
    void getAllCapital()
    {
        app.getAllCapital();
    }

    @Test
    void getAllCapitalContinent()
    {
        app.getAllCapitalContinent(null);
    }

    @Test
    void getRegionCapital()
    {
        app.getRegionCapital(null);
    }
    @Test

    void getCountryTestNull()
    {
        app.getCountryPopulation();
    }

    @Test

    void getCountryRegionPopTest()
    {
        app.getCountryPopulation();
    }


}
