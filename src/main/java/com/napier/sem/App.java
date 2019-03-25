package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;


public class App
{

    public static void main(String[] args)
    {
        //create new application
        App a = new App();

        //Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:33060");
        }
        else
        {
            a.connect(args[0]);
        }
        a.getPopulationOrder();
        a.getContinentPop();
        a.getPopCity();
        a.getCityPopCon();
        a.getPopCityReg();
        a.getPopCityCount();
        a.getDiscPop();
        a.getAllCapital();
        a.getAllCapitalContinent("Asia");
        a.getRegionCapital("Caribbean");
        a.getCountryPopulation();
        a.getCountryRegionPopulation();
        a.getContinentPopulation();

        a.disconnect();

    }


    public ArrayList<Country> getPopulationOrder() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT country.Name, country.Population"
                            + " FROM country"
                            + " ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> PopOrder = new ArrayList<>();
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getInt("Population");

                System.out.println(cnt.Name + " " + cnt.Population);
                PopOrder.add(cnt);
            }
            return PopOrder;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }



    public ArrayList<Country> getContinentPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT country.Continent, country.Name, SUM(country.Population)"
                            + " FROM country"
                            + " GROUP BY country.Continent, country.Name"
                            + " ORDER BY county.Continent, SUM(country.Population) DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> ContPop = new ArrayList<>();
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Name = rset.getString("CountryName");
                cnt.Population = rset.getInt("CountryPopulation");
                cnt.Continent = rset.getString("CountryContinent");
                System.out.println(cnt.Continent + " " + cnt.Name + " " + cnt.Population);
                ContPop.add(cnt);
            }
            return ContPop;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }



    public ArrayList<Country> getRegionPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT country.Region, country.Name, SUM(country.Population)"
                            +" FROM country"
                            +" GROUP BY country.Region, country.Name"
                            +" ORDER BY country.Region, SUM(country.Population) DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> RegPop = new ArrayList<>();
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Name = rset.getString("CountryName");
                cnt.Region = rset.getString("CountryRegion");
                cnt.Population = rset.getInt("CountryPopulation");
                System.out.println(cnt.Region + " " + cnt.Name + " " + cnt.Population);
                RegPop.add(cnt);
            }
            return RegPop;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }



    public ArrayList<City> getPopCity() {
    try {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        // ALl the capital cities in the WORLD organised by largest population to smallest
        String strSelect =
                "SELECT city.Name, city.Population"
                        +" FROM city"
                        +" ORDER BY city.Population DESC";
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);
        // Return new capital city if valid.
        // Check one is returned
        ArrayList<City> CtyPop = new ArrayList<>();
        while (rset.next()) {
            // Create new city (to store in database)
            City cty = new City();
            cty.Name = rset.getString("cityName");
            cty.Population = rset.getInt("CityPopulation");
            System.out.println(cty.Name + " " + cty.Population);
            CtyPop.add(cty);
        }
        return CtyPop;
    } catch (Exception e) {
        // Capital City not found.
        System.out.println(e.getMessage());
        System.out.println("Failed to get city details");
        return null;
    }
}


    public ArrayList<City> getCityPopCon() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //All the cities in a continent organised by largest population to smallest
            String strSelect =
                    "SELECT country.Continent, city.Name, city.Population"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode=Country.Code"
                            +" ORDER BY country.Continent, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> CityPopCon = new ArrayList<>();
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("CityName");
                cty.Population = rset.getInt("CityPopulation");

                Country cnt = new Country();
                cnt.Continent = rset.getString("CountryContinent");
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


    public ArrayList<City> getPopCityReg() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //All the cities in a region organised by largest population to smallest
            String strSelect =
                    "SELECT country.Region, city.Name, city.Population"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode=Country.Code"
                            +" ORDER BY country.Region, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> PopCityReg = new ArrayList<>();
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("CityName");
                cty.Population = rset.getInt("CityPopulation");

                Country cnt = new Country();
                cnt.Region = rset.getString("CountryRegion");
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



    public ArrayList<City> getPopCityCount() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //All the cities in a country organised by largest population to smallest
            String strSelect =
                    "SELECT country.Name, city.Name, city.Population"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode=Country.Code"
                            +" ORDER BY country.Continent, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> PopCityCount = new ArrayList<>();
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("CityName");
                cty.Population = rset.getInt("CityPopulation");
                Country cnt = new Country();
                cnt.Name = rset.getString("CountryName");
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



    public ArrayList<City> getDiscPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.District, city.Name, city.Population"
                            +" FROM city"
                            +" ORDER BY city.District, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> DiscPop = new ArrayList<>();
            while (rset.next()) {
                // Create new city (to store in database)
                City cty = new City();
                cty.Name = rset.getString("cityName");
                cty.Population = rset.getInt("CityPopulation");
                cty.District = rset.getString("CityDistrict");
                System.out.println(cty.District + " " + cty.Name + " " + cty.Population);
                DiscPop.add(cty);
            }
            return DiscPop;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }
    //Ems Ams



    public ArrayList<City> getAllCapital() {
        try {
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
            while (rset.next()) {
                // Create new City (to store in database)
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                // cCty.CountryCode = rset.getString("CountryCode");
                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                System.out.println(cCty.Name + " " + cCty.Population + " " + cCountry.Name);
                capCity.add(cCty);
            }
            return capCity;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    // Get all the capitals and populations within a continent
    public ArrayList<City> getAllCapitalContinent(String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, country.Name AS 'CountryName', city.Population "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID AND country.Continent = " + "'" + continent +"'"
                            + " ORDER BY city.Population DESC";
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
                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                //cCountry.Continent = rset.getString("continent");
                System.out.println(cCty.Name +  " " + cCty.Population + " " + cCountry.Name);
                capCity.add(cCty);
            }
            return capCity;
        }
        catch (Exception e)
        {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details by continent");
            return null;
        }
    }

    // Get Capital Cities by Region
    public ArrayList<City> getRegionCapital(String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, country.Name AS 'CountryName', city.Population "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID AND country.Region = " + "'" + region +"'"
                            + " ORDER BY city.Population DESC";
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
            System.out.println("Failed to get city details by region");
            return null;
        }

    }

    //population of people in each REGION
    public ArrayList<Country> getCountryRegionPopulation()
    {
        try
        {
            Statement stmt = con.createStatement();

             String strSelect =
                " SELECT  DISTINCT(country.Region) AS Region, SUM(country.Population) AS Population "
                        + " FROM country" +
                        " GROUP BY Region";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countryRegionPop = new ArrayList<Country>();
            while (rset.next())
            {
                Country cnt = new Country();

                cnt.Population = (int) rset.getLong("Population");
                cnt.Region = rset.getString("Region");

                System.out.println(cnt.Population+ " " + cnt.Region);

                countryRegionPop.add(cnt);
            }

            return countryRegionPop;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("No region populations in array");
            return null;
        }
    }


    public ArrayList<Country> getCountryPopulation() {
        try {
            Statement stmt = con.createStatement();

            //population of people in each COUNTRY
            String strSelect =
                    " SELECT  DISTINCT(country.Name) AS Name, SUM(country.Population) AS Population "
                            + " FROM country" +
                            " GROUP BY Name";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countryPopulation = new ArrayList<Country>();
            while (rset.next()) {
                Country cnt = new Country();

                cnt.Population = (int) rset.getLong("Population");
                cnt.Name = rset.getString("Name");


                System.out.println(cnt.Population + " " + cnt.Name);

                countryPopulation.add(cnt);
            }

            return countryPopulation;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No country populations in array");

            return null;
        }
    }
        public ArrayList<Country> getContinentPopulation()
        {
            try
            {
                Statement stmt = con.createStatement();

                //population of people in each CONTINENT
           String strSelect =
                   " SELECT DISTINCT(country.Continent) AS Continent, SUM(country.Population) AS Population "
                + " FROM country" +
                           " GROUP BY Continent";

                ResultSet rset = stmt.executeQuery(strSelect);

                ArrayList<Country> continentRegionPop = new ArrayList<Country>();
                while (rset.next())
                {
                    Country cnt = new Country();

                    cnt.Population = (int) rset.getLong("Population");
                    cnt.Name = rset.getString("Region");


                    System.out.println(cnt.Population + " " + cnt.Region);

                    continentRegionPop.add(cnt);
                }

                return continentRegionPop;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("No continent populations in the array");

                return null;
            }
        }


    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     * @param s
     */
    public void connect(String s) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + s + "/world?allowPublicKeyRetrieval=true&useSSL=false","root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("Successfully Disconnected");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


}




// All the countries in a region organised by largest population to smallest.
            /*String strSelect =
                    "SELECT country.Continent, country.Name, SUM(country.Population)"
                            + " FROM country"
                            + " GROUP BY country.Continent, country.Name"
                            + " ORDER BY county.Continent, SUM(country.Population) DESC"; */

// All the cities in the world organised by largest population to smallest.
            /*String strSelect =
                    "SELECT city.Name, city.Population"
                            +"FROM city"
                            +"ORDER BY city.Population DESC" //sql 7.*/

//All the cities in a continent organised by largest population to smallest.
            /*String strSelect =
                    "SELECT country.Continent, city.Name, city.Population"
                            +"FROM city"
                            +"INNER JOIN country ON city.CountryCode=Country.Code"
                            +"ORDER BY country.Continent, city.Population DESC" //sql 8.*/

//All the cities in a region organised by largest population to smallest.
            /*String strSelect =
                    "SELECT country.Region, city.Name, city.Population"
                            +"FROM city"
                            +"INNER JOIN country ON city.CountryCode=Country.Code"
                            +"ORDER BY country.Region, city.Population DESC" //sql 9.*/

//All the cities in a country organised by largest population to smallest.
            /*String strSelect =
                    "SELECT country.Name, city.Name, city.Population"
                            +"FROM city"
                            +"INNER JOIN country ON city.CountryCode=Country.Code"
                            +"ORDER BY country.Continent, city.Population DESC" sql 10 */

//All the cities in a district organised by largest population to smallest.
            /*String strSelect =
                    "SELECT city.District, city.Name, city.Population"
                            +"FROM city"
                            +"ORDER BY city.District, city.Population DESC" sql 11 */

