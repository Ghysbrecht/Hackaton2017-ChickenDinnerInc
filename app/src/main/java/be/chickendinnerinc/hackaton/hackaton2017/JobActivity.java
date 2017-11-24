package be.chickendinnerinc.hackaton.hackaton2017;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class JobActivity extends AppCompatActivity implements IJobListener, IUserListener, View.OnClickListener{

    private Database database;
    private int jobId = 0;
    private int userId = 0;
    private Button acceptButton;
    private boolean jobAccepted;
    private boolean jobCompleted;
    private boolean myJob;
    private String ownerPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        int jobId = getIntent().getExtras().getInt("JobId");

        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        String serverAddress  = settings.getString("serverAddress", "http://localhost:3000/");
        userId  = settings.getInt("currentUserId", 0);

        database = new Database(serverAddress);
        database.getJobWithId(this, jobId);
    }

    @Override
    public void populate(List<Job> jobs) {
        Job job = jobs.get(0);
        jobId = job.getId();
        database.getUserWithId(this, job.getOwner());

        acceptButton = (Button) findViewById(R.id.acceptButton);

        if(job.getAcceptor() == 0) jobAccepted = false;
        else jobAccepted = true;

        if(job.getOwner() == userId) myJob = true;
        else myJob = false;

        if(myJob && jobAccepted) acceptButton.setText("Voltooien");
        else if(myJob && !jobAccepted) acceptButton.setText("Verwijderen");
        else if(jobAccepted) acceptButton.setText("Contacteer Eigenaar");
        else acceptButton.setText("Accepteer");
        acceptButton.setOnClickListener(this);

        ((TextView)findViewById(R.id.titleText)).setText(job.getTitle());
        ((TextView)findViewById(R.id.descriptText)).setText(job.getDescription());
        ((TextView)findViewById(R.id.creditsToEarn)).setText(job.getCredits_to_earn()+"");
        ((TextView)findViewById(R.id.tagsText)).setText(job.getTags());


        //Hier kan je beginnen spelen met de view

    }

    @Override
    public void populate(User user){
        ownerPhoneNumber = user.getCellphone();
        ((TextView)findViewById(R.id.userText)).setText(user.getName());
    }

    public void acceptJob(){
        Log.e("JOB", "Accepting Job with JobId:" + jobId + " UserId:" + userId);
        database.acceptJob(jobId, userId);
        acceptButton.setText("Job Geacepteerd!");
        acceptButton.setEnabled(false);
    }

    public void completeJob(){
        Log.e("JOB", "Completing Job with JobId:" + jobId);
        database.completeJob(jobId);
        acceptButton.setText("Job voltooid!");
        acceptButton.setEnabled(false);
    }

    public void deleteJob(){
        Log.e("JOB", "Deleting Job with JobId:" + jobId);
        database.deleteJob(jobId);
        acceptButton.setText("Job deleted!");
        acceptButton.setEnabled(false);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.acceptButton:
                if(myJob && jobAccepted) completeJob();
                else if(myJob && !jobAccepted) deleteJob();
                else if(jobAccepted) callOwner();
                else acceptJob();
                break;
        }
    }

    public void callOwner(){
        Log.e("JOB", "Calling owner...!");
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ownerPhoneNumber));
        startActivity(intent);
    }
}
