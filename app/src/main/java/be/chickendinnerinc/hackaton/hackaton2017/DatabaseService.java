package be.chickendinnerinc.hackaton.hackaton2017;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Thomas on 24/11/2017.
 */

public interface DatabaseService {
    @GET("tasks.json")
    Call<List<Job>> allJobs();

    @GET("tasks/{id}.json")
    Call<List<Job>> jobWithId(
            @Path("id") int id
    );
}
