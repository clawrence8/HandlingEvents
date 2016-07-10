package com.gitmad.handleevents;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView namePrompt;
    private EditText nameEditText;
    private Button xmlButton;
    private Button javaButton;
    private Button toastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the UI objects from the activity_main.xml file
        namePrompt = (TextView) findViewById(R.id.name_prompt_textview);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        xmlButton = (Button) findViewById(R.id.xml_button);
        javaButton = (Button) findViewById(R.id.java_button);
        toastButton = (Button) findViewById(R.id.toast_button);

        javaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract the user's name from the text box and save it in a variable
                String name = nameEditText.getText().toString();
                // The Log class is very handy when debugging. To make sure you have the correct
                // value in your variable, log the username to the Android Monitor here with Log.i()
                Log.i("username", name);
                // Create a new intent that will take the user from this activity to OtherActivity
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                // To pass data from one class to another, we use "extras". We want OtherActivity
                // to know what the user's name is, so add a string extra to the intent.
                intent.putExtra("name", name);
                // using the intent you just created, start a new instance of OtherActivity.
                startActivity(intent);
            }
        });

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make a toast that welcomes the user based on what they enter in the text box.
                //Once the toast is made, don't forget to show it!
                String name = nameEditText.getText().toString();
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Using newConfig, determine if the phone is in portrait or landscape mode and print
        // a toast message to the user telling them which orientation the phone is in.
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(MainActivity.this, "Your orientation is portrait", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(MainActivity.this, "Your orientation is landscape", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /*
     * This method gets called when the xmlButton is selected
     */
    public void xmlSubmit(View view) {
        // Extract the user's name from the text box and save it in a variable
        String name = nameEditText.getText().toString();
        // The Log class is very handy when debugging. To make sure you have the correct
        // value in your variable, log the username to the Android Monitor here with Log.i()
        Log.i("username", name);
        // Create a new intent that will take the user from this activity to OtherActivity
        Intent intent = new Intent(MainActivity.this, OtherActivity.class);
        // To pass data from one class to another, we use "extras". We want OtherActivity
        // to know what the user's name is, so add a string extra to the intent.
        intent.putExtra("name", name);
        // using the intent you just created, start a new instance of OtherActivity.
        startActivity(intent);
    }

    // Extra Challenge: Prevent any the buttons from working if the user does not enter their name
}
