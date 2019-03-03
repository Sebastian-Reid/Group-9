package com.napier.sem;

public class City {

    public int ID;
    public String Name;
    public String CountryCode;
    public String District;
    public int Population;

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
                cty.Population = rset.getString("Population");

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
                cty.Population = rset.getString("Population");
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
                cty.Population = rset.getString("Population");
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
                cty.Population = rset.getString("city.Population");
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
                cty.Population = rset.getString("Population");

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






}
