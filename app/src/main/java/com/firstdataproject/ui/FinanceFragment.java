package com.firstdataproject.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firstdataproject.R;
import com.firstdataproject.adapter.StockAdapter;
import com.firstdataproject.model.Stock;
import com.firstdataproject.rest.ApiClient;
import com.firstdataproject.rest.ApiInterface;
import com.firstdataproject.utils.CommonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Avdhesh AKhani on 10/26/2016.
 */



public class FinanceFragment extends Fragment {

    private static final String TAG = "Stock Fragment";
    private View view;
    private RecyclerView recyclerView;
    private Button btnRequest;
    private TextView tvEmpty;

    private String client = "ig";
    private String stocks = "C,COF,AXP,D,FS,LLOY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stock, container, false);
        setRetainInstance(true);

        setupUI();
        setListener();

        return view;

    }

    private void setListener() {
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CommonUtils.isOnline(getActivity())) {
                    loadData();
                }else {
                    CommonUtils.showToast(getActivity(),"No Internet Connection!");
                }

            }
        });

    }

    private void updateAdapter(ArrayList<Stock> stocksList) {

        if (stocksList.size()>0){
            tvEmpty.setVisibility(View.GONE);
            StockAdapter   adapter = new StockAdapter(stocksList, R.layout.rowlayout_post, getActivity());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    private void parseJson(String json) {

        try {
            json= json.replace("//","").trim();

            JSONArray array = new JSONArray(json);
            ArrayList<Stock> stocksList = new ArrayList<Stock>();

            for (int i=0;i<array.length();i++){

                JSONObject stockObj = array.getJSONObject(i);
                Stock stock = new Stock();

                if (stockObj.has("t")){stock.setT(stockObj.getString("t"));}
                if (stockObj.has("l")){stock.setL(stockObj.getString("l"));}
                if (stockObj.has("c")){stock.setC(stockObj.getString("c"));}

                stocksList.add(stock);

            }

            updateAdapter(stocksList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupUI() {

        btnRequest = (Button) view.findViewById(R.id.btnRequest);
        tvEmpty = (TextView) view.findViewById(R.id.tvEmpty);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }





    private void loadData() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getStocks(client,stocks);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "Success");

                String json = null;
                try {
                    json = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                parseJson(json);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Fail");
            }
        });
    }


}