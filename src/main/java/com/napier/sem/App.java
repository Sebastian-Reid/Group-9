package com.napier.sem;

import java.sql.*;
import java.util.Scanner;
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

        ArrayList<Country> country = a.getCountry();
        // Disconnect from database
        a.disconnect();

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
                Thread.sleep(5000);
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

    // 1st statement
    public ArrayList<Country> getCountry() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Population"
                            + "FROM country"
                            + "ORDER BY country.Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();
            while(rset.next())
            {
                Country cnt = new Country();
                cnt.Population = rset.getInt("Population");
                cnt.Name = rset.getString("Name");

                country.add(cnt);
            }

            return country;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }


/*
    //2nd statement
    public Country getCountry(String Name) {
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
                cnt.Continent = rset.getint("Continent");
                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getint("Population");

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
                cnt.Population = rset.getInt("Population");

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

    //sql 7
    public City getCity(String Name, int Population)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.Population"
                            +"FROM city"
                            +"ORDER BY city.Population DESC"


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City cty = new City();
                cty.Region = rset.getString("Region");
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");

                //System.out.println(cty.Region + " : " + cty.Name + " : " + cty.Population);

                return cty;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

//sql 8

    public City getCity(String Name, int Population)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT country.Continent, city.Name, city.Population"
                            +"FROM city"
                            +"INNER JOIN country ON city.CountryCode=Country.Code"
                            +"ORDER BY country.Continent, city.Population DESC"


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City cty = new City();
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");
                cnt.Continent = rset.getString("Continent");

                //System.out.println(cnt.Continent + " : " + cty.Name + " : " + cty.Population);

                return cty;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }


    //sql 9
    public City getCity(String Name, int Population)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT country.Region, city.Name, city.Population"
                            +"FROM city"
                            +"INNER JOIN country ON city.CountryCode=Country.Code"
                            +"ORDER BY country.Region, city.Population DESC"


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City cty = new City();
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");
                cnt.Region = rset.getString("Region");

                //System.out.println(cnt.Region + " : " + cty.Name + " : " + cty.Population);

                return cty;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }
    //sql 10
    public City getCity(String Name, int Population)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT country.Name, city.Name, city.Population"
                            +"FROM city"
                            +"INNER JOIN country ON city.CountryCode=Country.Code"
                            +"ORDER BY country.Continent, city.Population DESC"


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City cty = new City();
                cty.Name = rset.getString("city.Name");
                cty.Population = rset.getInt("city.Population");
                cnt.Name = rset.getString("country.Name");

                //System.out.println(cnt.Name + " : " + cty.Name + " : " + cty.Population);

                return cty;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }


//sql 11

    public City getCity(String Name, int Population, String District)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.District, city.Name, city.Population"
                            +"FROM city"
                            +"ORDER BY city.District, city.Population DESC"


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City cty = new City();
                cty.District = rset.getString("District");
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");

                //System.out.println(cty.District + " : " + cty.Name + " : " + cty.Population);

                return cty;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }
*/
//sql 4

/*

    class Input {
        public static void main (String[] args){
            Scanner input = new Scanner(System.in);

            System.out.print("Enter N where N is the number of top results you wish to see: ");
            int number = input.nextInt();
        }


        public City getCity(String Name, int Population, String District, int number)
        {
            try
            {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT country.Name, country.Population"
                                + "FROM country"
                                + "ORDER BY country.Population DESC"
                                + "LIMIT 'number'";


                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return new employee if valid.
                // Check one is returned
                if (rset.next())
                {
                    City cty = new City();
                    cty.District = rset.getString("District");
                    cty.Name = rset.getString("Name");
                    cty.Population = rset.getInt("Population");

                    //System.out.println(cty.District + " : " + cty.Name + " : " + cty.Population);

                    return cty;
                }
                else
                    return null;

            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Failed to get city details");
                return null;
            }
        }
*/
}



