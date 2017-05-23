package com.skeleton.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.skeleton.R;
import com.skeleton.fragment.SignInFragment;
import com.skeleton.fragment.SignUpFragment;

import java.util.ArrayList;
import java.util.List;

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
        setContentView(R.layout.activity_login);
        init();

    }

    /**
     * initilization
     */
    private void init() {
        viewPager = (ViewPager) findViewById(R.id.vpSwipe);
        tabLayout = (TabLayout) findViewById(R.id.tltablayout);

        fragments = new ArrayList<>();
        fragments.add(new SignUpFragment());
        fragments.add(new SignInFragment());

        PagerAdapter pagerAdapter = new com.skeleton.adapter.PagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * @param value contain true or false value
     */
    public void checkSucessFromFragment(final boolean value) {
        if (value) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
    }


}
