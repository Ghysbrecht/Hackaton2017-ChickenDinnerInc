package be.chickendinnerinc.hackaton.hackaton2017;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
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
    private IStreetListener Slistener;

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

    public void getJobsAcceptedBy(IJobListener endpoint, int userid){
        this.listener = endpoint;
        Call<List<Job>> call = databaseService.acceptedJobByUser(userid);
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

    public void getCreatedJobsBy(IJobListener endpoint, int userid){
        this.listener = endpoint;
        Call<List<Job>> call = databaseService.ownedJobsBy(userid);
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

    public void completeJob(int jobId){
        Call<BasicResponse> response = databaseService.completeJob(jobId, new CompleteJob(true));
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

    public void deleteJob(int jobId){
        Call<ResponseBody> response = databaseService.deleteJob(jobId);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.body() != null){
                    Log.e("THUMP","It did work, here is the info: " + response.body());

                }
                else Log.e("THUMP", "No body :(");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("THUMP", "Network error?");
            }
        });
    }

    public void createJob(Job job){
        Call<BasicResponse> response = databaseService.createJob(job);
        response.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {
                if(response.body() != null){
                    Log.e("THUMP","It did work, here is the info: " + response.body());

                }
                else Log.e("THUMP", "No body :(");
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.e("THUMP", "Network error?");
            }
        });
    }

    public void getStreets(IStreetListener endpoint){
        this.Slistener = endpoint;
        Call<List<String>> call = databaseService.getStreets();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body() != null) {
                    Slistener.populate(response.body());
                } else {
                    Log.e("REST", "HTTP REST Request returned no data to parse");
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("REST", "HTTP REST Request failed: " + t);
            }
        });
    }

    public void getCompletedJobsBy(IJobListener endpoint, int userid){
        this.listener = endpoint;
        Call<List<Job>> call = databaseService.getJobsCompletedBy(userid);
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
