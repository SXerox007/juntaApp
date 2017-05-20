package com.skeleton.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.activity.UserProfileActivity;
import com.skeleton.model.Profile.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.customview.MaterialEditText;

import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;
import okhttp3.RequestBody;

/**
 * Developer: Sumit Thakur
 * Dated: 15-05-2017.
 */
public class ProfileCompletenessStep1Fragment extends BaseFragment implements View.OnClickListener {
    private MaterialEditText tvHistoryRelation, tvEthnicity, tvReligion, tvHeight, tvBodyType, tvSmoking, tvDrinking, tvOrientation;
    private ImageView ivLeft, ivRight, ivCenter;
    private Response responseFinal;
    private TextView textViewBar1, textViewbar2, textViewBar3, textViewBar4, textViewBar5, textViewBar6, textViewBar7, textViewBar8;
    private Button btnNextStep, btnSkipPress;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_complete_step1_fragment, container, false);
        init(view);
        btnNextStep.setOnClickListener(this);
        btnSkipPress = ((UserProfileActivity) getActivity()).btnSkipPressed();
        btnSkipPress.setOnClickListener(this);
        return view;
    }

    /**
     * @param v view
     */
    private void init(final View v) {
        btnNextStep = (Button) v.findViewById(R.id.btnNext);
        textViewBar1 = (TextView) v.findViewById(R.id.tvBar1);
        textViewbar2 = (TextView) v.findViewById(R.id.tvBar2);
        textViewBar3 = (TextView) v.findViewById(R.id.tvBar3);
        textViewBar4 = (TextView) v.findViewById(R.id.tvBar4);
        textViewBar5 = (TextView) v.findViewById(R.id.tvBar5);
        textViewBar6 = (TextView) v.findViewById(R.id.tvBar6);
        textViewBar7 = (TextView) v.findViewById(R.id.tvBar7);
        textViewBar8 = (TextView) v.findViewById(R.id.tvBar8);
        tvHistoryRelation = (MaterialEditText) v.findViewById(R.id.tvRelationshipHistory);
        tvEthnicity = (MaterialEditText) v.findViewById(R.id.tvEuthnicity);
        tvReligion = (MaterialEditText) v.findViewById(R.id.tvReligion);
        tvHeight = (MaterialEditText) v.findViewById(R.id.tvHeight);
        tvBodyType = (MaterialEditText) v.findViewById(R.id.tvBodyType);
        tvSmoking = (MaterialEditText) v.findViewById(R.id.tvSmoking);
        tvDrinking = (MaterialEditText) v.findViewById(R.id.tvDrinking);
        tvOrientation = (MaterialEditText) v.findViewById(R.id.tvOrientation);
        ivLeft = (ImageView) v.findViewById(R.id.iv_leftgreen);
        ivRight = (ImageView) v.findViewById(R.id.iv_rightgreen);
        ivCenter = (ImageView) v.findViewById(R.id.iv_centergreen);
        ivCenter.setVisibility(View.GONE);
        ivRight.setVisibility(View.GONE);
        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.profileConstant().enqueue(new ResponseResolver<com.skeleton.model.Profile.Response>(getActivity(), true, true) {
            @Override
            public void success(final com.skeleton.model.Profile.Response response) {
                responseFinal = response;
                Log.e("debug", " in Profile 1 Sucess");
                onclickOnItems();
            }

            @Override
            public void failure(final APIError error) {
                Log.e("debug", "failure in profile 1");

            }
        });


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
        switch (v.getId()) {
            case R.id.tvRelationshipHistory:
                Log.e("debug", "onclick Relation history");
                popUp(RELATIONSHIP_HISTORY,
                        tvHistoryRelation, responseFinal.getData().getRelationshipHistory(), textViewBar1);
                break;
            case R.id.tvEuthnicity:
                popUp(EUTHNICITY,
                        tvEthnicity, responseFinal.getData().getEthnicity(), textViewbar2);
                break;
            case R.id.tvReligion:
                popUp(RELIGION,
                        tvReligion, responseFinal.getData().getReligion(), textViewBar3);
                break;
            case R.id.tvHeight:
                popUp(HEIGHT,
                        tvHeight, responseFinal.getData().getHeight(), textViewBar4);
                break;
            case R.id.tvBodyType:
                popUp(BODY_TYPE,
                        tvBodyType, responseFinal.getData().getBodyType(), textViewBar5);
                break;
            case R.id.tvSmoking:
                popUp(SMOKING,
                        tvSmoking, responseFinal.getData().getSmoking(), textViewBar6);
                break;
            case R.id.tvDrinking:
                popUp(DRINKING,
                        tvDrinking, responseFinal.getData().getDrinking(), textViewBar7);
                break;
            case R.id.tvOrientation:
                popUp(ORIENTATION,
                        tvOrientation, responseFinal.getData().getOrientation(), textViewBar8);
                break;
            case R.id.btnNext:
                updateUserProfile(0);
                replaceFragment();
                break;
            default:
                updateUserProfile(1);
                replaceFragment();
                break;
        }


    }

    /**
     * replace fragment
     */
    private void replaceFragment() {
        ((UserProfileActivity) getActivity()).replaceFragment(new ProfileCompletenessStep2Fragment());
    }


    /**
     * @param title            title
     * @param materialEditText material Edit Text
     * @param list             list
     * @param textView         textView
     */
    private void popUp(final String title, final MaterialEditText materialEditText, final List<String> list, final TextView textView) {
        Log.e("debug", title);
        final CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setItems(cs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        materialEditText.setText(cs[which]);
                        textView.setBackgroundResource(R.color.colorPrimary);
                    }
                });
        builder.create();
        builder.show();
    }

    /**
     * @param value value check which will execute
     */
    private void updateUserProfile(final int value) {
        if (value == 0) {
            HashMap<String, RequestBody> multipartParams = new MultipartParams.Builder()
                    .add(RELATIONSHIP_HISTORY, tvHistoryRelation.getText().toString().trim())
                    .add(EUTHNICITY, tvEthnicity.getText().toString().trim())
                    .add(RELIGION, tvReligion.getText().toString().trim())
                    .add(HEIGHT, tvHeight.getText().toString().trim())
                    .add(BODY_TYPE, tvBodyType.getText().toString().trim())
                    .add(SMOKING, tvSmoking.getText().toString().trim())
                    .add(DRINKING, tvDrinking.getText().toString().trim())
                    .add(ORIENTATION, tvOrientation.getText().toString().trim())
                    .add(STEP1_COMPLETE, true).build().getMap();
            apiCall(multipartParams);
        } else {
            HashMap<String, RequestBody> multipartParams = new MultipartParams.Builder()
                    .add(STEP1_COMPLETE, true).build().getMap();
            apiCall(multipartParams);
        }
    }


    /**
     * @param multipartParams hash map
     */
    private void apiCall(final HashMap<String, RequestBody> multipartParams) {
        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.updateProfile((String) Paper.book().read(ACCESS_TOKEN), multipartParams)
                .enqueue(new ResponseResolver<com.skeleton.model.Update.Response>(getActivity(), true, true) {
                    @Override
                    public void success(final com.skeleton.model.Update.Response response) {
                        Log.e("debug", "sucess update user profile sucessfully");
                    }

                    @Override
                    public void failure(final APIError error) {
                        Log.e("debug", String.valueOf(error.getStatusCode()));
                        Log.e("debug", "failure in user profile");
                    }
                });
    }


}
