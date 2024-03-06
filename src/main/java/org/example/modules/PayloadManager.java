package org.example.modules;

import com.google.gson.Gson;
import org.example.pojos.request.Booking;
import org.example.pojos.request.Bookingdates;
import org.example.pojos.response.Bookingresponse;

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



}
