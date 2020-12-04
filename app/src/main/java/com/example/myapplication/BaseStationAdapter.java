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

public class BaseStationAdapter extends FirestoreRecyclerAdapter<BaseStationItem, BaseStationAdapter.BaseStationHolder> {

    private BaseStationAdapter.OnItemClickListener listener;

    public BaseStationAdapter(@NonNull FirestoreRecyclerOptions<BaseStationItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BaseStationHolder holder, int position, @NonNull BaseStationItem model) {

        holder.textViewName.setText(model.getBaseStationName());
        holder.textViewId.setText(model.getBaseStationId());

    }

    @NonNull
    @Override
    public BaseStationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.basestation_itemm,parent,false);
        return new BaseStationHolder(v);
    }



    class BaseStationHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewId;
        public BaseStationHolder(View itemView){
            super(itemView);
            textViewName = itemView.findViewById(R.id.basestation_name);
            textViewId = itemView.findViewById(R.id.basestation_id);

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
    public void setOnItemClickListener(BaseStationAdapter.OnItemClickListener listener){
        this.listener = listener;

}

}
