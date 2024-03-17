package com.example.fintech_l3.dataloader;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fintech_l3.json.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DataLoader<T> {
    private static final String TAG = "DataLoader";
    private static final String API_URL = "https://www.floatrates.com/daily/usd.json";

    private final DataLoaderListener<T> listener;

    private final JsonParser<T> jsonParser;

    public DataLoader(DataLoaderListener<T> listener, Class<T> classType) {
        this.listener = listener;
        this.jsonParser = new JsonParser<>(classType);
    }

    public void loadData() {
        new FetchDataTask().execute();
    }

    private class FetchDataTask extends AsyncTask<Void, Void, List<T>> {
        @Override
        protected List<T> doInBackground(Void... voids) {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                String jsonString = readJsonStringFromStream(urlConnection.getInputStream());
                return jsonParser.parseStringAsList(jsonString);
            } catch (IOException e) {
                Log.e(TAG, "Error fetching data", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<T> currencies) {
            if (currencies != null && !currencies.isEmpty()) {
                listener.onDataLoaded(currencies);
            } else {
                listener.onDataLoadFailed();
            }
        }
    }

    private String readJsonStringFromStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }

    public interface DataLoaderListener<T> {
        void onDataLoaded(List<T> currencies);
        void onDataLoadFailed();
    }
}
