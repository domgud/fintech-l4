package com.example.fintech_l3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fintech_l3.dataloader.DataLoader;
import com.example.fintech_l3.dto.CurrencyDTO;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DataLoader.DataLoaderListener<CurrencyDTO> {

    private  DataLoader<CurrencyDTO> dataLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataLoader = new DataLoader<>(this, CurrencyDTO.class);
        dataLoader.loadData();
    }

    @Override
    public void onDataLoaded(List<CurrencyDTO> currencies) {
        currencies.forEach(System.out::println);
    }

    @Override
    public void onDataLoadFailed() {
        System.out.println("something went wrong");
    }
}