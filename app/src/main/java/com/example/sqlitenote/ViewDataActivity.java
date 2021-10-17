package com.example.sqlitenote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private DBHelper dbHelper;
    private ArrayList<Model> orderList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        dbHelper = new DBHelper(this);
        orderList.clear();
        orderList.addAll(dbHelper.getAllData());
        recyclerView = findViewById(R.id.recycler);
        if (!orderList.isEmpty()) {
            myAdapter = new MyAdapter(getApplicationContext(),orderList, this);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        } else {
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setStatus(long order_no, long status,int position) {
        Log.e("setStatus", "orderNo : " + order_no + ",  Status : " + status);
        dbHelper.updateData(order_no, status);
        orderList.get(position).setStatus(status);
        myAdapter.notifyDataSetChanged();
    }
}