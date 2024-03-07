package org.example.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.example.pojos.response.Bookingresponse;
import org.testng.annotations.BeforeTest;

public class Basetest {
    //to use it outside the package it should be public

   public  RequestSpecification requestSpecification;
   public Response response;
   public  PayloadManager payloadManager;
   public ValidatableResponse validatableResponse;
   public AssertActions assertActions;


    @BeforeTest
    public void setconfig()
    {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();


                requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.base_url)
                        .addHeader("Content-Type","application/json")
                .build().log().all();
    }


    public String gettoken() {
        requestSpecification = RestAssured.given().baseUri(APIConstants.base_url).
                basePath(APIConstants.gettoken_url).contentType(ContentType.JSON).
                body(payloadManager.setAuthpayload());
        response = requestSpecification.when().post();
        validatableResponse = response.then();

        String token = payloadManager.gettokenresponsefromjson(response.asString());
        validatableResponse.statusCode(200);
        //System.out.println(token);
        return token;


    }


}
