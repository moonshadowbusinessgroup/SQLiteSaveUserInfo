package com.example.sqlitenote;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Model> dataModelArrayList;
    private Context mContext;
    private ItemClickListener itemClickListener;

    public MyAdapter(Context ctx, ArrayList<Model> dataModelArrayList, ItemClickListener itemClickListener) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
        this.itemClickListener = itemClickListener;
        mContext = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Log.e("onBindViewHolder", "orderNo : " + dataModelArrayList.get(position).getOrder_No() + ",  Status : " + dataModelArrayList.get(position).getStatus());
        holder.orderNoT.setText("Order No : " + dataModelArrayList.get(position).getOrder_No());
        holder.userNameT.setText(dataModelArrayList.get(position).getUser_Name());
        holder.userLocationT.setText(dataModelArrayList.get(position).getUser_Location());
        holder.phoneNumberT.setText(dataModelArrayList.get(position).getPhone_Number());


        if (dataModelArrayList.get(position).getStatus() == 0){
            holder.orderOk.setVisibility(View.VISIBLE);
            holder.orderNotOk.setVisibility(View.VISIBLE);
        }
        else  if (dataModelArrayList.get(position).getStatus() == 1){
            holder.orderOk.setVisibility(View.VISIBLE);
            holder.orderNotOk.setVisibility(View.GONE);
        }
        else  if (dataModelArrayList.get(position).getStatus() == 2)
        {
            holder.orderNotOk.setVisibility(View.VISIBLE);
            holder.orderOk.setVisibility(View.GONE);

        }
        holder.orderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.setStatus(dataModelArrayList.get(position).getOrder_No(), 1,position);

            }
        });

        holder.orderNotOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.setStatus(dataModelArrayList.get(position).getOrder_No(), 2,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView orderNoT, userNameT, userLocationT, phoneNumberT;
        AppCompatImageView orderOk, orderNotOk;

        public MyViewHolder(final View itemView) {
            super(itemView);
            orderNoT = itemView.findViewById(R.id.orderNoT);
            userNameT = itemView.findViewById(R.id.userNameT);
            userLocationT = itemView.findViewById(R.id.userLocationT);
            phoneNumberT = itemView.findViewById(R.id.phoneNumberT);

            orderOk = itemView.findViewById(R.id.orderOk);
            orderNotOk = itemView.findViewById(R.id.orderNotOk);
        }
    }

    public interface ItemClickListener {
        void setStatus(long order_no, long status, int position);
    }
}
