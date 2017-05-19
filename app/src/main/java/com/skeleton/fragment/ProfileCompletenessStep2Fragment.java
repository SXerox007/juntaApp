package com.skeleton.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.adapter.RecyclerViewAdapter;
import com.skeleton.model.Profile2.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;

import io.paperdb.Paper;

import static com.skeleton.constant.AppConstant.ACCESS_TOKEN;
import static com.skeleton.constant.AppConstant.KEY_INTEREST;

/**
 * Developer: Sumit Thakur
 * Dated: 16-05-2017.
 */
public class ProfileCompletenessStep2Fragment extends Fragment {

    private TextView tvBar1, tvBar2, tvBar3, tvBar4, tvBar5;
    private ImageView imageView;
    private RecyclerView rvRecyclerView;
    private Drawable.ConstantState mColour, mColorPrimary;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_complete_step2_fragment, container, false);
        init(view);
        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.profileImageSet((String) Paper.book().read(ACCESS_TOKEN), KEY_INTEREST)
                .enqueue(new ResponseResolver<Response>(getActivity(), true, true) {
                    @Override
                    public void success(final Response response) {
                        Log.e("debug", "sucess complete");
                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),
                                response.getData().getCategories(),
                                ProfileCompletenessStep2Fragment.this);
                        rvRecyclerView.setAdapter(recyclerViewAdapter);
                        rvRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                        rvRecyclerView.setHasFixedSize(true);
                    }

                    @Override
                    public void failure(final APIError error) {
                        Log.e("debug", " In Failure");
                    }
                });

        return view;
    }

    /**
     * @param view view
     */
    public void init(final View view) {
        rvRecyclerView = (RecyclerView) view.findViewById(R.id.rvUserIntrest);
        tvBar1 = (TextView) view.findViewById(R.id.tvBar1);
        tvBar2 = (TextView) view.findViewById(R.id.tvBar2);
        tvBar3 = (TextView) view.findViewById(R.id.tvBar3);
        tvBar4 = (TextView) view.findViewById(R.id.tvBar4);
        tvBar5 = (TextView) view.findViewById(R.id.tvBar5);
        imageView = (ImageView) view.findViewById(R.id.iv_centergreen);
        mColorPrimary = imageView.getBackground().getConstantState();
        mColour = tvBar1.getBackground().getConstantState();
    }

    /**
     * bar fill
     */
    public void barFill() {
        if (tvBar1.getBackground().getConstantState().equals(mColour)) {
            tvBar1.setBackgroundResource(R.color.colorPrimary);
        } else if (tvBar2.getBackground().getConstantState().equals(mColour)) {
            tvBar2.setBackgroundResource(R.color.colorPrimary);
        } else if (tvBar3.getBackground().getConstantState().equals(mColour)) {
            tvBar3.setBackgroundResource(R.color.colorPrimary);
        } else if (tvBar4.getBackground().getConstantState().equals(mColour)) {
            tvBar4.setBackgroundResource(R.color.colorPrimary);
        } else {
            tvBar5.setBackgroundResource(R.color.colorPrimary);
        }
    }

    /**
     * bar unfill
     */
    public void barUnfill() {
        if (tvBar5.getBackground().getConstantState().equals(mColorPrimary)) {
            tvBar5.setBackgroundResource(R.color.gray_light);
        } else if (tvBar4.getBackground().getConstantState().equals(mColorPrimary)) {
            tvBar4.setBackgroundResource(R.color.gray_light);
        } else if (tvBar3.getBackground().getConstantState().equals(mColorPrimary)) {
            tvBar3.setBackgroundResource(R.color.gray_light);
        } else if (tvBar2.getBackground().getConstantState().equals(mColorPrimary)) {
            tvBar2.setBackgroundResource(R.color.gray_light);
        } else {
            tvBar1.setBackgroundResource(R.color.gray_light);
        }
    }

}
