package com.skeleton.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skeleton.R;
import com.skeleton.model.Profile.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.customview.MaterialEditText;

import java.util.List;

/**
 * Developer: Sumit Thakur
 * Dated: 15-05-2017.
 */
public class ProfileCompletenessStep1Fragment extends BaseFragment implements View.OnClickListener {
    private MaterialEditText tvHistoryRelation, tvEthnicity, tvReligion, tvHeight, tvBodyType, tvSmoking, tvDrinking, tvOrientation;
    private ImageView ivLeft, ivRight, ivCenter;
    private Response responseFinal;

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
                        tvHistoryRelation, responseFinal.getData().getRelationshipHistory());
                break;
            case R.id.tvEuthnicity:
                popUp(EUTHNICITY,
                        tvEthnicity, responseFinal.getData().getEthnicity());
                break;
            case R.id.tvReligion:
                popUp(RELIGION,
                        tvReligion, responseFinal.getData().getReligion());
                break;
            case R.id.tvHeight:
                popUp(HEIGHT,
                        tvHeight, responseFinal.getData().getHeight());
                break;
            case R.id.tvBodyType:
                popUp(BODY_TYPE,
                        tvBodyType, responseFinal.getData().getBodyType());
                break;
            case R.id.tvSmoking:
                popUp(SMOKING,
                        tvSmoking, responseFinal.getData().getSmoking());
                break;
            case R.id.tvDrinking:
                popUp(DRINKING,
                        tvDrinking, responseFinal.getData().getDrinking());
                break;
            case R.id.tvOrientation:
                popUp(ORIENTATION,
                        tvOrientation, responseFinal.getData().getOrientation());
                break;
            default:
                break;
        }


    }

    /**
     * @param title            title
     * @param materialEditText material Edit Text
     * @param list             list
     */
    private void popUp(final String title, final MaterialEditText materialEditText, final List<String> list) {
        Log.e("debug", title);
        final CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setItems(cs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        materialEditText.setText(cs[which]);
                    }
                });
        builder.create();
        builder.show();
    }

}
