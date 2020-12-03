package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Appliances extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances);


        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        TextView textView = findViewById(R.id.meratext);
        textView.setText(str);

    }
}