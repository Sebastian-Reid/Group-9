package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        App a = new App();

        a.connect();
        a.getPopCityReg();
        a.getPopCityCount();
        a.getCityPopCon();
        a.getRegionPop();
        a.getContinentPop();
        a.disconnect();
    }

    // 2. All the countries in a CONTINENT organised by largest population to smallest.
    public ArrayList<Country> getContinentPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT SUM(country.Population) AS Population, country.Continent, country.Name"
                            + " FROM country"
                            + " GROUP BY Continent, Name"
                            + " ORDER BY country.Continent, SUM(country.Population) DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> Country = new ArrayList<>();
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();

                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getInt("Population");
                cnt.Continent = rset.getString("Continent");

                System.out.println(cnt.Continent + " " + cnt.Name + " " + cnt.Population);
                Country.add(cnt);
            }
            return Country;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    // 3. All the countries in a REGION organised by largest population to smallest.
    public ArrayList<Country> getRegionPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT SUM(country.Population) AS Population, country.Region, country.Name"
                            +" FROM country"
                            +" GROUP BY country.Region, country.Name"
                            +" ORDER BY country.Region, SUM(country.Population) DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> Country = new ArrayList<>();
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Name = rset.getString("Name");
                cnt.Region = rset.getString("Region");
                cnt.Population = rset.getInt("Population");
                System.out.println(cnt.Region + " " + cnt.Name + " " + cnt.Population);
                Country.add(cnt);
            }
            return Country;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    // 8. All the cities in a CONTINENT organised by largest population to smallest.
    public ArrayList<City> getCityPopCon() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //All the cities in a continent organised by largest population to smallest
            String strSelect =
                    "SELECT country.Continent, city.Name, city.Population"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode = country.Code"
                            +" ORDER BY country.Continent, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> CityPopCon = new ArrayList<>();
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");

                Country cnt = new Country();
                cnt.Continent = rset.getString("Continent");
                System.out.println(cnt.Continent + " " + cty.Name + " " + cty.Population);
                CityPopCon.add(cty);
            }
            return CityPopCon;
        } catch (Exception e) {
            // City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }


    // 9. All the cities in a REGION organised by largest population to smallest.
    public ArrayList<City> getPopCityReg() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //All the cities in a region organised by largest population to smallest
            String strSelect =
                    "SELECT country.Region, city.Name, city.Population"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode = country.Code"
                            +" ORDER BY country.Region, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> PopCityReg = new ArrayList<>();
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");

                Country cnt = new Country();
                cnt.Region = rset.getString("Region");
                System.out.println(cnt.Region + " " + cty.Name + " " + cty.Population);
                PopCityReg.add(cty);
            }
            return PopCityReg;
        } catch (Exception e) {
            // City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    // 10. All the cities in a COUNTRY organised by largest population to smallest.
    public ArrayList<City> getPopCityCount() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //All the cities in a country organised by largest population to smallest
            String strSelect =
                    "SELECT country.Name, city.Name AS cCity, city.Population"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode = country.Code"
                            +" ORDER BY country.Continent, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> PopCityCount = new ArrayList<>();
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("cCity");
                cty.Population = rset.getInt("Population");
                Country cnt = new Country();
                cnt.Name = rset.getString("Name");
                System.out.println(cnt.Name + " " + cty.Name + " " + cty.Population);
                PopCityCount.add(cty);
            }
            return PopCityCount;
        } catch (Exception e) {
            // City not found.
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
