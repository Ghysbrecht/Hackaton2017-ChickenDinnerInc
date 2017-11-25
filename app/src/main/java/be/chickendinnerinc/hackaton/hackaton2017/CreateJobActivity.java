package be.chickendinnerinc.hackaton.hackaton2017;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateJobActivity extends AppCompatActivity implements View.OnClickListener, IStreetListener{

    private Database database;
    private int userId;
    private List<String> streets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        streets = new ArrayList<>();

        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        String serverAddress  = settings.getString("serverAddress", "http://localhost:3000/");
        userId = settings.getInt("currentUserId", 0);

        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        database = new Database(serverAddress);
        database.getStreets(this);



    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.submitButton:
                submit();
                break;
        }
    }

    public void submit(){
        //Check all values and make object
        String title = ((EditText)findViewById(R.id.titleField)).getText().toString();
        String description = ((EditText)findViewById(R.id.commentField)).getText().toString();
        String location = ((EditText)findViewById(R.id.streetField)).getText().toString();
        int credits = 0;
        try {
            credits = Integer.parseInt(((EditText)findViewById(R.id.creditField)).getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        String tags = ((EditText)findViewById(R.id.tagsField)).getText().toString();


        boolean verified = true;
        String reason = "";
        if(title == ""){
            verified = false;
            reason = "Titel is niet ingevuld!";
        }
        if(description == ""){
            verified = false;
            reason = "Beschrijving is niet ingevuld!";
        }
        if(credits == 0 || credits > 1000){
            verified = false;
            reason = "Beloning is 0 of te hoog!";
        }

        if(verified){
        Job job = new Job(title,description, location, credits, tags, userId);
        database.createJob(job);
        kill_activity();
        }
        else{
            Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_SHORT).show();
        }

    }

    void kill_activity()
    {
        finish();
    }

    @Override
    public void populate(List<String> streets) {
        this.streets = streets;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, (String[])streets.toArray(new String[0]));
        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.streetField);
        textView.setAdapter(adapter);
    }
}
