package com.example.tp3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    private ArrayList<String> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView titulo;
        public TextView fecha;
        View v;

        public ViewHolder(View a){
            super(a);
            titulo= a.findViewById(R.id.tvTituloItem);
            fecha= a.findViewById(R.id.tvFechaItem);
            v=a;
        }
    }

    public mAdapter(ArrayList<String> adapter){
        mDataSet=adapter;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {

        String recordatorio = mDataSet.get(i);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String[] parts = recordatorio.split(", ");
        viewHolder.fecha.setText(parts[1]);
        viewHolder.titulo.setText(parts[0]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
