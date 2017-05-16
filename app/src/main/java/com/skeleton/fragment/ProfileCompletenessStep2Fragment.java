package com.skeleton.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private RecyclerView rvRecyclerView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_complete_step2_fragment, container, false);
        rvRecyclerView = (RecyclerView) view.findViewById(R.id.rvUserIntrest);
        Log.e("debug", "Above sucess");
        ApiInterface apiInterface = RestClient.getApiInterface();
        Log.e("debug", "Above sucess1");

        apiInterface.profileImageSet((String) Paper.book().read(ACCESS_TOKEN), KEY_INTEREST)
                .enqueue(new ResponseResolver<Response>(getActivity(), true, true) {
                    @Override
                    public void success(final Response response) {
                        Log.e("debug", "sucess complete");
                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), response.getData().getCategories());
                        rvRecyclerView.setAdapter(recyclerViewAdapter);
                        rvRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                        rvRecyclerView.setHasFixedSize(true);
                    }

                    @Override
                    public void failure(final APIError error) {
                        Log.e("debug", "Failure");
                    }
                });


        return view;
    }
}
