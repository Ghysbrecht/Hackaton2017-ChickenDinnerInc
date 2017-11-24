package be.chickendinnerinc.hackaton.hackaton2017;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Thomas on 24/11/2017.
 */

public interface DatabaseService {
    @GET("tasks.json")
    Call<List<Job>> allJobs();

    @GET("tasks/{id}.json")
    Call<Job> jobWithId(
            @Path("id") int id
    );

    @GET("users/{id}.json")
    Call<User> userWithId(
            @Path("id") int id
    );

    @POST("tasks/{id}/accept.json")
    Call<BasicResponse> sendAcceptJob(@Path("id") int id, @Body AcceptJob accept);

}
