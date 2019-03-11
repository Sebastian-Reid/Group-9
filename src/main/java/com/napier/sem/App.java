package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        App a = new App();

        // Connect to database
        a.connect("localhost:33060");

        // ArrayList<Country> country = a.getCountry();
        ArrayList<Country> country = a.getCountry();

        Country cnt = a.getCountry();


        // Print salary report
        a.printSalaries(employees);

        // Disconnect from database
        a.disconnect();

    }

    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/employees?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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
    public ArrayList<City> getCity() {
        try {
            Statement stmt = con.createStatement();



           /* String strSelect =
                   " SELECT DISTINCT(country.Continent) AS Continent, SUM(country.Population) AS Population "
                + " FROM country" +
                           " GROUP BY Continent"; */ //population of people in each continent

            /* String strSelect =
                " SELECT  DISTINCT(country.Region) AS Region, SUM(country.Population) AS Population "
                        + " FROM country" +
                        " GROUP BY Region"; */ //population of people in each region
            /*
            String strSelect =
                    " SELECT  DISTINCT(country.Name) AS Name, SUM(country.Population) AS Population "
                            + " FROM country" +
                            " GROUP BY Name";  //population of people in each country
             */
            /*
            //sql 7 All the cities in the world organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, city.Population"
                            +" FROM city"
                            +" ORDER BY city.Population DESC";
            */
            String strSelect =
                    "SELECT country.Continent, city.Name, city.Population"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode = country.Code"
                            +" ORDER BY country.Continent, city.Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> city = new ArrayList<City>();
            while (rset.next()) {
                City cty = new City();
                Country cnt = new Country();
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");
                cnt.Continent = rset.getInt("Continent");

                // cnt.Continent = rset.getString("Continent");
               // cnt.Population = (int) rset.getLong("Population");
               // cnt.Name = rset.getString("Name");
                //cnt.Region = rset.getString("Region");

                System.out.println(cnt.Continent + " : " + cty.Name + " : " + cty.Population);
                //System.out.println(cty.Name + " : " + cty.Population);
                //System.out.println(cnt.Population + " " + cnt.Name);
                //System.out.println(cnt.Population+ " " + cnt.Region);
                //System.out.println(cnt.Population+ " " + cnt.Continent);

                city.add(cty);

            }

            return city;
        } catch (Exception e) {
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
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(50000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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



