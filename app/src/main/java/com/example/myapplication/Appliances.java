package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Appliances extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference appliancesRef = db.collection("Appliances");
    private CollectionReference routerRef = db.collection("Router");

    private AppliancesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances);


        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");

TextView textView = findViewById(R.id.room);
textView.setText(str);
        setUpRecyclerView(str);
    }


    private void setUpRecyclerView(String str){
        Query query  = appliancesRef.whereEqualTo("routerId",str);
       // Query query  = routerRef.whereEqualTo("baseStationId","0002");
        ///Query query = routerRef;
        FirestoreRecyclerOptions<AppliancesItem> options = new FirestoreRecyclerOptions.Builder<AppliancesItem>()
                .setQuery(query,AppliancesItem.class)
                .build();
        adapter = new AppliancesAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AppliancesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                AppliancesItem appliancesItem= documentSnapshot.toObject(AppliancesItem.class);
                Toast.makeText(getApplicationContext(),"position:"+ position +" id :"+documentSnapshot.getId() ,Toast.LENGTH_LONG
                ).show();
                if(appliancesItem.getStatus().equals("on")) {
                    documentSnapshot.getReference().update("status","off");
                } else
                {
                    documentSnapshot.getReference().update("status", "on");
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }
}