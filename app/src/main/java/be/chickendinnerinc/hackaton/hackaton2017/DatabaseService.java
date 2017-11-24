package be.chickendinnerinc.hackaton.hackaton2017;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Thomas on 24/11/2017.
 */

public interface DatabaseService {
    //Get all jobs (That are not completed or already in progress)
    @GET("tasks.json")
    Call<List<Job>> allJobs();

    //Get a job with a specific ID
    @GET("tasks/{id}.json")
    Call<Job> jobWithId(
            @Path("id") int id
    );

    //Get jobs accepted by a user
    @GET("users/{id}/tasks/accepted.json")
    Call<List<Job>> acceptedJobByUser(
            @Path("id") int id
    );

    //Get jobs owned by a user
    @GET("users/{id}/tasks.json")
    Call<List<Job>> ownedJobsBy(
            @Path("id") int id
    );

    //Get a user
    @GET("users/{id}.json")
    Call<User> userWithId(
            @Path("id") int id
    );

    //Accept a Job
    @POST("tasks/{id}/accept.json")
    Call<BasicResponse> sendAcceptJob(@Path("id") int id, @Body AcceptJob accept);

    //Delete a job
    @DELETE("tasks/{id}")
    Call<ResponseBody> deleteJob(@Path("id") int id);

    //Complete a job
    @POST("tasks/{id}/complete.json")
    Call<BasicResponse> completeJob(@Path("id") int id, @Body CompleteJob accept);

    //Create a job
    @POST("tasks")
    Call<BasicResponse> createJob(@Body Job job);
}
