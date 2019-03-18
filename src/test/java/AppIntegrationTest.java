

import com.napier.sem.App;
import com.napier.sem.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        //app.connect("db");
        app.connect("localhost:33060");
    }

//    @Test
//    void testGetCapital()
//    {
//        Country country = app.getCountryPopulation("103000");
//        assertEquals(country.Population, "103000" );
//        assertEquals(country.Name, "Aruba");
//    }
}