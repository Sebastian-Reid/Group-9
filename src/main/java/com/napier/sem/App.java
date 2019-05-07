package com.napier.sem;

//import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
@RestController

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
        SpringApplication.run(App.class, args);

        // SQL Statements
        a.getPopulationOrder(); // 1
        a.getContinentPop(); // 2
        a.getRegionPop(); // 3
        a.getPopCity(); //7
        a.getCityPopCon(); //8
        a.getPopCityReg(); // 9
        a.getPopCityCount(); //10
        a.getDiscPop(); //11
        a.getAllCapital(); //17
        a.getAllCapitalContinent("Asia"); //18
        a.getRegionCapital("Caribbean"); //19
        a.getContinentPopulation(); // 23
        a.getCountryRegionPopulation(); //24
        a.getCountryPopulation(); //25

        a.disconnect();
    }

    // 1. All the countries in the WORLD organised by largest population to smallest
    @RequestMapping("world_population")
    public ArrayList<Country> getPopulationOrder() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT country.Name, country.Population"
                            + " FROM country"
                            + " ORDER BY Population DESC LIMIT 3";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> PopOrder = new ArrayList<>();
            System.out.println("Country | Population");
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getInt("Population");

                System.out.println(cnt.Name + " | " + cnt.Population);
                PopOrder.add(cnt);
            }
            System.out.println("\n");
            return PopOrder;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    // 2. All the countries in a CONTINENT organised by largest population to smallest.
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
                            + " ORDER BY country.Continent, SUM(country.Population) DESC LIMIT 3";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> ContPop = new ArrayList<>();
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Name = rset.getString("Name");
                cnt.Population = rset.getInt("Population");
                cnt.Continent = rset.getString("Continent");
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

    // 3. All the countries in a REGION organised by largest population to smallest.
    public ArrayList<Country> getRegionPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT country.Region, country.Name, SUM(country.Population)"
                            + " FROM country"
                            + " GROUP BY country.Region, country.Name"
                            + " ORDER BY country.Region, SUM(country.Population) DESC";
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

    // 7. All the cities in the WORLD organised by largest population to smallest.
    @RequestMapping("world_city_population")
    public ArrayList<City> getPopCity() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, city.Population"
                            +" FROM city"
                            +" ORDER BY city.Population DESC LIMIT 3";
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
    // 10. All the cities in a COUNTRY organised by largest population to smallest.
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

    // 11. All the cities in a DISTRICT organised by largest population to smallest.
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

    // 17. All the capital cities in the WORLD organised by largest population to smallest.
    @RequestMapping("all_city_population")
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
                            + "ORDER BY city.population DESC LIMIT 3";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> capCity = new ArrayList<City>();
            System.out.println("Capital City | Population | Country");
            while (rset.next())
            {
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                // cCty.CountryCode = rset.getString("CountryCode");
                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                System.out.println(cCty.Name + " | " + cCty.Population + " | " + cCountry.Name);
                capCity.add(cCty);
            }
            System.out.println("\n");
            return capCity;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }

    }

    // 18. All the capital cities in a CONTINENT organised by largest population to smallest. (Continent = 'Asia')
    @RequestMapping("continent_capital_city")
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
                            + " ORDER BY city.Population DESC LIMIT 3";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> capCity = new ArrayList<City>();
            System.out.println("Capital City | Population | Country");
            while (rset.next())
            {
                // Create new City (to store in database)
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");

                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                System.out.println(cCty.Name +  " | " + cCty.Population + " | " + cCountry.Name);
                capCity.add(cCty);
            }
            System.out.println("\n");
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

   // 19. All the capital cities in a REGION organised by largest to smallest. (Region = 'Caribbean')
    @RequestMapping("region_capital_city")
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
                            + " ORDER BY city.Population DESC LIMIT 3";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> capCity = new ArrayList<City>();
            System.out.println("Capital City | Population | Country");
            while (rset.next())
            {
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");

                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                System.out.println(cCty.Name +  " | " + cCty.Population + " | " + cCountry.Name);

                capCity.add(cCty);
            }
            System.out.println("\n");
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
    // 23. The population of people, people living in cities, and people not living in cities in each CONTINENT.
    public ArrayList<Country> getContinentPopulation()
    {
        // i. Population of people in each CONTINENT
        try
        {
            Statement stmt = con.createStatement();

            //population of people in each CONTINENT
            String strSelect =
                    " SELECT DISTINCT(country.Continent) AS dContinent, SUM(DISTINCT country.Population) AS coPopulation, SUM(city.Population) AS cPopulation" +
                            " FROM country JOIN city ON country.Code = city.CountryCode" +
                            " WHERE country.Code = city.CountryCode" +
                            " GROUP BY dContinent DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country= new ArrayList<Country>();
            System.out.println(" Continent | Continent Pop | City Pop | City Pop % | Not a City Pop | Not a City Pop %");
            while (rset.next())
            {
                Country cnt = new Country();
                cnt.Population = (int) rset.getLong("Population");
                cnt.Name = rset.getString("Region");

                City cCity = new City();
                //cCity.Name = rset.getString("cName");
                cCity.Population = rset.getInt("cPopulation");

                System.out.println(cnt.Continent + " | " + cnt.Population + " | " + cCity.Population + " | " + (((cCity.Population * 100) / (cnt.Population))) + " | " + (cnt.Population - cCity.Population) + " | " + (100 - (cCity.Population * 100) / (cnt.Population)));

                country.add(cnt);
            }
            System.out.println("\n");
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("No continent populations in the array");

            return null;
        }
    }

    // 24. The population of people, people living in cities, and people not living in cities in each REGION.
    public ArrayList<Country> getCountryRegionPopulation()
    {
        try
        {
            Statement stmt = con.createStatement();

            String strSelect =
                    " SELECT DISTINCT(country.Region) AS dRegion, SUM(DISTINCT country.Population) AS coPopulation, SUM(DISTINCT city.Population) AS cPopulation" +
                            " FROM country JOIN city ON country.Code = city.CountryCode" +
                            " WHERE country.Code = city.CountryCode" +
                            " GROUP BY dRegion DESC"; //population of people in each region

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country= new ArrayList<Country>();
            System.out.println(" Region | Region Pop | City Pop | City Pop % | Not a City Pop | Not a City Pop %");
                while(rset.next())
                {
                    Country cnt = new Country();
                    cnt.Region = rset.getString("dRegion");
                    cnt.Population = rset.getInt("coPopulation");

                    City cCity = new City();
                    cCity.Population = rset.getInt("cPopulation");

                    System.out.println(cnt.Region + " | " + cnt.Population + " | " + cCity.Population + " | " + (((cCity.Population * 100) / (cnt.Population))) + " | " + (cnt.Population - cCity.Population) + " | " + (100 - (cCity.Population * 100) / (cnt.Population)));
                }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("No region populations in array");
            return null;
        }
    }

    // 25. The population of people, people living in cities, and people not living in cities in each COUNTRY.
    @RequestMapping("country_population")
    public ArrayList<Country> getCountryPopulation() {
        try {
            Statement stmt = con.createStatement();

            //population of people in each COUNTRY
            String strSelect =
                    " SELECT DISTINCT(country.Name) AS dCountry, SUM(DISTINCT country.Population) AS coPopulation, SUM(DISTINCT city.Population) AS cPopulation" +
                            " FROM country JOIN city ON country.Code = city.CountryCode" +
                            " WHERE country.Code = city.CountryCode" +
                            " GROUP BY dCountry DESC "; // population of people in each country

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();

            while(rset.next())
            {
                Country cnt = new Country();
                cnt.Name = rset.getString("dCountry");
                cnt.Population = (int)rset.getLong("coPopulation");

                City cCity = new City();
                cCity.Population = (int)rset.getLong("cPopulation");

                System.out.println(cnt.Name + " | " + cnt.Population + " | " + cCity.Population + " | " + (((cCity.Population * 100) / (cnt.Population))) + " | " + (cnt.Population - cCity.Population) + " | " + (100 - (cCity.Population * 100) / (cnt.Population)));

                country.add(cnt);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No country populations in array");

            return null;
        }
    }

    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     * @param s
     */
    public static void connect(String s) {
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
                Thread.sleep(50000);
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
    public static void disconnect() {
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
