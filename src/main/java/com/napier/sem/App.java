package com.napier.sem;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {

        // Create new Application
        App a = new App();
        Country c = new Country();
        // Connect to database
        a.connect();

        a.getAllCapital();

        c.displayCountry();

        // Disconnect from database
        a.disconnect();
    }

    /*
    public ArrayList<City> getAllCapital()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, country.name AS 'CountryName', city.Population "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID "
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> capCity = new ArrayList<City>();
            while (rset.next())
            {
                // Create new City (to store in database)
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                // cCty.CountryCode = rset.getString("CountryCode");

                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                System.out.println(cCty.Name +  " " + cCty.Population + " " + cCountry.Name);

                capCity.add(cCty);
            }
            return capCity;
        }
        catch (Exception e)
        {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }

    }
    */

    // Get all the capitals and populations within a continent
    public ArrayList<City> getAllCapital()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, country.name AS 'CountryName', DISTINCT(country.Continent) AS 'continent', city.Population "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID  "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> capCity = new ArrayList<City>();
            while (rset.next())
            {
                // Create new City (to store in database)
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                // cCty.CountryCode = rset.getString("CountryCode");

                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                cCountry.Continent = rset.getString("continent");
                System.out.println(cCty.Name +  " " + cCty.Population + " " + cCountry.Name);

                capCity.add(cCty);
            }
            return capCity;
        }
        catch (Exception e)
        {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
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

