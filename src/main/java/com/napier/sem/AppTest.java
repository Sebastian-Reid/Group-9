package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;
    Country cnt = new Country();

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void getCountryNull()
    {
        app.getCountry();
    }

    public void getCountry(ArrayList<Country> country)
    {
        // Check employees is not null
        if (country == null)
        {
            System.out.println("No country");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Country", "Population", "Region"));
        // Loop over all employees in the list
        for (Country cnt : country)
        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            cnt.Name, cnt.Population, cnt.Region);
            System.out.println(emp_string);
        }
    }
}