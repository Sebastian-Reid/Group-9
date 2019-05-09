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
        a.getAllCapitalContinent(); //18
        a.getRegionCapital(); //19
        a.getContinentPopulation(); // 23
        a.getRegionPopulation(); //24
        a.getCountryPopulation(); //25
        a.getWorldPopulation(); //26
        a.getAContinentPopulation("Asia"); // 27
        a.getARegionPopulation("Caribbean"); //28
        a.getACountryPopulation("Japan"); //29
        a.getADistrictPopulation("Tennessee"); //30
        a.getACityPopulation("Tokyo"); //31

        a.disconnect();
    }

    // 1. All the countries in the WORLD organised by largest population to smallest
    @RequestMapping("world_population")
    public ArrayList<Country> getPopulationOrder() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital"
                            + " FROM country"
                            + " ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> PopOrder = new ArrayList<>();
            System.out.println("1. All the countries in the WORLD organised by largest population to smallest");
            System.out.println(" Code | Name | Continent | Region | Population | Capital");
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Code = rset.getString("Code");
                cnt.Name = rset.getString("Name");
                cnt.Continent = rset.getString("Continent");
                cnt.Region = rset.getString("Region");
                cnt.Population = rset.getInt("Population");
                cnt.Capital = rset.getInt("Capital");

                System.out.println(cnt.Code + " | " + cnt.Name + " | " + cnt.Continent + " | " + cnt.Region + " | " + cnt.Population + " | " + cnt.Capital );
                PopOrder.add(cnt);
            }
            System.out.println("\n");
            return PopOrder;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("1. Failed to get country details");
            return null;
        }
    }

    // 2. All the countries in a CONTINENT organised by largest population to smallest.
    public ArrayList<Country> getContinentPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) AS cPopulation, country.Code, country.Name, country.Continent, country.Region, country.Capital"
                            + " FROM country"
                            + " GROUP BY country.Continent, country.Code, country.Name, country.Continent, country.Region, country.Capital"
                            + " ORDER BY country.Continent, cPopulation DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> Country = new ArrayList<>();
            System.out.println("2. All the countries in a CONTINENT organised by largest population to smallest.");
            System.out.println("Code | Name | Continent | Region | Population | Capital");
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Code = rset.getString("Code");
                cnt.Name = rset.getString("Name");
                cnt.Continent = rset.getString("Continent");
                cnt.Region = rset.getString("Region");
                cnt.Population = rset.getInt("cPopulation");
                cnt.Capital = rset.getInt("Capital");

                System.out.println(cnt.Code + " | " + cnt.Name + " | " + cnt.Continent + " | " + cnt.Region + " | " + cnt.Population + " | " + cnt.Capital );
                Country.add(cnt);
            }
            return Country;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("2. Failed to get country details");
            return null;
        }
    }

    // 3. All the countries in a REGION organised by largest population to smallest.
    public ArrayList<Country> getRegionPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(country.Population) AS cPopulation, country.Code, country.Name, country.Continent, country.Region, country.Capital"
                            +" FROM country"
                            +" GROUP BY country.Region, country.Name, country.Code, country.Continent, country.Capital"
                            +" ORDER BY country.Region, cPopulation DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<Country> Country = new ArrayList<>();
            System.out.println("3. All the countries in a REGION organised by largest population to smallest.");
            System.out.println("Code | Name | Continent | Region | Population | Capital");
            while (rset.next()) {
                // Create new Country (to store in database)
                Country cnt = new Country();
                cnt.Code = rset.getString("Code");
                cnt.Name = rset.getString("Name");
                cnt.Continent = rset.getString("Continent");
                cnt.Region = rset.getString("Region");
                cnt.Population = rset.getInt("cPopulation");
                cnt.Capital = rset.getInt("Capital");

                System.out.println(cnt.Code + " | " + cnt.Name + " | " + cnt.Continent + " | " + cnt.Region + " | " + cnt.Population + " | " + cnt.Capital);
                Country.add(cnt);
            }
            return Country;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("3. Failed to get city details");
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
            String strSelect =
                    "SELECT city.Name, city.Population, city.CountryCode, city.District"
                            +" FROM city"
                            +" ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> CtyPop = new ArrayList<>();
            System.out.println("7. All the cities in the WORLD organised by largest population to smallest.");
            System.out.println("Name | Country | District | Population");
            while (rset.next()) {
                // Create new city (to store in database)
                City cty = new City();
                cty.Name = rset.getString("Name");
                cty.CountryCode = rset.getString("CountryCode");
                cty.District = rset.getString("District");
                cty.Population = rset.getInt("Population");

                System.out.println(cty.Name + " | " + cty.CountryCode + " | " + cty.District + " | " + cty.Population);
                CtyPop.add(cty);
            }
            return CtyPop;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("7. Failed to get city details");
            return null;
        }
    }

    // 8. All the cities in a CONTINENT organised by largest population to smallest.
    public ArrayList<City> getCityPopCon() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, city.Name, city.Population, city.District, city.CountryCode"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode = country.Code"
                            +" ORDER BY country.Continent, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> CityPopCon = new ArrayList<>();
            System.out.println("8. All the cities in a CONTINENT organised by largest population to smallest.");
            System.out.println("Continent | Name | Country | District | Population");
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("Name");
                cty.CountryCode = rset.getString("CountryCode");
                cty.District = rset.getString("District");
                cty.Population = rset.getInt("Population");

                Country cnt = new Country();
                cnt.Continent = rset.getString("Continent");
                System.out.println(cnt.Continent + " | " + cty.Name + " | " + cty.CountryCode + " | " + cty.District + " | " + cty.Population);
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
            String strSelect =
                    "SELECT country.Region, city.Name, city.Population,  city.District, city.CountryCode"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode = country.Code"
                            +" ORDER BY country.Region, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> PopCityReg = new ArrayList<>();
            System.out.println("9. All the cities in a REGION organised by largest population to smallest.");
            System.out.println("Region | Name | Country | District | Population");
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("Name");
                cty.Population = rset.getInt("Population");
                cty.CountryCode = rset.getString("CountryCode");
                cty.District = rset.getString("District");

                Country cnt = new Country();
                cnt.Region = rset.getString("Region");
                System.out.println(cnt.Region + " | " + cty.Name + " | "  + cty.CountryCode + " | " + cty.District + " | " + cty.Population);
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
            String strSelect =
                    "SELECT country.Name, (city.Name) AS cName, city.Population, city.District, city.CountryCode"
                            +" FROM city"
                            +" INNER JOIN country ON city.CountryCode = country.Code"
                            +" ORDER BY country.Name, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> PopCityCount = new ArrayList<>();
            System.out.println("10. All the cities in a COUNTRY organised by largest population to smallest.");
            System.out.println("Name | Country | District | Population");
            while (rset.next()) {
                // Create new Country/City (to store in database)
                City cty = new City();
                cty.Name = rset.getString("cName");
                cty.Population = rset.getInt("Population");
                cty.District = rset.getString("District");
                cty.CountryCode = rset.getString("CountryCode");

                Country cnt = new Country();
                cnt.Name = rset.getString("Name");
                System.out.println(cnt.Name + " | " + cty.Name + " | " + cty.CountryCode + " | " + cty.District + " | " + cty.Population);
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
            String strSelect =
                    "SELECT city.District, (city.Name) AS cName, city.Population, city.District, city.CountryCode"
                            +" FROM city"
                            +" ORDER BY city.District, city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> DiscPop = new ArrayList<>();
            System.out.println("11. All the cities in a COUNTRY organised by largest population to smallest.");
            System.out.println("Name | Country | District | Population");
            while (rset.next()) {
                // Create new city (to store in database)
                City cty = new City();
                cty.Name = rset.getString("cName");
                cty.Population = rset.getInt("Population");
                cty.District = rset.getString("District");
                cty.CountryCode = rset.getString("CountryCode");

                System.out.println(cty.Name + " | " + cty.CountryCode + " | " + cty.District + " | "  + cty.Population);
                DiscPop.add(cty);
            }
            return DiscPop;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("11. Failed to get city details");
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
            // ALL the capital cities in the WORLD organised by largest population to smallest
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
            System.out.println("17. All the capital cities in the WORLD organised by largest population to smallest.");
            System.out.println("Name | Country | Population");
            while (rset.next())
            {
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                // cCty.CountryCode = rset.getString("CountryCode");
                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                System.out.println(cCty.Name + " | " + cCountry.Name +" | " + cCty.Population );
                capCity.add(cCty);
            }
            System.out.println("\n");
            return capCity;
        } catch (Exception e) {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("17. Failed to get capital city details");
            return null;
        }
    }

    // 18. All the capital cities in a CONTINENT organised by largest population to smallest. (Continent = 'Asia')
    @RequestMapping("continent_capital_city")
    public ArrayList<City> getAllCapitalContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, country.Name AS 'CountryName', city.Population, country.Continent "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID "
                            + " ORDER BY country.Continent, city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> capCity = new ArrayList<City>();
            System.out.println("18. All the capital cities in a CONTINENT organised by largest population to smallest.");
            System.out.println("Continent | Name | Country | Population");
            while (rset.next())
            {
                // Create new City (to store in database)
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                cCountry.Continent = rset.getString("Continent");
                System.out.println(cCountry.Continent + " | " + cCty.Name + " | " + cCountry.Name +" | " + cCty.Population );
                capCity.add(cCty);
            }
            System.out.println("\n");
            return capCity;
        }
        catch (Exception e)
        {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("18. Failed to get capital city details by continent");
            return null;
        }
    }

   // 19. All the capital cities in a REGION organised by largest to smallest. (Region = 'Caribbean')
    @RequestMapping("region_capital_city")
    public ArrayList<City> getRegionCapital()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // ALl the capital cities in the WORLD organised by largest population to smallest
            String strSelect =
                    "SELECT city.Name, country.Name AS 'CountryName', city.Population, country.Region "
                            + "FROM country JOIN city "
                            + "ON country.Code = city.CountryCode  "
                            + "WHERE country.Capital = city.ID "
                            + " ORDER BY country.Region, city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> capCity = new ArrayList<City>();
            System.out.println("19. All the capital cities in a REGION organised by largest to smallest.");
            System.out.println("Region | Name | Country | Population");
            while (rset.next())
            {
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");
                Country cCountry = new Country();
                cCountry.Name = rset.getString("CountryName");
                cCountry.Region = rset.getString("Region");
                System.out.println(cCountry.Region + " | " + cCty.Name + " | " + cCountry.Name +" | " + cCty.Population );

                capCity.add(cCty);
            }
            System.out.println("\n");
            return capCity;
        }
        catch (Exception e)
        {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("19. Failed to get capital city details by region");
            return null;
        }

    }
    // 23. The population of people, people living in cities, and people not living in cities in each CONTINENT.
    @RequestMapping("continent_population")
    public ArrayList<Country> getContinentPopulation()
    {
        try
        {
            Statement stmt = con.createStatement();

            //population of people in each CONTINENT
            String strSelect =
                    " SELECT DISTINCT(country.Continent) AS dContinent, SUM(DISTINCT country.Population) AS coPopulation, SUM(city.Population) AS cPopulation" +
                            " FROM country JOIN city ON country.Code = city.CountryCode" +
                            " WHERE country.Code = city.CountryCode" +
                            " GROUP BY dContinent";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country= new ArrayList<Country>();
            System.out.println("23. The population of people, people living in cities, and people not living in cities in each CONTINENT.");
            System.out.println(" Continent | Continent Pop | City Pop | City Pop % | Not a City Pop | Not a City Pop %");
            while (rset.next())
            {
                Country cnt = new Country();
                cnt.Population = rset.getLong("coPopulation");
                cnt.Continent = rset.getString("dContinent");

                City cCity = new City();
                cCity.Population = rset.getLong("cPopulation");

                System.out.println(cnt.Continent + " | " + cnt.Population + " | " + cCity.Population + " | " + (((cCity.Population*100)/(cnt.Population))) + "%" + " | " + (cnt.Population - cCity.Population) + " | " + (100 - ((cCity.Population * 100) / (cnt.Population)))+ "%");

                country.add(cnt);
            }
            System.out.println("\n");
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("23. Failed to get continent populations");

            return null;
        }
    }

    // 24. The population of people, people living in cities, and people not living in cities in each REGION.
    @RequestMapping("region_population")
    public ArrayList<Country> getRegionPopulation()
    {
        try
        {
            Statement stmt = con.createStatement();

            String strSelect =
                    " SELECT DISTINCT(country.Region) AS dRegion, SUM(DISTINCT country.Population) AS coPopulation, SUM(DISTINCT city.Population) AS cPopulation" +
                            " FROM country JOIN city ON country.Code = city.CountryCode" +
                            " WHERE country.Code = city.CountryCode" +
                            " GROUP BY dRegion "; //population of people in each region

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country= new ArrayList<Country>();
            System.out.println(" 24. The population of people, people living in cities, and people not living in cities in each REGION.");
            System.out.println(" Region | Region Pop | City Pop | City Pop % | Not a City Pop | Not a City Pop %");
                while(rset.next())
                {
                    Country cnt = new Country();
                    cnt.Region = rset.getString("dRegion");
                    cnt.Population = rset.getLong("coPopulation");

                    City cCity = new City();
                    cCity.Population = rset.getLong("cPopulation");

                    System.out.println(cnt.Region + " | " + cnt.Population + " | " + cCity.Population + " | " + ((cCity.Population * 100) / (cnt.Population)) + " | " + (cnt.Population - cCity.Population) + " | " + (100 - (cCity.Population * 100) / (cnt.Population)));
                    country.add(cnt);
                }
            System.out.println("\n");
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("24. Failed to get region populations");
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
                            " GROUP BY dCountry "; // population of people in each country

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();
            System.out.println("25. The population of people, people living in cities, and people not living in cities in each COUNTRY.");
            System.out.println(" Country | Country Pop | City Pop | City Pop % | Not a City Pop | Not a City Pop %");
            while(rset.next())
            {
                Country cnt = new Country();
                cnt.Name = rset.getString("dCountry");
                cnt.Population = rset.getLong("coPopulation");

                City cCity = new City();
                cCity.Population = rset.getLong("cPopulation");

                System.out.println(cnt.Name + " | " + cnt.Population + " | " + cCity.Population + " | " + (((cCity.Population * 100) / (cnt.Population))) + " | " + (cnt.Population - cCity.Population) + " | " + (100 - (cCity.Population * 100) / (cnt.Population)));

                country.add(cnt);
            }
            System.out.println("\n");
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("25. Failed to get country populations");

            return null;
        }
    }
    // 26. Population of the World
    @RequestMapping("Population_of_world")
    public ArrayList<Country> getWorldPopulation() {
        try {
            Statement stmt = con.createStatement();

            //population of people in the world
            String strSelect =
                    " SELECT SUM(country.Population) AS wPopulation" +
                            " FROM country";

            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("26. Population of the world.");
            ArrayList<Country> country = new ArrayList<Country>();

            while (rset.next()) {
                Country cnt = new Country();
                cnt.Population = rset.getLong("wPopulation");

                System.out.println("World Population" + " | " + cnt.Population);
                country.add(cnt);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("26. Failed to get world population");

            return null;
        }
    }

    // 27. Population of a Continent
    @RequestMapping("Population_of_all_continent")
    public ArrayList<Country> getAContinentPopulation(String continent) {
        try {
            Statement stmt = con.createStatement();

            //population of people in the world
            String strSelect =
                    " SELECT DISTINCT(country.Continent) AS dContinent, SUM(DISTINCT country.Population) AS coPopulation" +
                            " FROM country " +
                            " WHERE country.Continent = " + "'" + continent + "'";

            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("27. Population of a continent.");
            System.out.println("Continent" + " | " + "Population");
            ArrayList<Country> country = new ArrayList<Country>();

            while (rset.next()) {
                Country cnt = new Country();
                cnt.Continent = rset.getString("dContinent");
                cnt.Population = rset.getLong("coPopulation");

                System.out.println(cnt.Continent + " | " + cnt.Population);
                country.add(cnt);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("27. Failed to get population of each continent");

            return null;
        }
    }

    // 28. Population of a Region
    @RequestMapping("Population_of_a_region")
    public ArrayList<Country> getARegionPopulation(String region) {
        try {
            Statement stmt = con.createStatement();

            //population of people in the world
            String strSelect =
                    " SELECT DISTINCT(country.Region) AS cRegion, SUM(DISTINCT country.Population) AS coPopulation" +
                            " FROM country " +
                            " WHERE country.Region = " + "'" + region + "'";

            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("28. Population of a region.");
            System.out.println("Region" + " | " + "Population");
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country cnt = new Country();
                cnt.Population = rset.getLong("coPopulation");
                cnt.Region = rset.getString("cRegion");

                System.out.println(cnt.Region + " | " + cnt.Population);
                country.add(cnt);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("28. Failed to get population of each region");

            return null;
        }
    }

    // 29. Population of a Country
    @RequestMapping("Population_of_a_region")
    public ArrayList<Country> getACountryPopulation(String region) {
        try {
            Statement stmt = con.createStatement();

            //population of people in the world
            String strSelect =
                    " SELECT DISTINCT(country.Name) AS cName, SUM(DISTINCT country.Population) AS coPopulation" +
                            " FROM country " +
                            " WHERE country.Name = " + "'" + region + "'";

            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("29. Population of a country.");
            System.out.println("Country" + " | " + "Population");
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country cnt = new Country();
                cnt.Population = rset.getLong("coPopulation");
                cnt.Name = rset.getString("cName");

                System.out.println(cnt.Name + " | " + cnt.Population);
                country.add(cnt);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("28. Failed to get population of each region");

            return null;
        }
    }

    // 30. Population of a District
    public ArrayList<City> getADistrictPopulation(String district)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT(city.District), SUM(city.Population) AS cPopulation "
                            + "FROM city "
                            + "WHERE city.District = " + "'" + district +"'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> city = new ArrayList<City>();
            System.out.println("30. Population of a district.");
            System.out.println("City | Population ");
            while (rset.next())
            {
                // Create new City (to store in database)
                City cCty = new City();
                cCty.District = rset.getString("District");
                cCty.Population = rset.getInt("cPopulation");

                System.out.println(cCty.District +  " | " + cCty.Population);
                city.add(cCty);
            }
            System.out.println("\n");
            return city;
        }
        catch (Exception e)
        {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("30. Failed to get district population");
            return null;
        }
    }

    // 30. Population of a City
    public ArrayList<City> getACityPopulation(String city)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT(city.Name), city.Population "
                            + "FROM city "
                            + "WHERE city.Name = " + "'" + city +"'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new capital city if valid.
            // Check one is returned
            ArrayList<City> nCity = new ArrayList<City>();
            System.out.println("31. Population of a city.");
            System.out.println("City | Population ");
            while (rset.next())
            {
                // Create new City (to store in database)
                City cCty = new City();
                cCty.Name = rset.getString("Name");
                cCty.Population = rset.getInt("Population");

                System.out.println(cCty.Name +  " | " + cCty.Population);
                nCity.add(cCty);
            }
            System.out.println("\n");
            return nCity;
        }
        catch (Exception e)
        {
            // Capital City not found.
            System.out.println(e.getMessage());
            System.out.println("30. Failed to get district population");
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
