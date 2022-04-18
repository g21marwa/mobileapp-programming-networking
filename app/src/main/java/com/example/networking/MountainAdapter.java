package com.example.networking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MountainAdapter extends ArrayAdapter<Mountain> {

    public MountainAdapter(@NonNull Context context, ArrayList<Mountain> list){
        super(context, 0, list);
        System.out.println("asd");
    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View counterView, @NonNull ViewGroup parent){
        System.out.println("asd");
        View listItem = counterView;
        if (listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }

        Mountain currentMountain = getItem(pos);

        /*WebView mountainImage = listItem.findViewById(R.id.mountainImg);
        mountainImage.loadUrl(currentMountain.getAuxdata().get("img"));*/
        System.out.println("asd");
        TextView mountainName = listItem.findViewById(R.id.mountainName);
        mountainName.setText(currentMountain.getName());

        return listItem;
    }
}
