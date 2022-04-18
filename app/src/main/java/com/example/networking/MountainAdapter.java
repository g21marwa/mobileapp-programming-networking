package com.example.networking;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {

    private static List<Mountain> localDataSet;
    private static Context activity;
    public MountainAdapter(List<Mountain> dataset, Context context){
        localDataSet = dataset;
        activity = context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d("TAG", "Element " + position + " set.");
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getMyTextView().setText(localDataSet.get(position).getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView myTextView;
        private Mountain clicked;
        public ViewHolder(View view){
            super(view) ;
            myTextView = view.findViewById(R.id.mountainName);
            myTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clicked = localDataSet.get(getAdapterPosition());
                    Intent i = new Intent(activity, MountainInfo.class);
                    i.putExtra("mountain", clicked);
                    activity.startActivity(i);
                }
            });
        }
        public TextView getMyTextView(){
            return myTextView;
        }
    }
}
