package com.example.keshav.represent;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CandidateFragment extends Fragment {
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ZIP_CODE = "param_zip_code";
    private static final String PARAM_PARTY = "party";


    public TextView candidate;

    public static CandidateFragment newInstance(String zipCode, String name, String party) {
        Bundle arguments = new Bundle();
        arguments.putString(PARAM_NAME, name);
        arguments.putString(PARAM_ZIP_CODE, zipCode);
        arguments.putString(PARAM_PARTY, party);
        CandidateFragment candidateFragment = new CandidateFragment();
        candidateFragment.setArguments(arguments);
        return candidateFragment;
    }

    public CandidateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup candidateView = (ViewGroup) layoutInflater.inflate(R.layout.candidate_view, container, false);
        candidate = (TextView) candidateView.findViewById(R.id.rep_party);
        String name = getArguments().getString(PARAM_NAME)+" "+getArguments().getString(PARAM_PARTY);
        candidate.setText(name);
        candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(getActivity().getBaseContext(), WatchToPhoneService.class); //phone
                getActivity().startService(phoneIntent);

                Intent electionIntent = new Intent(getActivity(), obamaromney.class); //watch
                if (getArguments().getString(PARAM_ZIP_CODE) != null) {
                    electionIntent.putExtra("ZIP_CODE", getArguments().getString(PARAM_ZIP_CODE));
                }
                startActivity(electionIntent);
            }
        });
        return candidateView;
    }
}
