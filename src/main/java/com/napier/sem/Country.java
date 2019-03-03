package com.napier.sem;

import java.sql.*;

/**
 * Represents an country
 */

// code, name, continent, region, population, capital

//sql2
////SELECT COUNTRY.Continent, SUM(Population)
////FROM COUNTRY
////GROUP BY COUNTRY.Continent
////ORDER BY SUM(Population) DESC

//sql3
//SELECT COUNTRY.Region, SUM(Population)
//FROM COUNTRY
//GROUP BY COUNTRY.Region
//ORDER BY SUM(Population) DESC

//sql4 not completed
//SELECT Name, Population
//FROM COUNTRY
//ORDER BY Population DESC
//LIMIT 5 &





//sql7
//SELECT CITY.Name, CITY.Population
//FROM CITY
//ORDER BY CITY.Population DESC

//sql8 not checked
//SELECT  COUNTRY.Continent, CITY.Name, CITY.Population
//FROM CITY
//INNER JOIN COUNTRY ON CITY.CountryCode=COUNTRY.Code
//ORDER BY COUNTRY.Continent, CITY.Population DESC
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

    // 1st statement
    public Country getCountry(String Name, int Population) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Population"
                            + "FROM country"
                            + "ORDER BY country.Population DESC"

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Country cnt = new Country();
                cnt.Population = rset.getString("Population");
                cnt.Name = rset.getString("Name");

                //System.out.println(cnt.Name + " : " + cnt.Population);

                return cnt;
            } else
                return null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void displayCountry(Country cnt) {
        if (cnt != null) {
            System.out.println(cnt.Name + "\n " + cnt.Population);
        }
    }


    //2nd statement
    public Country getCountry(char Continent, int Population, String Name) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT country.Continent, country.Name, SUM(country.Population)"
                            + "FROM country"
                            + "GROUP BY country.Continent, country.Name"
                            + "ORDER BY county.Continent, SUM(country.Population) DESC"

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Country cnt = new Country();
                cnt.Continent = rset.getString("Continent");
                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getString("Population");

                //System.out.println(cnt.Continent + " : " + cnt.Name + " : " + cnt.Population);

                return cnt;
            } else
                return null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void displayCountry(Country cnt) {
        if (cnt != null) {
            System.out.println(cnt.Continent + "\n " + cnt.Name + "\n" + cnt.Population);
        }
    }


//3rd sql


    public Country getCountry(String Region, int Population, String Name) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT country.Region, country.Name, SUM(country.Population)"
                            + "FROM country"
                            + "GROUP BY country.Region, country.Name"
                            + "ORDER BY county.Region, SUM(country.Population) DESC"

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Country cnt = new Country();
                cnt.Region = rset.getString("Region");
                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getString("Population");

                //System.out.println(cnt.Region + " : " + cnt.Name + " : " + cnt.Population);

                return cnt;
            } else
                return null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }


// 4th sql (incomplete)
/*     public Country getCountry(String Region, int Population, String Name)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT country.Name, country.Population"
                            +"FROM country"
                            +"ORDER BY country.Population DESC"
                            +"LIMIT 5"

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country cnt = new Country();
                cnt.Region = rset.getString("Region");
                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getString("Population");

                //System.out.println(cnt.Region + " : " + cnt.Name + " : " + cnt.Population);

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

*/

}