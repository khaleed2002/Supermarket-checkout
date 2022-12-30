package com.example.supermarket_checkout;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String name = modelList.get(position).getmName();
        String description = modelList.get(position).getmDetail();
        int images = modelList.get(position).getmPhoto();

        holder.mName.setText(name);
        holder.mDescription.setText(description);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    // here we will find the views on which we will inflate our data

        TextView mName, mDescription;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mDescription = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

            // lets get the position of the view in list and then work on it

            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent = new Intent(context, InfoActivity.class);
                context.startActivity(intent);
            } else if(position ==1){
                Intent intent2 = new Intent(context, CheckenActivity.class);
                context.startActivity(intent2);
            } else if(position ==2){
                Intent intent2 = new Intent(context, DairymilkActivity.class);
                context.startActivity(intent2);
            } else if(position ==3){
                Intent intent3 = new Intent(context, CheeseActivity.class);
                context.startActivity(intent3);
            } else if(position ==4){
                Intent intent4 = new Intent(context, PresidentTriangleActivity.class);
                context.startActivity(intent4);
            } else if(position ==5){
                Intent intent5 = new Intent(context, DoritosActivity.class);
                context.startActivity(intent5);
            } else if(position ==6){
                Intent intent6 = new Intent(context, AlmaraiYogurtActivity.class);
                context.startActivity(intent6);
            } else if(position ==7){
                Intent intent7 = new Intent(context, ChipsyActivity.class);
                context.startActivity(intent7);
            }


        }

    }

}