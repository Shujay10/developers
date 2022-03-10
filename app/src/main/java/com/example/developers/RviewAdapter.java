package com.example.developers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewAdapter.ExampleHolder> {

    ArrayList<Data> list;

    public RviewAdapter(ArrayList<Data> list) {
        this.list = list;
    }

    public class ExampleHolder extends RecyclerView.ViewHolder {

        TextView subject;

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.inpSub);

        }
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rview_list,parent,false);

        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {
            String name = list.get(position).getName();
            holder.subject.setText(name);

        try {
            holder.subject.setText(list.get(position).getName());
        }catch (Exception e){
            //AxeltaLogger.err("error>>>"+e);
            System.out.println(e);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
