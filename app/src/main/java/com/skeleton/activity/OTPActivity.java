package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private Button btnResend, btnEditNo, btnverified, btnTool;
    private MaterialEditText metOTP1, metOTP2, metOTP3, metOTP4;
    private TextView tvPhoneNumber;
    private ImageView ivBack;
    private ValidateEditText validateEditText;
    private Intent intent = getIntent();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        init();
        tvPhoneNumber.setText(Paper.book().read(INTENT_KEY_COUNTRY_CODE) + "-" + Paper.book().read(INTENT_KEY_PHONE_NUMBER));
        btnTool.setVisibility(View.GONE);
        onClickItems();

    }


    /**
     * on click
     */
    private void onClickItems() {
        btnverified.setOnClickListener(this);
        btnResend.setOnClickListener(this);
        btnEditNo.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }


    /**
     * initilization
     */
    private void init() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        btnResend = (Button) findViewById(R.id.btnResendOTP);
        btnEditNo = (Button) findViewById(R.id.btnEditNumber);
        btnverified = (Button) findViewById(R.id.btnVerify);
        metOTP1 = (MaterialEditText) findViewById(R.id.etOTP1);
        metOTP2 = (MaterialEditText) findViewById(R.id.etOTP2);
        metOTP3 = (MaterialEditText) findViewById(R.id.etOTP3);
        metOTP4 = (MaterialEditText) findViewById(R.id.etOTP4);
        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);
        validateEditText = new ValidateEditText();
        btnTool = (Button) findViewById(R.id.btnToolBar);
        metOTP1.addTextChangedListener(this);
        metOTP2.addTextChangedListener(this);
        metOTP3.addTextChangedListener(this);
        metOTP4.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

    }

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        if (metOTP1.getText().toString().length() == 1) {
            metOTP2.requestFocus();
        }
        if (metOTP2.getText().toString().length() == 1) {
            metOTP3.requestFocus();
        }
        if (metOTP3.getText().toString().length() == 1) {
            metOTP4.requestFocus();
        }


    }

    @Override
    public void afterTextChanged(final Editable s) {

    }

    @Override
    public void onClick(final View v) {
        ApiInterface apiInterface = RestClient.getApiInterface();
        switch (v.getId()) {
            case R.id.btnVerify:
                if (validateEditText.genericEmpty(metOTP1, ERROR_MSG_EMPTY)
                        && validateEditText.genericEmpty(metOTP2, ERROR_MSG_EMPTY)
                        && validateEditText.genericEmpty(metOTP3, ERROR_MSG_EMPTY)
                        && validateEditText.genericEmpty(metOTP4, ERROR_MSG_EMPTY)) {
                    otpVerifyCheck();
                    clearFields(metOTP1, metOTP2, metOTP3, metOTP4);
                } else {
                    Log.e("debug", "All OTP Fields fill completely");
                }
                break;
            case R.id.btnResendOTP:
                apiInterface.resendOTP((String) Paper.book().read(ACCESS_TOKEN)).enqueue(new ResponseResolver<Response>(this, true, true) {
                    @Override
                    public void success(final Response response) {
                        Log.e("debug", "OTP resend.");
                    }

                    @Override
                    public void failure(final APIError error) {
                        Log.e("debug", String.valueOf(error.getStatusCode()));
                        Log.e("debug", "OTP resend in Failure");
                    }
                });

                break;
            case R.id.btnEditNumber:
                intent = new Intent(OTPActivity.this, EditNumberActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.ivBack:
                onBackPressFunctionality();
                break;
            default:
                break;
        }

    }

    /**
     * OTP verification form server
     */
    private void otpVerifyCheck() {
        HashMap<String, String> hashMap = new CommonParams.Builder()
                .add(KEY_FRAGMENT_COUNTRY_CODE, Paper.book().read(INTENT_KEY_COUNTRY_CODE))
                .add(KEY_FRAGMENT_PHONE, Paper.book().read(INTENT_KEY_PHONE_NUMBER))
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
                    intent = new Intent(OTPActivity.this, UserProfileActivity.class);
                    startActivity(intent);
                } else {
                    Log.e("debug", "In sucess OTP not verified ");
                    intent = new Intent(OTPActivity.this, UserProfileActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void failure(final APIError error) {
                Log.e("debug", "OTP not verified in Failure ");
            }
        });
    }

    /**
     * @param materialEditText parametr material edit text
     */
    private void clearFields(final MaterialEditText... materialEditText) {
        for (MaterialEditText editText : materialEditText) {
            editText.setText("");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackPressFunctionality();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        tvPhoneNumber.setText(Paper.book().read(INTENT_KEY_COUNTRY_CODE) + "-" + Paper.book().read(INTENT_KEY_PHONE_NUMBER));
    }


    /**
     * on back press
     */
    private void onBackPressFunctionality() {
        Paper.book().delete(ACCESS_TOKEN);

    }


}
