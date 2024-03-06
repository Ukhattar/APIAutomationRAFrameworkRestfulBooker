package org.example.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
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


}
