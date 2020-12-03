package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class AppliancesAdapter extends FirestoreRecyclerAdapter<AppliancesItem , AppliancesAdapter.AppliancesHolder> {

    private OnItemClickListener listener;


    public AppliancesAdapter(@NonNull FirestoreRecyclerOptions<AppliancesItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AppliancesAdapter.AppliancesHolder holder, int position, @NonNull AppliancesItem model) {

        holder.textViewName.setText(model.getDeviceName());
        holder.status.setText(model.getStatus());
        holder.textViewId.setText(model.getRouterId());

    }

    @NonNull
    @Override
    public AppliancesAdapter.AppliancesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.apliances_item,
                parent,false);
        return new AppliancesHolder(v);


    }

    class AppliancesHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewId;
        TextView status;
        public AppliancesHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.appliance_name);
            textViewId = itemView.findViewById(R.id.appliances_id);
            status = itemView.findViewById(R.id.appliance_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position) , position);
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot , int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener = listener;
    }


}
