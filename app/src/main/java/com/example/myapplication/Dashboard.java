package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView name = findViewById(R.id.Uname);
        ImageView dp = (ImageView) findViewById(R.id.dp);
        CardView station , router , users;
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount == null){

            Intent i2 = new Intent(Dashboard.this,MainActivity.class);
            startActivity(i2);
        }


        station = (CardView) findViewById(R.id.station);
        router = (CardView) findViewById(R.id.router);
        users = (CardView) findViewById(R.id.users);
        station.setOnClickListener(this);
        router.setOnClickListener(this);
        users.setOnClickListener(this);

        if(signInAccount != null) {
            name.setText(signInAccount.getDisplayName());
           /* String  url = signInAccount.getPhotoUrl().toString();
            if(uri != null){
                Log.d("dp", url);

            }
            else Log.d("dp","failed");*/
        }

        }


    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        Intent i ;
        switch(v.getId()){

            case R.id.station :
                Toast.makeText(getApplicationContext(), "station",Toast.LENGTH_LONG).show();
                i = new Intent(this , BaseStation.class);
                break;
            case R.id.router :
                Toast.makeText(getApplicationContext(), "router",Toast.LENGTH_LONG).show();
                i = new Intent(this,Router.class);
                break;
            case R.id.users :
                Toast.makeText(getApplicationContext(), "users",Toast.LENGTH_LONG).show();
                i = new Intent(this, Users.class);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getTag());
        }
        startActivity(i);
        //Toast.makeText(getApplicationContext(), String.valueOf(v.getId()),Toast.LENGTH_LONG).show();

    }
}
