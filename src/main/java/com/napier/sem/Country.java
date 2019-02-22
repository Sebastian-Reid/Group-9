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


    public Country getCountry(String Name)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Code "
                            + "FROM country "
                            + "WHERE Name = " + Name;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country cnt = new Country();
                cnt.Code = rset.getString("Code");
                cnt.Name = rset.getString("Name");

                //System.out.println(cnt.Code + " : " + cnt.Name);

                return cnt;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void displayCountry(Country cnt)
    {
        if (cnt != null)
        {
            System.out.println(cnt.Code + "\n " + cnt.Name);
        }
    }

}



