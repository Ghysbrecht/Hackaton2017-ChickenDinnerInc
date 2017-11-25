package be.chickendinnerinc.hackaton.hackaton2017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements IUserListener, View.OnClickListener{

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

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_profile, container, false);
        listView = (ListView)mView.findViewById(R.id.listView);

        SharedPreferences settings = this.getActivity().getSharedPreferences("MyPrefsFile", 0);
        String serverAddress  = settings.getString("serverAddress", "http://localhost:3000/");
        userId = settings.getInt("currentUserId", 0);
        database = new Database(serverAddress);
        database.getUserWithId(this, userId);

        ImageButton completedButton = mView.findViewById(R.id.buttonCompleted);
        completedButton.setOnClickListener(this);

        ImageButton transactionButton = mView.findViewById(R.id.buttonTrans);
        transactionButton.setOnClickListener(this);

        ImageButton cartButton = mView.findViewById(R.id.buttonContacts);
        cartButton.setOnClickListener(this);

        ImageButton settingsButton = mView.findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(this);

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
        Log.e("ATTACH", "ProfileFragment Attached");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void populate(User user){
        ((TextView)mView.findViewById(R.id.textName)).setText(user.getName());
        ((TextView)mView.findViewById(R.id.textPhone)).setText(user.getCellphone());
        ((TextView)mView.findViewById(R.id.textMoney)).setText(user.getCredits() + "");
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.buttonCompleted:
                Intent myIntent = new Intent(getContext(), CompletedTasks.class);
                startActivity(myIntent);
                break;
            case R.id.buttonContacts:

                break;
            case R.id.buttonTrans:
                Toast.makeText(getContext(), "Transactions - Coming Soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonSettings:
                Toast.makeText(getContext(), "Settings - Coming Soon", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
