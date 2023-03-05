package testcase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class TestCase {

    Object total_Page;

    @BeforeClass
    public void beforeClass(){

    }


    @Test
    public void TC_00_GET(){
        String url = "https://reqres.in/api/users?page=2";
        Response response = RestAssured.get(url);
        total_Page = response.jsonPath().get("data[0].email");
        Map<Object,Object> getList = response.jsonPath().get("data[0]");
        Assert.assertEquals(total_Page,"michael.lawson@reqres.in");
        System.out.println(getList);
    }

    @Test
    public void TC_01_POST(){

        String url = "https://reqres.in/api/users";
        String contentType = "application/json";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",total_Page);
        jsonObject.put("job","leader");
        Response response = RestAssured.given().contentType(contentType).body(jsonObject.toString()).post(url);
        String id = response.jsonPath().get("createdAt");
        Assert.assertTrue(id.contains("2023"));
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());

    }


    @Test
    public void TC_02_POST(){
        String url = "https://api.getpostman.com/monitors";
        String contentType = "text/plain";
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("mock");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("collection","con");
        jsonObject.put("environment","env");
        jsonArray.put(jsonObject);
        String body = jsonArray.toString().replace("[","{").replace("]","}");
        Response response = RestAssured.given().contentType(contentType).body(body).post(url);
        System.out.println(response.getBody().asString());
    }



    @Test
    public void TC_03_PUT(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","test");
        jsonObject.put("job","leader");
        String url = "https://reqres.in/api/users/2";
        String contentType = "application/json";
        Response response = RestAssured.given().contentType(contentType).body(jsonObject.toString()).put(url);
        System.out.println(response.getBody().asString());
        String job = response.jsonPath().get("job");
        String update = response.jsonPath().get("updatedAt");
        System.out.println(job);
        System.out.println(update);

    }


    @Test
    public void TC_03_DELETE(){
        Response response = RestAssured.given().delete("https://reqres.in/api/users/2");
       Object contentType = response.getBody();

        System.out.println(contentType);
    }

}
