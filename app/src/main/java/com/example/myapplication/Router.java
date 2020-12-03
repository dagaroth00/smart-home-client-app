package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Router extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference routerRef = db.collection("Router");

    private RouterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);

        FloatingActionButton floatingActionButton  = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Router.this,NewRouter.class));
            }
        });

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query  = routerRef.whereEqualTo("baseStationId","0002");
        ///Query query = routerRef;
        FirestoreRecyclerOptions<RouterItem> options = new FirestoreRecyclerOptions.Builder<RouterItem>()
                .setQuery(query,RouterItem.class)
                .build();
        adapter = new RouterAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RouterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                RouterItem routerItem = documentSnapshot.toObject(RouterItem.class);
                Toast.makeText(getApplicationContext(),"position:"+ position +" id :"+documentSnapshot.getId() ,Toast.LENGTH_LONG
                ).show();
                String subStationId = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(getApplicationContext(), Appliances.class);
                intent.putExtra("message_key", subStationId);
                startActivity(intent);
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