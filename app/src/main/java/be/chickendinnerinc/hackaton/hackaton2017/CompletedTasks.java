package be.chickendinnerinc.hackaton.hackaton2017;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class CompletedTasks extends AppCompatActivity implements IJobListener{

    private Database database;
    private int userId;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        String serverAddress  = settings.getString("serverAddress", "http://localhost:3000/");
        userId = settings.getInt("currentUserId", 0);

        listView = (ListView)findViewById(R.id.listView);
        database = new Database(serverAddress);
        refreshList();
    }

    @Override
    public void populate(List<Job> jobs) {
        final StableArrayAdapter adapter = new StableArrayAdapter(this, jobs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Job item = (Job) parent.getItemAtPosition(position);

                Intent myIntent = new Intent(getApplicationContext(),
                        JobActivity.class);
                myIntent.putExtra("JobId", item.getId());
                startActivity(myIntent);
            }
        });
    }

    public void refreshList(){
        database.getCompletedJobsBy(this, userId);
    }


}
