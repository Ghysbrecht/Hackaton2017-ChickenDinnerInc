package be.chickendinnerinc.hackaton.hackaton2017;

/**
 * Created by Thomas on 24/11/2017.
 */

import com.google.gson.annotations.Expose;


public class Job {


    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String location;
    @Expose
    private int credits_to_earn;
    @Expose
    private boolean completed;
    @Expose
    private String tags;
    @Expose
    private int owner;
    @Expose
    private int acceptor;
    @Expose
    private String created_at;
    @Expose
    private String updated_at;


    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getLocation(){
        return location;
    }

    public int getCredits_to_earn(){
        return credits_to_earn;
    }

    public boolean getCompleted(){
        return completed;
    }

    public String getTags(){
        return tags;
    }

    public int getAcceptor(){
        return acceptor;
    }

    public String getCreated_at(){
        return created_at;
    }

    public String getUpdated_at(){
        return updated_at;
    }



}
