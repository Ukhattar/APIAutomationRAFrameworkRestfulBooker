package org.example.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertActions;
import org.example.base.Basetest;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.example.pojos.response.Bookingresponse;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TCCreateBooking extends Basetest {

    //URI
    //Header


    @Test
    @Owner("Urja Khattar")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC #1: Verify that the booking is created successfully")
    public void testcreatebooking(){

        requestSpecification.basePath(APIConstants.updateandcreatebookingurl);

        // we will provide cookies and body here
        //body Pojo class,need to create payloadmanager object
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createbookingpayloadgson()).post();

         validatableResponse = response.then().log().all();

        Bookingresponse bookingresponse = payloadManager.bookingresponsejava(response.asString());
        validatableResponse.statusCode(200);

        //asertj
        assertThat(bookingresponse.getBookingid()).isNotNull();
        assertThat(bookingresponse.getBooking().getFirstname()).isEqualTo("Urja");
        assertThat(bookingresponse.getBooking().getDepositpaid()).isTrue();

        //TestNg assertion
        assertActions.verifyStatusCode(response);
        System.out.println(bookingresponse.getBookingid());
        System.out.println("Get token -> " + gettoken());


    }






}
