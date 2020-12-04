package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class BaseStation extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference routerRef = db.collection("BaseStation");

    private BaseStationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_station);

        setUpRecyclerView();

    }


    private void setUpRecyclerView(){
        //FirebaseUser user =  mAuth.getCurrentUser();

        ///assert user != null;
        Query query  = routerRef.whereEqualTo("baseStationId","0002");
        ///Query query = routerRef;
        FirestoreRecyclerOptions<BaseStationItem> options = new FirestoreRecyclerOptions.Builder<BaseStationItem>()
                .setQuery(query,BaseStationItem.class)
                .build();
        adapter = new BaseStationAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

       adapter.setOnItemClickListener(new BaseStationAdapter.OnItemClickListener(){

           @Override
           public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
               BaseStationItem baseStationItem = documentSnapshot.toObject(BaseStationItem.class);

               String subStationId = documentSnapshot.getReference().getPath();
               Intent intent = new Intent(getApplicationContext(), Router.class);
               // intent.putExtra("message_key", subStationId);
               intent.putExtra("message_key", documentSnapshot.getId());
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