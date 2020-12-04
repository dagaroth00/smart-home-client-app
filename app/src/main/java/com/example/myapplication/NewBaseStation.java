package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class NewBaseStation extends AppCompatActivity {
    EditText baseStationId, baseStationName;
    private String TAG = "New Base Station" ;
    Button button_add;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_base_station);

        mAuth = FirebaseAuth.getInstance();
        baseStationName = (EditText)findViewById(R.id.base_station_name);
        baseStationId = (EditText) findViewById(R.id.base_station_id);
       // FirebaseUser user = auth.getCurrentUser();
        baseStationId = (EditText) findViewById(R.id.base_station_id);


        button_add = findViewById(R.id.Button_Add);
        button_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(!baseStationName.getText().toString().isEmpty()&&(!baseStationId.getText().toString().isEmpty())) {
                    Toast.makeText(NewBaseStation.this, "SubStation Added Successfully " , Toast.LENGTH_LONG).show();
                    TextView hidden = findViewById(R.id.hidden);
                    String uidR = hidden.getText().toString();

                    addBaseStationDetails(uidR);

                } else {
                    baseStationId.setError("Please Enter all details");

                }




            }
        });
    }

     void addBaseStationDetails(String user){
     //    String user =hidden.getText().toString();
         /*if(signInAccount != null){

           user = signInAccount.getId();
            }*/

        Map<String, Object> uData = new HashMap<>();
        uData.put("baseStationId", String.valueOf(baseStationId.getText()));
        uData.put("baseStationName", String.valueOf(baseStationName.getText()));
        uData.put("userId", String.valueOf(user));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("BaseStation").document(String.valueOf(baseStationId.getText()))
                .set(uData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");


//                        baseStationName.setText("");
//                        baseStationId.setText("");
//
//                        startActivity(new Intent(NewBaseStation.this, BaseStation.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        TextView hidden =  findViewById(R.id.hidden);
        hidden.setText(currentUser.getUid().toString());
        //updateUI(currentUser);
    }
}