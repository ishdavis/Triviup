package cs1635.triviup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LandingPageInterface} interface
 * to handle interaction events.
 * Use the {@link HostLandingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HostLandingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LandingPageInterface mListener;

    // RecyclerViews
    private RecyclerView mRecyclerView;
    private MatchHistoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public HostLandingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HostLandingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HostLandingFragment newInstance(String param1, String param2) {
        HostLandingFragment fragment = new HostLandingFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_host_landing, container, false);
        rootView.setTag("HostLandingFragment");

        // Handle RecyclerView for Match History
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.matchHistoryRecycler);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        String[] test = new String[10];
        test[0] = "Star Team 1";
        test[1] = "Oakland Warriors";
        test[2] = "Pitt Dissapointment";
        test[3] = "Snowy days";
        test[4] = "Easter fun";
        mAdapter = new MatchHistoryAdapter(test);
        mRecyclerView.setAdapter(mAdapter);// Inflate the layout for this fragment

        return rootView;
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
        if (context instanceof LandingPageInterface) {
            mListener = (LandingPageInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
}
