package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.skeleton.R;
import com.skeleton.fragment.SignInFragment;
import com.skeleton.fragment.SignUpFragment;
import com.skeleton.model.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

import static com.skeleton.constant.AppConstant.ACCESS_TOKEN;

/**
 * Login Activity
 */
public class LoginActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //checkServer();
        setContentView(R.layout.activity_login);
        init();
        PagerAdapter pagerAdapter = new com.skeleton.adapter.PagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * initilization
     */
    private void init() {
        viewPager = (ViewPager) findViewById(R.id.vpSwipe);
        fragments = new ArrayList<>();
        fragments.add(new SignUpFragment());
        fragments.add(new SignInFragment());
        tabLayout = (TabLayout) findViewById(R.id.tltablayout);
    }

    /**
     * check server which activity we have to switch
     */
    private void checkServer() {
        if (Paper.book().read(ACCESS_TOKEN) != null) {
            ApiInterface apiInterface = RestClient.getApiInterface();
            apiInterface.getProfile((String) Paper.book().read(ACCESS_TOKEN)).enqueue(new ResponseResolver<Response>(this, true, true) {
                @Override
                public void success(final Response commonResponse) {
                    if ("200".equals(String.valueOf(commonResponse.getStatusCode()))) {
                        Log.e("debug", "sucess Acess Token");
                        if (commonResponse.getData().getUserDetails().getPhoneVerified()) {
                            Log.e("debug", "Phone Verifed");
                            startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                            finish();
                        } else {
                            Log.e("debug", "Phone Not Verifed");
                            startActivity(new Intent(getApplicationContext(), OTPActivity.class));
                            finish();
                        }
                    } else {
                        Log.e("debug", String.valueOf(commonResponse.getStatusCode()));
                        Log.e("debug", "Error in Sucess");
                    }
                }

                @Override
                public void failure(final APIError error) {
                    Log.e("debug", "Failure");
                }
            });
        } else {
            Log.e("debug", "Not found in paper db");
        }
    }

}
