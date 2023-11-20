package com.example.springrest.getapi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
@Repository
public class GetIMPL implements GetDAO {
    @Autowired
    private GetBO getBO;
    private String acctoken;
    private static RequestSpecification req;
    private static ResponseSpecification respec;

    @BeforeClass
    public void setUp() {
        getBO = new GetBO(); // or however you create an instance of getBO
    }

    @Test
    public String  getCredtoken() {
        HashMap<String, String> login = new HashMap<>();
        login.put("emailId", getBO.getEmail());
        login.put("password", getBO.getPassword());
        login.put("grantType", getBO.getGrantType());
        login.put("refreshToken", getBO.getRefreshToken());

        RestAssured.baseURI = getBO.getBaseURI();
        signinBO.Root response = given().log().all()
                .header("x-api-version", getBO.getXApiVersion())
                .header("channel-id", getBO.getChannelId())
                .header("Content-Type", getBO.getContentType())
                .body(login)
                .when()
                .post("/core/auth/sign-in")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .extract()
                .response()
                .as(signinBO.Root.class);
        acctoken = response.getData().accessToken;
        System.out.println(acctoken);


        // Build request specification
        req = new RequestSpecBuilder()
                .setBaseUri(getBO.getBaseURI())
                .addHeader("x-api-version", getBO.getXApiVersion())
                .addHeader("channel-id", String.valueOf(getBO.getChannelId()))
                .addHeader("x-fi-access-token", acctoken)
                .setContentType(ContentType.JSON)
                .build()
                .log()
                .all();

        // Build response specification
        respec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RequestSpecification fearture = given().spec(req);
        Response featureResponse = fearture.when().get("/core/features")
                .then().log().all().spec(respec)
                .extract().response();

        RequestSpecification userprofile = given().spec(req);
        Response userProfileResponse = userprofile.when().get("/core/user-profile")
                .then().log().all().spec(respec)
                .extract().response();
        String featureResponseBody = featureResponse.getBody().prettyPrint();
        String userProfileResponseBody = userProfileResponse.getBody().prettyPrint();

        String combinedResponse = "Feature API:\n" + featureResponseBody + "\n\nUser API:\n" + userProfileResponseBody;

        return combinedResponse;
    }
    @Override
    public String getTokenByemail(String email, String password) {
        HashMap<String, String> login = new HashMap<>();
        login.put("emailId", email);
        login.put("password", password);
        login.put("grantType", getBO.getGrantType());
        login.put("refreshToken", getBO.getRefreshToken());

        RestAssured.baseURI = getBO.getBaseURI();
        signinBO.Root response = given().log().all()
                .header("x-api-version", getBO.getXApiVersion())
                .header("channel-id", getBO.getChannelId())
                .header("Content-Type", getBO.getContentType())
                .body(login)
                .when()
                .post("/core/auth/sign-in")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .extract()
                .response()
                .as(signinBO.Root.class);
        acctoken = response.getData().accessToken;
        System.out.println(acctoken);


        // Build request specification
        req = new RequestSpecBuilder()
                .setBaseUri(getBO.getBaseURI())
                .addHeader("x-api-version", getBO.getXApiVersion())
                .addHeader("channel-id", String.valueOf(getBO.getChannelId()))
                .addHeader("x-fi-access-token", acctoken)
                .setContentType(ContentType.JSON)
                .build()
                .log()
                .all();

        // Build response specification
        respec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RequestSpecification fearture = given().spec(req);
        Response featureResponse = fearture.when().get("/core/features")
                .then().log().all().spec(respec)
                .extract().response();

        RequestSpecification userprofile = given().spec(req);
        Response userProfileResponse = userprofile.when().get("/core/user-profile")
                .then().log().all().spec(respec)
                .extract().response();
        String featureResponseBody = featureResponse.getBody().prettyPrint();
        String userProfileResponseBody = userProfileResponse.getBody().prettyPrint();

        String combinedResponse = "Feature API:\n" + featureResponseBody + "\n\nUser API:\n" + userProfileResponseBody;

        return combinedResponse;

    }


}