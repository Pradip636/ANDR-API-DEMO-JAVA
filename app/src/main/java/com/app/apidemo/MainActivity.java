package com.app.apidemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apidemo.adapter.CityListAdapter;
import com.app.apidemo.data.ApiClient;
import com.app.apidemo.data.ApiService;
import com.app.apidemo.model.City;
import com.app.apidemo.model.CityResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;

    private ListView listView;
    private TextView tvError;
    private ProgressBar progressBar;

    private CityListAdapter cityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        tvError = findViewById(R.id.tvError);
        progressBar = findViewById(R.id.progressBar);

        apiService = ApiClient.getApiClient().create(ApiService.class);


        getAllCity();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    progressBar.setVisibility(View.VISIBLE);
                    getAllCity();
                    new Handler(Looper.myLooper()).postDelayed(() -> progressBar.setVisibility(View.GONE), 3000);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
        });

    }

    private void getAllCity() {
        try {
            apiService.getData().enqueue(new Callback<CityResponse>() {

                @Override
                public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                    if (response.isSuccessful()) {


                        if (response.body() != null
                                && response.body().getSuccess().equalsIgnoreCase("1")
                                && !response.body().getResponse().isEmpty()) {

                            progressBar.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            tvError.setVisibility(View.GONE);
                            List<City> cityList = response.body().getResponse();


                            cityListAdapter = new CityListAdapter(MainActivity.this, cityList, new CityListAdapter.OnItemTap() {
                                @Override
                                public void onTap(City city) {

                                }
                            });

                            listView.setAdapter(cityListAdapter);

                        } else {
                            progressBar.setVisibility(View.GONE);
                            listView.setVisibility(View.GONE);
                            tvError.setVisibility(View.VISIBLE);
                            tvError.setText("Something went wrong");
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        listView.setVisibility(View.GONE);
                        tvError.setVisibility(View.VISIBLE);
                        tvError.setText("Something went wrong");
                    }
                }

                @Override
                public void onFailure(Call<CityResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText(t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(e.getLocalizedMessage());
        }
    }
}