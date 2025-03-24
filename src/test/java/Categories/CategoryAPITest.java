package Categories;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.RestAssured.given;

/**
 * This class contains test cases for the Category API.
 * It validates the API response against the specified acceptance criteria:
 * 1. The Name field should be "Carbon credits".
 * 2. The CanRelist field should be true.
 * 3. The Promotions element with Name = "Gallery" should have a Description containing "Good position in category".
 */
public class CategoryAPITest {

    private static final Logger logger = Logger.getLogger(CategoryAPITest.class.getName());
    private static final String GET_CATEGORY_BY_ID = "/v1/Categories/6327/Details.json?catalogue=false";
    private static final Level LOG_LEVEL = Level.INFO;

    /**
     * This method sets up the test environment before all tests are executed.
     * It loads the API URL and Log level from the config.properties file.
     */
    @BeforeAll
    public static void configSetup() {

        logger.setLevel(LOG_LEVEL);
        logger.info("Starting Category API Test Execution");
        // Load the configuration file
        try {
            InputStream configPropFile = CategoryAPITest.class.getClassLoader().getResourceAsStream("config.properties");
            Properties props = new Properties();
            if (configPropFile == null) {
                logger.severe("Could not find config.properties file");
                throw new RuntimeException("Unable to find config.properties");
            }
            props.load(configPropFile);

            // Get the API base URL from the config file
            String apiCategoryUrl = props.getProperty("api.category.url");
            if (apiCategoryUrl == null || apiCategoryUrl.isEmpty()) {
                logger.severe("api.category.url is not defined in config.properties");
                throw new RuntimeException("api.category.url is not defined in config.properties");
            }

            // Set the base URI for RestAssured
            RestAssured.baseURI = apiCategoryUrl;
        } catch (Exception e) {
            logger.severe("Failed to start the test");
            throw new RuntimeException("Failed to start the test", e);
        }
    }

    /**
     * This test case validates the Category API response.
     * It performs the following checks:
     * 1. Asserts that the API returns a 200 status code.
     * 2. Asserts that the Name field is "Carbon credits".
     * 3. Asserts that the CanRelist field is true.
     * 4. Asserts that the Promotions element with Name = "Gallery" has a Description containing "Good position in category".
     */
    @Test
    public void testCategoryApi() {
        // Send a GET request to the API endpoint
        Response apiResponse = given()
                .get(GET_CATEGORY_BY_ID)
                .then()
                .extract()
                .response();


        assertEquals(200, apiResponse.getStatusCode(), "API did not return a 200 status code");

        String name = apiResponse.jsonPath().getString("Name");
        assertNotNull(name, "Name is null");
        assertEquals("Carbon credits", name, "Name is not 'Carbon credits'");

        boolean canRelist = apiResponse.jsonPath().getBoolean("CanRelist");
        assertTrue(canRelist, "CanRelist is not true");

        String galleryDescription = apiResponse.jsonPath().getString("Promotions.find { it.Name == 'Gallery' }.Description");
        assertNotNull(galleryDescription, "Promotion with Name 'Gallery' not found");
        assertTrue(galleryDescription.contains("Good position in category"), "Description does not contain 'Good position in category'");
    }
}