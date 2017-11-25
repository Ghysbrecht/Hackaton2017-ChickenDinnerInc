package be.chickendinnerinc.hackaton.hackaton2017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OwnedJobsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OwnedJobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OwnedJobsFragment extends Fragment implements IJobListener, View.OnClickListener {


    private View mView;
    private Database database;
    private ListView listView;
    private int userId;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OwnedJobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OwnedJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OwnedJobsFragment newInstance(String param1, String param2) {
        OwnedJobsFragment fragment = new OwnedJobsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_owned_jobs, container, false);
        listView = (ListView)mView.findViewById(R.id.listView);

        SharedPreferences settings = this.getActivity().getSharedPreferences("MyPrefsFile", 0);
        String serverAddress  = settings.getString("serverAddress", "http://localhost:3000/");
        userId = settings.getInt("currentUserId", 0);

        FloatingActionButton plusButton = (FloatingActionButton)mView.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this);

        database = new Database(serverAddress);

        refreshList();
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void populate(List<Job> jobs) {
        final StableArrayAdapter adapter = new StableArrayAdapter(getContext(), jobs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Job item = (Job) parent.getItemAtPosition(position);

                Intent myIntent = new Intent(getContext(),
                        JobActivity.class);
                myIntent.putExtra("JobId", item.getId());
                startActivity(myIntent);
            }
        });
    }

    public void refreshList(){
        database.getCreatedJobsBy(this, userId);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.plusButton:
                Intent myIntent = new Intent(getContext(), CreateJobActivity.class);
                startActivity(myIntent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }
}
