package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        App a = new App();

        a.connect();
        a.getCountry();
        a.disconnect();
    }

    public ArrayList<Country> getCountry()
    {
        try
        {
            Statement stmt = con.createStatement();

        /*
            The population of people, people living in cities, and people not living in cities in each continent.
            The population of people, people living in cities, and people not living in cities in each region.
            The population of people, people living in cities, and people not living in cities in each country.
          */

            /* String strSelect =
             " SELECT DISTINCT(country.Continent) AS Continent, country.Name AS cntName, SUM(country.Population) AS Population, SUM(city.Population) AS cPopulation, city.Name AS cName" +
                        " FROM country JOIN city ON country.Code = city.CountryCode" +
                        " WHERE country.Code = city.CountryCode"+
                        " GROUP BY Continent, cName, cntName ";*/ //population of people in cities in each continent


                  /*String strSelect =
                          " SELECT DISTINCT(country.Region) AS cRegion, SUM(DISTINCT country.Population) AS coPopulation, SUM(DISTINCT city.Population) AS cPopulation" +
                          " FROM country JOIN city ON country.Code = city.CountryCode" +
                          " WHERE country.Code = city.CountryCode" +
                          " GROUP BY cRegion"; //population of people in each continent*/

            String strSelect =
                    " SELECT DISTINCT(country.Name) AS cName, SUM(DISTINCT country.Population) AS coPopulation, SUM(DISTINCT city.Population) AS cPopulation" +
                            " FROM country JOIN city ON country.Code = city.CountryCode" +
                            " WHERE country.Code = city.CountryCode" +
                            " GROUP BY cName";

            /*

            /* String strSelect = " SELECT DISTINCT(country.Continent) AS Continent, SUM(country.Population) AS Population "
                    + " FROM country" +
                    " GROUP BY Continent"; */ //population of people in each continent

            /* String strSelect =
                " SELECT  DISTINCT(country.Region) AS Region, SUM(country.Population) AS Population "
                        + " FROM country" +
                        " GROUP BY Region"; */ //population of people in each region

            /*
            String strSelect =
                    " SELECT country.Region, SUM(city.Population) AS Population, city.Name" +
                            " FROM country JOIN city ON country.Code = city.CountryCode" +
                            " GROUP BY Region, Name "; */ //population of people in each city in each region

            /*
            String strSelect =
                    " SELECT  DISTINCT(country.Name) AS Name, SUM(country.Population) AS Population "
                            + " FROM country" +
                            " GROUP BY Name"; */  //population of people in each country

            /*
            String strSelect =
                    " SELECT country.Name, SUM(city.Population) AS Population, city.Name AS cName" +
                            " FROM country JOIN city ON country.Code = city.CountryCode " +
                            " GROUP BY Name, cName "; */ //population of people in each city in each country


            /*
            String strSelect =
                                " SELECT DISTINCT(country.Continent) AS dContinent, SUM(DISTINCT country.Population) AS coPopulation" +
                                " FROM country " +
                                " GROUP BY dContinent";
                                    // Execute SQL statement */

            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("Region" + " | " + "Population" + " | " + "city %" + " | " + "Non city %");
            ArrayList<Country> country = new ArrayList<Country>();

            while(rset.next())
            {
                Country cnt = new Country();
                cnt.Name = rset.getString("cName");
                //cnt.Continent = rset.getString("dContinent");
                cnt.Population = rset.getLong("coPopulation");
                 //  cnt.Name = rset.getString("cntName");
                //cnt.Region = rset.getString("cRegion");
                City cCity = new City();
                //cCity.Name = rset.getString("cName");
                cCity.Population = rset.getLong("cPopulation");


                System.out.println(cnt.Name + " " + cnt.Population + " " + ((cCity.Population * 100) / (cnt.Population))+"%" + " " + (100 - (cCity.Population * 100) / (cnt.Population)) + "%");


                //cnt.Region = rset.getString("Region");

                 // System.out.println(cnt.Population+ " " + cnt.Continent);
                //System.out.println(cnt.Population+ " " + cnt.Region);
               // System.out.println(cnt.Name + " " + cnt.Population  + " " + cnt.Continent + " " + cCity.Population + " " + cCity.Name);
            //    System.out.println(cnt.Continent + " Total population of continent - " + cnt.Population +
              //         " Percentage of people in cities - " + (((cCity.Population) / (cnt.Population)) * 100) +
               //        " Percentage of people not in cities - " + (cnt.Population - cCity.Population));
               // System.out.println(cnt.Name + " " + cnt.Population + " " + cnt.Region);
               // System.out.println(cCity.cName + " " + cnt.Population + " " + cnt.Name);

                country.add(cnt);


            }

            return country;


        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(50000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                System.out.println("Successfully Disconnected");
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

}

