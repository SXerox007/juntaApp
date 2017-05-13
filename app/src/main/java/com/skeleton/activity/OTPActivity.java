package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.model.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;

import java.util.HashMap;

import io.paperdb.Paper;

/**
 * Developer: Sumit Thakur
 * Dated: 13-05-2017.
 */
public class OTPActivity extends BaseActivity implements TextWatcher, View.OnClickListener {
    private Button btnResend, btnEditNo, btnverified;
    private MaterialEditText metOTP1, metOTP2, metOTP3, metOTP4;
    private TextView tvPhoneNumber;
    private ValidateEditText validateEditText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        init();
        btnverified.setOnClickListener(this);

    }


    /**
     * initilization
     */
    private void init() {
        btnResend = (Button) findViewById(R.id.btnResendOTP);
        btnEditNo = (Button) findViewById(R.id.btnEditNumber);
        btnverified = (Button) findViewById(R.id.btnVerify);
        metOTP1 = (MaterialEditText) findViewById(R.id.etOTP1);
        metOTP2 = (MaterialEditText) findViewById(R.id.etOTP2);
        metOTP3 = (MaterialEditText) findViewById(R.id.etOTP3);
        metOTP4 = (MaterialEditText) findViewById(R.id.etOTP4);
        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);
        validateEditText = new ValidateEditText();
    }


    @Override
    public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

    }

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
    }

    @Override
    public void afterTextChanged(final Editable s) {

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnVerify:
                if (validateEditText.genericEmpty(metOTP1, ERROR_MSG_EMPTY)
                        && validateEditText.genericEmpty(metOTP2, ERROR_MSG_EMPTY)
                        && validateEditText.genericEmpty(metOTP3, ERROR_MSG_EMPTY)
                        && validateEditText.genericEmpty(metOTP4, ERROR_MSG_EMPTY)) {
                    otpVerifyCheck();
                } else {
                    Log.e("debug", "All OTP Fields fill completely");
                }
                break;
            default:
                break;
        }

    }

    /**
     * OTP verification form server
     */
    private void otpVerifyCheck() {
        Intent intent = getIntent();
        HashMap<String, String> hashMap = new CommonParams.Builder()
                .add(KEY_FRAGMENT_COUNTRY_CODE, intent.getStringExtra(INTENT_KEY_COUNTRY_CODE).toString())
                .add(KEY_FRAGMENT_PHONE, intent.getStringExtra(INTENT_KEY_PHONE_NUMBER).toString())
                .add(KEY_OTP_CODE, metOTP1.getText().toString()
                        + metOTP2.getText().toString()
                        + metOTP3.getText().toString()
                        + metOTP4.getText().toString()).build().getMap();
        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.otpVerify((String) Paper.book().read(ACCESS_TOKEN), hashMap).enqueue(new ResponseResolver<Response>(this, true, true) {
            @Override
            public void success(final Response response) {
                if ("200".equals(response.getStatusCode())) {
                    Log.e("debug", "OTP verifed");
                } else {
                    Log.e("debug", "In sucess OTP not verified ");
                }
            }

            @Override
            public void failure(final APIError error) {
                Log.e("debug", "OTP not verified in Failure ");
            }
        });
    }


}
