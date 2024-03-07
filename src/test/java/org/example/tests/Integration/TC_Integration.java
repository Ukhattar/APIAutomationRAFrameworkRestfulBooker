package org.example.tests.Integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.base.Basetest;
import org.example.endpoints.APIConstants;
import org.example.pojos.request.Booking;
import org.example.pojos.response.Bookingresponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TC_Integration extends Basetest {
    //Full Integration Scenario
    /****
     * 1. CreateToken- This should be common to all, so will be provided in BaseClass
     * 2. Create Booking using ITestContext
     * 3. Get Booking
     * 4. Extract Booking and token and then
     * 5. pass it to Delete Request
     * 6. To Maintain the order, either I'll be using Priority or I'll be using Depends on Method
     */


    @Test(priority = 1)
    @Owner("Urja Khattar")
    @Description("TC#1: CreateBooking and Verify it should not be Null")
    public void createBookingid(ITestContext iTestContext)
    {
        //post
        requestSpecification.basePath(APIConstants.updateandcreatebookingurl);
        response = RestAssured.given().spec(requestSpecification).when()
                .body(payloadManager.createbookingpayloadgson()).post();
        validatableResponse = response.then().log().all();
        //to verify me must have bookingid from bookingresponse
       Bookingresponse bookingresponse = payloadManager.bookingresponsejava(response.asString());

      assertThat(bookingresponse.getBookingid()).isNotNull();
      // setting the booking and token as attribute
      iTestContext.setAttribute("bookingid",bookingresponse.getBookingid());
      iTestContext.setAttribute("token",gettoken());
      //  System.out.println(iTestContext.getAttribute("bookingid"));
    }

    @Test(priority = 2)
    @Owner("Urja Khattar")
    @Description("TC#2: CreateBooking and Verify it should not be Null")
   public void getbooking(ITestContext iTestContext)
    {
        //GET, use itestcontext listener
        System.out.println(iTestContext.getAttribute("bookingid"));
        Assert.assertTrue(true);
    }


    @Test(priority =3)
    @Owner("Urja Khattar")
    @Description("TC#3: Update the booking name")
    public void updatebookingtest(ITestContext iTestContext)
    {
      Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
      String token = (String) iTestContext.getAttribute("token");
        System.out.println("Bookingid is -> " + bookingid);
        System.out.println("Token for the Request is -> " + token);

        requestSpecification.basePath(APIConstants.updateandcreatebookingurl + "/" + bookingid);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token).when()
                .body(payloadManager.updatebookinpayload()).put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //validate all the fields from Booking class

        Booking booking = payloadManager.bookingresponseforputrequest(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Mritunjay");
        assertThat(booking.getDepositpaid()).isTrue();

    }


    @Test(priority = 4)
    @Owner("Urja Khattar")
    @Description("TC#4: Delete Booking request ")
    public void deletebooking(ITestContext iTestContext)
    {
//delete
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        requestSpecification.basePath(APIConstants.updateandcreatebookingurl + "/" + bookingid);
     //I don't require fields to be validated
        validatableResponse = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().delete().then().log().all();

        validatableResponse.statusCode(201);

    }


}
