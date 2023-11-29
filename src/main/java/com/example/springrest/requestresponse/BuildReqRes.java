package com.example.springrest.requestresponse;

import com.example.springrest.getapi.GetBO;
import com.example.springrest.reporting.ExtendReporterManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;

public class BuildReqRes {

    private static GetBO getBO;

    @BeforeClass
    public void setUp() {
        getBO = new GetBO(); // or however you create an instance of getBO
    }


    private static RequestSpecification getRequestSpecification(){
        return  new RequestSpecBuilder().setBaseUri(getBO.getBaseURI())
                .addHeader("x-api-version", getBO.getXApiVersion())
                .addHeader("channel-id", String.valueOf(getBO.getChannelId()))
                .addHeader("x-fi-access-token", "")
                .setContentType(ContentType.JSON)
                .build()
                .log()
                .all();

    }

    private static ResponseSpecification getResponseSpecification(){
        return  new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build()
                .log()
                .all();

    }
    public static void printRequestSpecification(RequestSpecification requestSpecification){
        QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(requestSpecification);
        ExtendReporterManager.logInfoDetails("BaseURI " +queryableRequestSpecification.getBasePath());
        ExtendReporterManager.logInfoDetails("Method : " +queryableRequestSpecification.getMethod());
        ExtendReporterManager.logInfoDetails("URI :" +queryableRequestSpecification.getURI());
        ExtendReporterManager.logInfoDetails("BODY Payload :" +queryableRequestSpecification.getBody());

    }
    public static void printResponseSpecification(ResponseSpecification responseSpecification){

     //   ExtendReporterManager.logInfoDetails("BaseURI " +responseSpecification.);


    }

}
