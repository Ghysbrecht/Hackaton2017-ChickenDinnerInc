package be.chickendinnerinc.hackaton.hackaton2017;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Thomas on 24/11/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StableArrayAdapter extends ArrayAdapter<Job> {
    private final Context context;
    private final List<Job> values;

    public StableArrayAdapter(Context context, List<Job> values) {
        super(context, R.layout.list_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_layout, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);

        firstLine.setText(values.get(position).getTitle());
        secondLine.setText(values.get(position).getCredits_to_earn() + "");

        //Hier kan je beginnen spelen met een rij, de layout kan je aanpassen in list_layout.xml

        return rowView;
    }
}
