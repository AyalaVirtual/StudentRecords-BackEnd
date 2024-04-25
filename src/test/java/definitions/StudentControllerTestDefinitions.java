package definitions;

import org.example.studentrecordsapi.StudentRecordsApiApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import io.restassured.response.Response;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@CucumberContextConfiguration
// This configures the web environment to use a random port in order to avoid port number conflicts in the test environment
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StudentRecordsApiApplication.class)
public class StudentControllerTestDefinitions {

    private static final String BASE_URL = "http://localhost:";
    private static final Logger log = Logger.getLogger(StudentControllerTestDefinitions.class.getName());

    // This injects the random port used by the test into the class at runtime
    @LocalServerPort
    String port;

    private static Response response;


    /**
     * This test checks if the HTTP status code from the GET all method is OK, otherwise, it catches the exception
     */
    @Given("A list of students are available")
    public void aListOfStudentsAreAvailable() {
        log.info("Calling aListOfStudentsAreAvailable");

        try {
            // This uses the exchange method to get data by executing the request of the HTTP GET method and returning a ResponseEntity instance, which we can get the response status, body and headers from. To query data for the given properties, we can pass them as URI variables.
            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/students/", HttpMethod.GET, null, String.class);
            List<Map<String, String>> students = JsonPath.from(String.valueOf(response.getBody())).get("data");

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            // Assert.assertTrue(students.size() > 0);

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }


    /**
     * These tests check that when you call the POST method, the HTTP status code is 201 (created)
     */
    @When("I add a student to my list")
    public void iAddAStudentToMyList() throws JSONException {
        log.info("Calling iAddAStudentToMyList");

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "FirstName LastName");
        requestBody.put("email", "student@email.com");
        requestBody.put("dateOfBirth", "December 24, 2017");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/students/");
    }

    @Then("The student is added")
    public void theStudentIsAdded() {
        log.info("Calling theStudentIsAdded");
        Assert.assertEquals(201, response.getStatusCode());
    }


    /**
     * These tests check that when you call the GET method for a specific student, the HTTP status code is 200 (ok)
     */
    @When("I get a specific student")
    public void iGetASpecificStudent() {
        log.info("Calling iGetASpecificStudent");

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get(BASE_URL + port + "/api/students/1/");
    }

    @Then("The specific student is available")
    public void theSpecificStudentIsAvailable() {
        log.info("Calling theSpecificStudentIsAvailable");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode());
    }


    /**
     * These tests check that when you call the PUT method for a specific student, the HTTP status code is 200 (ok)
     */
    @When("I edit a student from my list")
    public void iEditAStudentFromMyList() throws JSONException {
        log.info("Calling iEditAStudentFromMyList");

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "FirstName LastName");
        requestBody.put("email", "student@email.com");
        requestBody.put("dateOfBirth", "December 24, 2017");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/students/1/");
    }

    @Then("The student content is edited")
    public void theStudentContentIsEdited() {
        log.info("Calling theStudentContentIsEdited");

        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("success", message);
    }


    /**
     * These tests check that when you call the DELETE method for a specific student, the HTTP status code is 200 (ok)
     */
    @When("I remove a student from my list")
    public void iRemoveAStudentFromMyList() {
        log.info("Calling iRemoveAStudentFromMyList");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/students/1/");
    }

    @Then("The student is removed")
    public void theStudentIsRemoved() {
        log.info("Calling theStudentIsRemoved");

        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("success", message);
    }

}
