import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

public class PetStoreAPISteps {
    private Response response;
    private String baseUrl = "https://petstore.swagger.io/v2/user";
    private String username;

    @Given("I have the user details {string}")
    public void i_have_the_user_details(String username) {
        username = username;
    }

    @When("I create the user using POST request with {int},{string},{string},{string},{string},{string},{int}")
    public void i_create_the_user_using_POST_request(int  id, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        RestAssured.baseURI = baseUrl + "/createWithArray";
        RequestSpecification request = RestAssured.given();

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("username", username);
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("phone", phone);
        jsonObject.put("userStatus", userStatus);
        jsonArray.put(jsonObject);

        request.header("Content-Type", "application/json");
        request.body(jsonArray.toString());

        response = request.post();
        Assert.assertEquals(200, response.getStatusCode());
        
        JSONObject jsonResponse = new JSONObject(response.asString());
    }

    @Then("I verify the user is created using GET request with {int},{string},{string},{string},{string},{string},{string},{int}")
    public void i_verify_the_user_is_created_using_GET_request(int  id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        RestAssured.baseURI = baseUrl + "/" + username;
        RequestSpecification request = RestAssured.given();
        response = request.get();

        Assert.assertEquals(200, response.getStatusCode());
        JSONObject jsonResponse = new JSONObject(response.asString());
        Assert.assertEquals(id, jsonResponse.getInt("id"));
        Assert.assertEquals(username, jsonResponse.getString("username"));
        Assert.assertEquals(firstName, jsonResponse.getString("firstName"));
        Assert.assertEquals(lastName, jsonResponse.getString("lastName"));
        Assert.assertEquals(email, jsonResponse.getString("email"));
        Assert.assertEquals(password, jsonResponse.getString("password"));
        Assert.assertEquals(phone, jsonResponse.getString("phone"));
        Assert.assertEquals(userStatus, jsonResponse.getInt("userStatus"));
    }

}