package org.example.modules;

import com.google.gson.Gson;
import org.example.pojos.request.Auth;
import org.example.pojos.request.Booking;
import org.example.pojos.request.Bookingdates;
import org.example.pojos.response.Bookingresponse;
import org.example.pojos.response.TokenResponse;

public class PayloadManager {
      Gson gson;
    public String createbookingpayloadgson()
    {
        //This method is returning String as json
        Booking booking = new Booking();
        booking.setFirstname("Urja");
        booking.setLastname("Khattar");
        booking.setDepositpaid(true);
        booking.setTotalprice(2345);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-01-01");
        bookingdates.setCheckout("2025-01-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        //Serialization
        gson = new Gson();
        String jsonStringBooking= gson.toJson(booking);

        return jsonStringBooking;

    }

    //desrialization
    public Bookingresponse bookingresponsejava(String responsestring)
    {
     gson = new Gson();
     Bookingresponse bookingresponse = gson.fromJson(responsestring, Bookingresponse.class);
    return bookingresponse;
    }


    public String setAuthpayload()
    {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        //serialsization
        gson = new Gson();
        String jsonStringauthpayload = gson.toJson(auth);
        return jsonStringauthpayload;

    }

    public String gettokenresponsefromjson(String tokenreponsestring)
    {
        gson = new Gson();
        TokenResponse tokenResponse = gson.fromJson(tokenreponsestring, TokenResponse.class);
        return tokenResponse.getToken();
    }

    public String updatebookinpayload()
    {
        //This method is returning String as json
        Booking booking = new Booking();
        booking.setFirstname("Mritunjay");
        booking.setLastname("Vats");
        booking.setDepositpaid(true);
        booking.setTotalprice(4456);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-01-02");
        bookingdates.setCheckout("2024-01-03");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Lunch");
        //covert object -> json
        gson = new Gson();
        String jsonupdatebookingresponse = gson.toJson(booking);
        return jsonupdatebookingresponse;
    }

     //To validate fields converting json -> object

    public Booking bookingresponseforputrequest(String bookingreponsestring)
    {
        gson = new Gson();
        Booking booking = gson.fromJson(bookingreponsestring, Booking.class);
                return booking;
    }



}
