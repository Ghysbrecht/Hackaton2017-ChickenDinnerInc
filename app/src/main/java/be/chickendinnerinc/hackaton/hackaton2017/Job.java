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

    Job(){
        this("Unset", "Unset", "Unset", 0, "Unset", 0);
    }

    Job(String title, String description, String location, int credits_to_earn, String tags, int owner){
        this.title = title;
        this.description = description;
        this.location = location;
        this.credits_to_earn = credits_to_earn;
        this.tags = tags;
        this.owner = owner;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public int getOwner(){
        return owner;
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
