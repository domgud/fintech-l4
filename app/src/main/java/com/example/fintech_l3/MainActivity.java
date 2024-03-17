package com.example.fintech_l3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fintech_l3.dataloader.DataLoader;
import com.example.fintech_l3.dataloader.DataLoaderListener;
import com.example.fintech_l3.dto.CurrencyDTO;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataLoaderListener<CurrencyDTO> {

    private  DataLoader<CurrencyDTO> dataLoader;
    private ListView currencyListView;
    private ProgressBar loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadingBar = findViewById(R.id.loadingBar);
        dataLoader = new DataLoader<>(this, CurrencyDTO.class);
        dataLoader.loadData();
        currencyListView = findViewById(R.id.currencyListView);

    }

    @Override
    public void onDataLoaded(List<CurrencyDTO> currencies) {
        ArrayAdapter<CurrencyDTO> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, currencies);
        currencyListView.setAdapter(adapter);
        loadingBar.setVisibility(ListView.GONE);
    }

    @Override
    public void onDataLoadFailed() {
        Toast.makeText(this, R.string.loading_error,
                Toast.LENGTH_SHORT).show();
        loadingBar.setVisibility(ListView.GONE);
    }


}