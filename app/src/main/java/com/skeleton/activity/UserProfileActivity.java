package com.skeleton.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.fragment.ProfileCompletenessStep1Fragment;
import com.skeleton.fragment.ProfileCompletenessStep2Fragment;
import com.skeleton.model.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;

import io.paperdb.Paper;

import static com.skeleton.constant.AppConstant.ACCESS_TOKEN;

/**
 * Developer: Sumit Thakur
 * Dated: 15-05-2017.
 */
public class UserProfileActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView tvTitle;
    private Button btnTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        checkProfile();
        btnTitle = (Button) findViewById(R.id.btnToolBar);
        tvTitle = (TextView) findViewById(R.id.toolbar_top_title);
        tvTitle.setText(R.string.text_view_profile_completeness);
        btnTitle.setText(R.string.button_skip);
    }

    /**
     * @param fragment fragment
     */
    public void replaceFragment(final Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flProfileCompleteness, fragment);
        fragmentTransaction.commit();
    }


    /**
     * check profile
     */
    private void checkProfile() {
        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.getProfile((String) Paper.book().read(ACCESS_TOKEN)).enqueue(new ResponseResolver<Response>(this, true, true) {
            @Override
            public void success(final Response response) {
                if ("200".equals(response.getStatusCode().toString())) {
                    if (response.getData().getUserDetails().getStep1CompleteOrSkip()) {
                        replaceFragment(new ProfileCompletenessStep2Fragment());
                    } else {
                        replaceFragment(new ProfileCompletenessStep1Fragment());
                    }
                } else {
                    Log.e("debug", "profile check in sucess.(failed)");
                }
            }

            @Override
            public void failure(final APIError error) {
                Log.e("debug", "profile check in failure");
            }
        });
    }

}
