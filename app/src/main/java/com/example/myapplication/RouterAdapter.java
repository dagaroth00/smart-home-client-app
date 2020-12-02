package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RouterAdapter extends FirestoreRecyclerAdapter<RouterItem , RouterAdapter.RouterHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RouterAdapter(@NonNull FirestoreRecyclerOptions<RouterItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RouterHolder holder, int position, @NonNull RouterItem model) {

        holder.textViewName.setText(model.getName());
        holder.textViewId.setText(model.getRouterId());

    }

    @NonNull
    @Override
    public RouterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.router_item,
                parent,false);

        return new RouterHolder(v);
    }

    class RouterHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewId;

        public RouterHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.router_name);
            textViewId = itemView.findViewById(R.id.router_id);
        }
    }



}