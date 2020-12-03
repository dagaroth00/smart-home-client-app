package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.okhttp.Route;

import java.util.HashMap;
import java.util.Map;

public class NewRouter extends AppCompatActivity {

    EditText baseStationId,subStationId,roomName;
   private String TAG = "NewRouter";
    Button button_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_router);

        subStationId = (EditText)findViewById(R.id.Substation_Id);
        roomName = (EditText) findViewById(R.id.Room_Name);
        baseStationId = (EditText) findViewById(R.id.base_station_id);
        button_add = findViewById(R.id.Button_Add);
      button_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(!subStationId.getText().toString().isEmpty()&&(!baseStationId.getText().toString().isEmpty())&&(!roomName.getText().toString().isEmpty())) {
                    Toast.makeText(NewRouter.this, "SubStation Added Successfully " , Toast.LENGTH_LONG).show();


                    addSubstationDetails();

                } else {
                    baseStationId.setError("Please Enter all details");

                }




            }
        });


    }
    void addSubstationDetails(){

        Map<String, Object> uData = new HashMap<>();
        uData.put("routerId",String.valueOf(subStationId.getText()));
        uData.put("baseStationId", String.valueOf(baseStationId.getText()));
        uData.put("name", String.valueOf(roomName.getText()));
        uData.put("device1", "on");
        uData.put("device2", "on");
        uData.put("device3", "on");
        uData.put("device4", "on");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Router").document(String.valueOf(subStationId.getText()))
                .set(uData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        subStationId.setText("");
        baseStationId.setText("");
        roomName.setText("");

        startActivity(new Intent(NewRouter.this, Router.class));
    }
}