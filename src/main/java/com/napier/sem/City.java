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
                    //SELECT  COUNTRY.Continent, CITY.Name, CITY.Population
//FROM CITY
//INNER JOIN COUNTRY ON CITY.CountryCode=COUNTRY.Code
//ORDER BY COUNTRY.Continent, CITY.Population DESC
                    "SELECT country.Continent, city.Name, city.Population"
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









}
