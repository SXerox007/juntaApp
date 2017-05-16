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

/**
 * Developer: Sumit Thakur
 * Dated: 15-05-2017.
 */
public class UserProfile extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private TextView tvTitle;
    private Button btnTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        btnTitle = (Button) findViewById(R.id.btnToolBar);
        tvTitle = (TextView) findViewById(R.id.toolbar_top_title);
        tvTitle.setText(R.string.text_view_profile_completeness);
        btnTitle.setText(R.string.button_skip);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new ProfileCompletenessStep1Fragment();
        fragmentTransaction.replace(R.id.flProfileCompleteness, fragment);
        fragmentTransaction.commit();

    }

}
