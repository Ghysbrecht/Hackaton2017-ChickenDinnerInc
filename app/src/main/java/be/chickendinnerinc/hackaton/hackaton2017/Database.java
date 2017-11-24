package be.chickendinnerinc.hackaton.hackaton2017;

import android.util.Log;

import java.util.ArrayList;
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
    private IUserListener Ulistener;

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

    public void getJobWithId(IJobListener endpoint, int id){
        this.listener = endpoint;
        Call<Job> call = databaseService.jobWithId(id);
        call.enqueue(new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                if (response.body() != null) {
                    List<Job> jobs = new ArrayList<Job>();
                    jobs.add(response.body());
                    listener.populate(jobs);
                } else {
                    Log.e("REST", "HTTP REST Request returned no data to parse");
                }
            }
            @Override
            public void onFailure(Call<Job> call, Throwable t) {
                Log.e("REST", "HTTP REST Request failed: " + t);
            }
        });
    }

    public void getUserWithId(IUserListener endpoint, int id){
        this.Ulistener = endpoint;
        Call<User> call = databaseService.userWithId(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    Ulistener.populate(response.body());
                } else {
                    Log.e("REST", "HTTP REST Request returned no data to parse");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("REST", "HTTP REST Request failed: " + t);
            }
        });
    }

    public void acceptJob(int jobId, int userId){
        Call<BasicResponse> response = databaseService.sendAcceptJob(jobId, new AcceptJob(userId));
        response.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {
                if(response.body() != null){
                    Log.e("THUMP","It did work, here is the info: " + response.body().getStatus() );

                }
                else Log.e("THUMP", "No body :(");
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.e("THUMP", "Network error?");
            }
        });
    }

}
