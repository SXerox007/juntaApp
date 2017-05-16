package com.skeleton.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;

/**
 * Developer: Sumit Thakur
 * Dated: 15-05-2017.
 */
public class ProfileCompletenessStep1Fragment extends Fragment implements View.OnClickListener {

    private TextView tvHistoryRelation, tvEthnicity, tvReligion, tvHeight, tvBodyType, tvSmoking, tvDrinking, tvOrientation;
    private com.skeleton.model.Profile.Data data = new com.skeleton.model.Profile.Data();

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_complete_step1_fragment, container, false);
        init(view);
        return view;
    }

    /**
     * @param v view
     */
    private void init(final View v) {
        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.profileConstant().enqueue(new ResponseResolver<com.skeleton.model.Profile.Response>(getActivity(), true, true) {
            @Override
            public void success(final com.skeleton.model.Profile.Response response) {

            }

            @Override
            public void failure(final APIError error) {

            }
        });

        tvHistoryRelation = (TextView) v.findViewById(R.id.tvRelationshipHistory);
        tvEthnicity = (TextView) v.findViewById(R.id.tvEuthnicity);
        tvReligion = (TextView) v.findViewById(R.id.tvReligion);
        tvHeight = (TextView) v.findViewById(R.id.tvHeight);
        tvBodyType = (TextView) v.findViewById(R.id.tvBodyType);
        tvSmoking = (TextView) v.findViewById(R.id.tvSmoking);
        tvDrinking = (TextView) v.findViewById(R.id.tvDrinking);
        tvOrientation = (TextView) v.findViewById(R.id.tvOrientation);
        onclickOnItems();
    }


    /**
     * onClick on items
     */
    private void onclickOnItems() {
        tvHistoryRelation.setOnClickListener(this);
        tvEthnicity.setOnClickListener(this);
        tvReligion.setOnClickListener(this);
        tvHeight.setOnClickListener(this);
        tvBodyType.setOnClickListener(this);
        tvSmoking.setOnClickListener(this);
        tvDrinking.setOnClickListener(this);
        tvOrientation.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
/*
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.text_view_releationship_history)
                .setItems(data.getRelationshipHistory(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {

                    }
                });
        builder.create();
        */
    }
}
