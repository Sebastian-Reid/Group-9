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
    void getPopulationOrder() {
        app.getPopulationOrder();
    }

    @Test
    void getContinentPop() {
        app.getContinentPop();
    }

    @Test
    void getRegionPop() {
        app.getRegionPop();
    }

 /*   @Test
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
    }*/

    @Test
    void getPopCity() {
        app.getPopCity();
    }

    @Test
    void getCityPopCon() {
        app.getCityPopCon();
    }

    @Test
    void getPopCityReg() {
        app.getPopCityReg();
    }

    @Test
    void getPopCityCount()
    {
        app.getPopCityCount();
    }

    @Test
    void getDiscPop()
    {
        app.getDiscPop();
    }

  /*  @Test
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
    }*/

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
        app.getCountryRegionPopulation();
    }

    @Test

    void getCountryContinentPopTest()
    {
        app.getContinentPopulation();
    }


}
