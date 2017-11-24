package be.chickendinnerinc.hackaton.hackaton2017;

import com.google.gson.annotations.Expose;

/**
 * Created by Thomas on 24/11/2017.
 */

public class BasicResponse {
    @Expose
    private String result;
    @Expose
    private int status;

    public int getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }
}
