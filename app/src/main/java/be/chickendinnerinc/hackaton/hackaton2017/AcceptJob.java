package be.chickendinnerinc.hackaton.hackaton2017;

import com.google.gson.annotations.Expose;

/**
 * Created by Thomas on 24/11/2017.
 */

public class AcceptJob {
    @Expose
    private int accepted_by;

    AcceptJob(int accepted_by){
        this.accepted_by = accepted_by;
    }

    public int getAccepted_by() {
        return accepted_by;
    }
}
