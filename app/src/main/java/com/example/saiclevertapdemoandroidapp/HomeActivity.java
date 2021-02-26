//Here also we will put the cleverTap Varibale and initialize the SDK as we did in the MainActivty.java

package com.example.saiclevertapdemoandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    //13. Creating Buttons for the HomeActivity Page
    Button UpdateProfile,AddToCart,ProductView,Charge,AppInbox,Other;
    CleverTapAPI clevertapDefaultInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialize the CleverTap SDK for this Page
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());


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



        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        ProductView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        Charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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



}

