package com.napier.sem;

import java.sql.*;

/**
 * Represents an country
 */

// code, name, continent, region, population, capital

public class Country {

    public String Code;
    public String Name;
    public char Continent;
    public String Region;
    public float SurfaceArea;
    public int IndepYear;
    public int Population;
    public float LifeExpectancy;
    public float GNP;
    public float GNPOld;
    public char LocalName;
    public char GovernmentForm;
    public char HeadOfState;
    public int Capital;
    public char Code2;
    private Connection con;



    public void displayCountry()
    {
        //if (cnt != null)
        {
            System.out.println(Name);
        }
    }

}



