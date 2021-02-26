//Here also we will put the cleverTap Varibale and initialize the SDK as we did in the MainActivty.java

package com.example.saiclevertapdemoandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CleverTapAPI;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements CTInboxListener {

    //13. Creating Buttons for the HomeActivity Page
    Button UpdateProfile,AddToCart,ProductView,Charge,AppInbox,Other;
    CleverTapAPI clevertapDefaultInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize the CleverTap SDK for this Page
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());


        //20.Initializing the In app notification Listner
        //Set the Notification Inbox Listener
        clevertapDefaultInstance.setCTNotificationInboxListener(this);
        //Initialize the inbox and wait for callbacks on overridden methods
        clevertapDefaultInstance.initializeInbox();


        //14. First Here we find the view for all the buttons in the HomePage [HomeActivity.java]
        UpdateProfile = findViewById(R.id.update_profile);
        AddToCart = findViewById(R.id.add_to_cart);
        ProductView = findViewById(R.id.product_view);
        Charge = findViewById(R.id.charged);
        AppInbox = findViewById(R.id.App_Inbox);
        Other = findViewById(R.id.Other);



        //15. Setting OnClickListner events for all the buttons

        UpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //16. Now we are updating the profile via a HashMap , here we will update the address and add a new property Language
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Address", "Chennai");
                profileUpdate.put("Language", "Tamil");
                //As we have initialized the clevertap sdk above, we can use CleverTap Methods
                // Here We push the ProfileUpdate into CleverTap and these properties will be added to our profile.
                clevertapDefaultInstance.pushProfile(profileUpdate);



            }
        });



        ProductView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 17. We can raise a event with properties,and we push the event product viewed

                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product Name", "Casio Chronograph Watch");
                prodViewedAction.put("Category", "Mens Accessories");
                prodViewedAction.put("Price", 59.99);
                prodViewedAction.put("Date", new java.util.Date());

                clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);



            }
        });


        //Here the product that is viewed that is added to the cart
        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product Name", "Casio Chronograph Watch");
                prodViewedAction.put("Category", "Mens Accessories");
                prodViewedAction.put("Price", 59.99);
                prodViewedAction.put("Date", new java.util.Date());

                clevertapDefaultInstance.pushEvent("Add To Cart", prodViewedAction);

            }
        });


        // 18. Charged is a special event, where there can be more than 1 product in the Cart
        // we store the Charged Details in a hashmap, which includes ChargeDetails such as amount, payment mode, charged id [Transaction id]
        // We also store individual product information the hashmap and then we add them in a Arraylist
        // then push the event to clevertap

        Charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Object> chargeDetails = new HashMap<String, Object>();
                chargeDetails.put("Amount", 300);
                chargeDetails.put("Payment Mode", "Credit card");
                chargeDetails.put("Charged ID", 24052013);

                HashMap<String, Object> item1 = new HashMap<String, Object>();
                item1.put("Product category", "books");
                item1.put("Book name", "The Millionaire next door");
                item1.put("Quantity", 1);

                HashMap<String, Object> item2 = new HashMap<String, Object>();
                item2.put("Product category", "books");
                item2.put("Book name", "Achieving inner zen");
                item2.put("Quantity", 1);

                HashMap<String, Object> item3 = new HashMap<String, Object>();
                item3.put("Product category", "books");
                item3.put("Book name", "Chuck it, let's do it");
                item3.put("Quantity", 5);

                ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
                items.add(item1);
                items.add(item2);
                items.add(item3);

                clevertapDefaultInstance.pushChargedEvent(chargeDetails, items);



            }
        });




        AppInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    

    //19.This is used for the AppInbox Messages Callback Functions
    // We implemented the CTInboxListener on the top [HomeActivityClass]

    @Override
    public void inboxDidInitialize() {

    }

    @Override
    public void inboxMessagesDidUpdate() {

    }
}

