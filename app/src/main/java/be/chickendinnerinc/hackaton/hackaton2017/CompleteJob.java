package be.chickendinnerinc.hackaton.hackaton2017;

import com.google.gson.annotations.Expose;


public class CompleteJob {
    @Expose
    private boolean completed;

    CompleteJob(boolean completed){
        this.completed = completed;
    }

    public boolean getCompleted() {
        return completed;
    }
}
