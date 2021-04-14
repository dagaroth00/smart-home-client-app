package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Router extends AppCompatActivity {
    private static final String TAG = "RouterMain";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference routerRef = db.collection("Router");
    private CollectionReference baseStationRef = db.collection("BaseStation");
    private RouterAdapter adapter;

    private FirebaseAuth mAuth;
String base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        FloatingActionButton floatingActionButton  = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Router.this,NewRouter.class));
            }
        });
        Intent intent = getIntent();
         String str = intent.getStringExtra("message_key");

      /*  {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (str[0] == null) {

                String id = currentUser.getUid(); //signInAccount.getIdToken();



             /*   db.collection("BaseStation")
                        .whereEqualTo("userId", id)
                        .limit(1)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        base = document.getId();
                                      //  str[0] = document.getId()+"";
                                        Log.d(TAG, base + " basee " );
                                    }
                        /*            Log.d(TAG, base + " after for " );

                                    //str[0] = str[0].concat(base);
                                    Log.d(TAG, base + " after for assignmenrt" );   /

                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });





                Log.d(TAG, id+"          id   56655555 => " + base);

                setUpRecyclerView(base.toString());
            } else {
                setUpRecyclerView(str[0]);
            }
        }*/
        if (str == null) {
            str = "6655";
            setUpRecyclerView(str);
        }else {
            setUpRecyclerView(str);
        }
    }

    private void setUpRecyclerView(String str){
        Query query  = routerRef.whereEqualTo("baseStationId",str);
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
               // intent.putExtra("message_key", subStationId);
                intent.putExtra("message_key", documentSnapshot.getId());
                startActivity(intent);
            }
        });
    }

    String getBase(String id){


       /* db.collection("BaseStation")
                .whereEqualTo("userId", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                base = document.getId();
                                Log.d(TAG, base + " basee " );



                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

*/



        CollectionReference docref =db.collection("cities");
                docref.whereEqualTo("capital", true);

                docref.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return base;


    }
    @Override
    protected void onStart() {

        super.onStart();
       // FirebaseUser currentUser = mAuth.getCurrentUser();
        //TextView hidden =  findViewById(R.id.hidden);
       //final hidden = currentUser.getUid().toString();
    adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
    adapter.startListening();
    }
}