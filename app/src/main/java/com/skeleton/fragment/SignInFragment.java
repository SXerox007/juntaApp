package com.skeleton.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skeleton.R;
import com.skeleton.activity.UserProfile;
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
 * Created by SUMIT_THAKUR on 11/05/17.
 */

public class SignInFragment extends BaseFragment implements View.OnClickListener {
    private MaterialEditText etEmail, etPassword;
    private Button btnSignIn;
    private ValidateEditText validateEditText;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_fragment, container, false);
        init(view);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (isValidate()) {
                    retrivedata();
                } else {
                    Log.e("debug", "error Login");
                }
            }
        });
        return view;
    }

    /**
     * @param view view
     */
    private void init(final View view) {
        etEmail = (MaterialEditText) view.findViewById(R.id.etEmail);
        etPassword = (MaterialEditText) view.findViewById(R.id.etPassword);
        btnSignIn = (Button) view.findViewById(R.id.btnSignin);
        validateEditText = new ValidateEditText();
    }

    /**
     * @return true or false value
     */
    public boolean isValidate() {
        return validateEditText.checkEmail(etEmail)
                && validateEditText.checkPassword(etPassword, false);
    }

    /**
     * retrive data from the server and validate the User
     */
    public void retrivedata() {
        HashMap<String, String> hashMap = new CommonParams.Builder()
                .add(KEY_FRAGMENT_EMAIL, etEmail.getText().toString().trim())
                .add(KEY_FRAGMENT_PASSWORD, etPassword.getText().toString().trim())
                .add(KEY_FRAGMENT_DEVICE_TYPE, VALUE_FRAGMENT_DEVICE_TYPE)
                .add(KEY_FRAGMENT_LANGUAGE, VALUE_FRAGMENT_LANGUAGE)
                .add(KEY_FRAGMENT_DEVICE_TOKEN, VALUE_RAGMENT_DEVICE_TOKEN)
                .add(KEY_FRAGMENT_FLUSH_TOKENS, true)
                .add(KEY_FRAGMENT_APP_VERSION, VALUE_FRAGMENT_APP_VERSION).build().getMap();

        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.userLogin(null, hashMap).enqueue(new ResponseResolver<Response>(getContext(), true, true) {
            @Override
            public void success(final Response response) {
                Paper.book().write(ACCESS_TOKEN, ACESS_START + response.getData().getAccessToken());
                Intent intent = new Intent(getContext(), UserProfile.class);
                Log.e("debug", "sucess signIn");
                clearFields(etEmail, etPassword);
                startActivity(intent);
            }

            @Override
            public void failure(final APIError error) {
                Log.e("debug", "Failure signIn");
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


}
