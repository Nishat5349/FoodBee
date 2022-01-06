package com.cse27.foodbee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.cse27.foodbee.Controller.LoginController;
import com.cse27.foodbee.Controller.ProfileController;
import com.cse27.foodbee.Controller.ProfileControllerInterface;
import com.cse27.foodbee.View.ProfileViewInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity implements ProfileViewInterface {
    private Button updateProfileButton;
    TextView textViewUserName, textViewUserAddress;

    ProfileControllerInterface profileController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileController = new ProfileController(this);

        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        textViewUserAddress = (TextView) findViewById(R.id.textViewUserAddress);
        //textViewUserName.setText("nishat");
        //textViewUserAddress.setText("ju");

        profileController.onProfileReload();

        updateProfileButton = findViewById(R.id.updateProfileButton);
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateProfile();
            }

        });

    }
    public void goToUpdateProfile(){
        Intent intent= new Intent(this, UpdateProfile.class );
        startActivity(intent);
    }

    @Override
    public void onProfileReloadSuccess(String fullName, String address) {
        textViewUserName.setText(fullName);
        textViewUserAddress.setText(address);
        Toast.makeText(this,"Hello !",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProfileReloadError(String message) {
        Toast.makeText(this,message ,Toast.LENGTH_LONG).show();
    }


}