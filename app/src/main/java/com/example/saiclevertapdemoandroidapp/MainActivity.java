package com.example.saiclevertapdemoandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    // 1. Creating Variables for the text field and signup button and CleverTap API

    EditText email,phone,name,username,address;
    Button SignUp;
    CleverTapAPI clevertapDefaultInstance;


    //4. As the data we enter in the fields will be a String, we create String Variables
    String textEmail,textPhone,textName,textUsername,textAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 0. Below we are Initializing the CleverTap SDK
        // CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        // This is the API that we get from CleverTap

        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());

        // 2. Now initializing the above variables, we use findviewbyid to find a particular view and we will get all the views

        email = findViewById(R.id.text_email_id);
        phone = findViewById(R.id.text_phone);
        name = findViewById(R.id.text_name);
        username = findViewById(R.id.text_user_name);
        address = findViewById(R.id.text_address);
        SignUp = findViewById(R.id.button_signup);


        // 3. Now we put a Setoncliclistener on the signup button, when the button is clicked, then the
        // OnClick Method is called and the data is passed to CleverTap

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         //5. We use the string variables, here to read the data and send it to the CleverTap API [Post Request]

         textEmail = email.getText().toString();
         textPhone = phone.getText().toString();
         textName  = name.getText().toString();
         textUsername = username.getText().toString();
         textAddress = address.getText().toString();


         // 6. We can add information to userProfile [OnUserLogin] Data
         // We use a hashmap to store the data

                // each of the below mentioned fields are optional if set, these populate demographic information in the Dashboard

                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

                profileUpdate.put("Name", textName);                  // String
                profileUpdate.put("Identity", textUsername);                    // String or number
                profileUpdate.put("Email", textEmail);               // Email address of the user
                profileUpdate.put("Phone", textPhone);                 // Phone (with the country code, starting with +)
                profileUpdate.put("Sign Up date", new Date());                       // Date of Birth. Set the Date object to the appropriate value first
                profileUpdate.put("address", textAddress);                    //an abbreviation such as "PST", a full name such as "America/Los_Angeles",
                //or a custom ID such as "GMT-8:00"
                profileUpdate.put("Photo", "https://www.mediavillage.com/media/articles/blackpanther.jpg.550x380_q85_box-0%2C23%2C640%2C468_crop.jpg");    // URL to the Image

                // optional fields. controls whether the user will be sent email, push etc.

                profileUpdate.put("MSG-email", false);                      // Disable email notifications
                profileUpdate.put("MSG-push", true);                        // Enable push notifications
                profileUpdate.put("MSG-sms", false);                        // Disable SMS notifications
                profileUpdate.put("MSG-dndPhone", true);                  // Opt out phone                                                                    number from SMS                                                                  notifications
                profileUpdate.put("MSG-dndEmail", true);                  // Opt out phone                                                                    number from SMS                                                                  notifications


               // 7. We use the CleverTap Default Instance to push the profile
               clevertapDefaultInstance.pushProfile(profileUpdate);

               //8. Giving the User a Message after clicking the button, via a Toast Method
                Toast.makeText(MainActivity.this, "SignUp Successful" , Toast.LENGTH_LONG).show();




            }
        });

    }
}