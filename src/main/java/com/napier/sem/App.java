package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        App a = new App();

        a.connect();

        ArrayList<Country> country = a.getCountry();

        a.disconnect();

    }

    public ArrayList<Country> getCountry() {
        try {
            Statement stmt = con.createStatement();

            //All the countries in the WORLD organised by largest population to smallest.
            /*String strSelect =
                    "SELECT country.Name, country.Population"
                            + " FROM country"
                            + " ORDER BY Population DESC"; */

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

            // All the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, country.name AS 'CountryName', city.Population "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID "
                            + "ORDER BY city.population DESC";

            /*// All the capital cities by CONTINENT organised by largest population to smallest
            "String strSelect =
                  "SELECT city.Name, country.Name AS 'CountryName', city.Population "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID AND country.Continent = " + "'" + continent +"'"
                            + " ORDER BY city.Population DESC"; */

            /*// All the capital cities by REGION organised by largest population to smallest
            "String strSelect =
                   "SELECT city.Name, country.Name AS 'CountryName', city.Population "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID AND country.Region = " + "'" + region +"'"
                            + " ORDER BY city.Population DESC"; */

            //population of people in each CONTINENT
           /* String strSelect =
                   " SELECT DISTINCT(country.Continent) AS Continent, SUM(country.Population) AS Population "
                + " FROM country" +
                           " GROUP BY Continent"; */

            //population of people in each REGION
            /* String strSelect =
                " SELECT  DISTINCT(country.Region) AS Region, SUM(country.Population) AS Population "
                        + " FROM country" +
                        " GROUP BY Region"; */

            //population of people in each COUNTRY
            /*String strSelect =
                    " SELECT  DISTINCT(country.Name) AS Name, SUM(country.Population) AS Population "
                            + " FROM country" +
                            " GROUP BY Name"; */

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country cnt = new Country();

                cnt.Continent = rset.getString("Continent");
                cnt.Population = (int) rset.getLong("Population");
                //cnt.Name = rset.getString("Name");
                //cnt.Region = rset.getString("Region");


                //System.out.println(cnt.Population + " " + cnt.Name);
                //System.out.println(cnt.Population+ " " + cnt.Region);
                //System.out.println(cnt.Population+ " " + cnt.Continent);

                // Capital City population
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                // cCty.CountryCode = rset.getString("CountryCode");
                Country cCountry = new Country();

                cCountry.Name = rset.getString("CountryName");
                System.out.println(cCty.Name +  " " + cCty.Population + " " + cCountry.Name);

                country.add(cnt);
            }

            return country;
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



