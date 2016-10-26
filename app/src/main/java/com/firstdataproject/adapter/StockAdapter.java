package com.firstdataproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstdataproject.R;
import com.firstdataproject.model.Stock;

import java.util.ArrayList;

/**
 * Created by Avdhesh AKhani on 10/26/2016.
 */


public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder>{
    private ArrayList<Stock> stocksList;
    private int rowLayout;
    private Context context;

    public StockAdapter(ArrayList<Stock> stocksList, int rowLayout, Context context) {
        this.stocksList = stocksList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.tvStockName.setText(stocksList.get(position).getT());
        holder.tvCurentPrice.setText(stocksList.get(position).getL());
        holder.tvChange.setText(stocksList.get(position).getC());

    }

    @Override
    public int getItemCount() {
        return this.stocksList.size();
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder{

        TextView tvStockName,tvCurentPrice,tvChange;
        private ImageView img,imgProfile;
        public StockViewHolder(View v) {
            super(v);
            tvStockName = (TextView) v.findViewById(R.id.tvStockName);
            tvCurentPrice = (TextView) v.findViewById(R.id.tvCurrentPrice);
            tvChange = (TextView) v.findViewById(R.id.tvChange);
        }
    }
}

