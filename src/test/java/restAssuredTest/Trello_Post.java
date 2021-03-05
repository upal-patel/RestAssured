package restAssuredTest;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Trello_Post {
	
	String id;
	String key = "Enter your key";
	String token = "Enter your token";
	//String name = "{qqqqqqqq}";
	
	//@Test
	public void Test1() throws UnirestException {
		
	//Response response =	RestAssured.post("https://api.trello.com/1/boards/?key=a8194b48255e8477ece3fb92dce660d8&token=2a53e24aa86bc6ff898c30932b347c8d1cb785796c821241a36adf3bb7e8bb91&name=Upal");
	//System.out.println(response.getStatusCode());
		HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/")
				  .queryString("key", "a8194b48255e8477ece3fb92dce660d8")
				  .queryString("token", "2a53e24aa86bc6ff898c30932b347c8d1cb785796c821241a36adf3bb7e8bb91")
				  .queryString("name", "Upal p")
				  .asString();

				System.out.println(response.getBody());
				System.out.println(response.getStatus());
		
	}
	//@Test
	public void Test_UpdateBoard() throws UnirestException {
		HttpResponse<String> response = Unirest.put("https://api.trello.com/1/boards/602861dcf7c0107b1e138cd6")
				  .queryString("key", "a8194b48255e8477ece3fb92dce660d8")
				  .queryString("token", "2a53e24aa86bc6ff898c30932b347c8d1cb785796c821241a36adf3bb7e8bb91")
				  .queryString("name", "Updated_Boards")
				  .asString();

				System.out.println(response.getBody());
				String S = response.getBody().toString();
				System.out.println(S);
				String attributes[] = (response.getBody().toString().split(","));
				String id = attributes[0];
				System.out.println(id);
	}
	@Test
	public void trial() {
		
		JsonPath response = 	given().
				contentType(ContentType.JSON).
				relaxedHTTPSValidation().
				queryParam("key", key).
				queryParam("token", token).
				queryParam("name", "jaiminkach").
				//param("name", "jaiminkach").
				when().
				post("https://api.trello.com/1/boards/").
				then().
				assertThat().
				statusCode(200).
				extract().
				jsonPath();
		
		id = response.getString("id");
		
		
		 System.out.println(id);
		
	}
	@Test
	public void zdte() {
		given().
		contentType(ContentType.JSON).
		queryParam("key", key).
		queryParam("token", token).
		//param("key", key).
		//param("token", token).
		pathParam("id", id).
		when().
        delete("https://api.trello.com/1/boards/{id}").
        then().
        assertThat().
        statusCode(200);
	}
	

}
