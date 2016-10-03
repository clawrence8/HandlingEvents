package com.gitmad.handleevents;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OtherActivity extends AppCompatActivity {

    private TextView welcomeText;
    private ImageView profilePic;
    private Button cameraButton;
    private static final int SELECT_PICTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        // Get the intent that triggered this activity and save the user's name in a variable
        String name = getIntent().getStringExtra("name");
        // Initialize the UI objects from activity_other.xml
        welcomeText = (TextView) findViewById(R.id.welcome_textview);
        welcomeText.setText("Welcome: " + name.trim() + "!");
        profilePic = (ImageView) findViewById(R.id.profile_pic);
        cameraButton = (Button) findViewById(R.id.camera_button);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent that will access the user's gallery
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Use startActivityForResult to launch the intent and open the gallery
                // once the picture is selected, we return to OtherActivity in the onActivityResult
                // callback
                startActivityForResult(galleryIntent, SELECT_PICTURE);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Using newConfig, determine if the phone is in portrait or landscape mode and print
        // a toast message to the user telling them which orientation the phone is in.
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(OtherActivity.this, "Your orientation is portrait", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(OtherActivity.this, "Your orientation is landscape", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the request code matches the one from your intent AND the result was ok AND the data
        // is not null, then grab the image and display it to the image view
        if (requestCode == SELECT_PICTURE && (resultCode == RESULT_OK) && data != null) {
            // Get the URI of the data
            Uri selectedImageUri = data.getData();
            //create an empty bitmap variable that will attempt to store the photo
            Bitmap bm = null;
            try {
                // Try using MediaStore.Images.Media methods to create a bitmap from the photo
                bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                // display the bitmap on the image view
                profilePic.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                Toast.makeText(OtherActivity.this, "Picture too large", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
