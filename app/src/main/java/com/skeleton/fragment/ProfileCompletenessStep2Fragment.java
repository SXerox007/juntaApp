package com.skeleton.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skeleton.R;


/**
 * Developer: Sumit Thakur
 * Dated: 16-05-2017.
 */
public class ProfileCompletenessStep2Fragment extends Fragment {

    private RecyclerView recyclerView;
    //private ArrayList<StudentInformation> userInfos;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_complete_step2_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvUserIntrest);


        return view;
    }
}
