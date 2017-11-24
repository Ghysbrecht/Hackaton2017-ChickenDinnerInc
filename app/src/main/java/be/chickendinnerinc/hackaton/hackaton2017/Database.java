package be.chickendinnerinc.hackaton.hackaton2017;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Thomas on 24/11/2017.
 */

public class Database {
    private DatabaseService databaseService;
    private IJobListener listener;

    Database(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        databaseService = retrofit.create(DatabaseService.class);
    }

    public void getAllAvailableJobs(IJobListener endpoint){
        this.listener = endpoint;
        Call<List<Job>> call = databaseService.allJobs();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.body() != null) {
                    listener.populate(response.body());
                } else {
                    Log.e("REST", "HTTP REST Request returned no data to parse");
                }
            }
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("REST", "HTTP REST Request failed: " + t);
            }
        });
    }
}
