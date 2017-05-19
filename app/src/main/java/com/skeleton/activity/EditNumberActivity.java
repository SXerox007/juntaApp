package com.skeleton.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.model.Update.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.customview.MaterialEditText;

import java.util.HashMap;

import io.paperdb.Paper;
import okhttp3.RequestBody;

/**
 * Developer: Sumit Thakur
 * Dated: 18-05-2017.
 */
public class EditNumberActivity extends BaseActivity {

    private MaterialEditText metPhoneNumber;
    private Button btnSubmit, btnTool;
    private TextView tvTitleToolBar;
    private ImageView ivBackPress;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);
        init();
        ivBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                commmon();
            }
        });
        btnTool.setVisibility(View.GONE);
        tvTitleToolBar.setText(EDIT_NUMBER);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                HashMap<String, RequestBody> multipartParams = new MultipartParams.Builder()
                        .add(NEW_NUMBER, metPhoneNumber.getText().toString().trim()).build().getMap();
                ApiInterface apiInterface = RestClient.getApiInterface();
                apiInterface.updateProfile((String) Paper.book().read(ACCESS_TOKEN), multipartParams)
                        .enqueue(new ResponseResolver<Response>(EditNumberActivity.this, true, true) {
                            @Override
                            public void success(final Response response) {
                                Log.e("debug", "edit number sucessfully");
                                Log.e("debug", response.getData().getUserDetails().getPhoneNo());
                                Paper.book().write(INTENT_KEY_PHONE_NUMBER, response.getData().getUserDetails().getPhoneNo());
                                commmon();
                            }

                            @Override
                            public void failure(final APIError error) {
                                Log.e("dubug", "edit number in failure");
                            }
                        });
            }
        });
    }

    /**
     * initilization
     */
    private void init() {
        ivBackPress = (ImageView) findViewById(R.id.ivBack);
        metPhoneNumber = (MaterialEditText) findViewById(R.id.etPhoneNumber);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvTitleToolBar = (TextView) findViewById(R.id.toolbar_top_title);
        btnTool = (Button) findViewById(R.id.btnToolBar);
    }

    /**
     * common line of code used
     */
    private void commmon() {
        setResult(1);
        finish();
    }

}