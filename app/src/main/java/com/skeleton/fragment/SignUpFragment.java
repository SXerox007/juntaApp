package com.skeleton.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.skeleton.R;
import com.skeleton.activity.OTPActivity;
import com.skeleton.model.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;
import com.skeleton.util.imagepicker.ImageChooser;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;
import okhttp3.RequestBody;

/**
 * Created by SUMIT_THAKUR on 11/05/17.
 */

public class SignUpFragment extends BaseFragment {
    private ImageView ivProfile;
    private MaterialEditText etName, etPhoneNumber, etEmail, etDateOfBirth, etPassword, etConfirmPassword, tvOrientation;
    private File file;
    private ImageChooser imageChooser;
    private ValidateEditText validateEditText = new ValidateEditText();
    private Date date = new Date();
    private boolean flag;
    private Button btnRegister;
    private RadioGroup radioGroup;
    private int mGender = VALUE_FLAG;
    private RadioButton rbMale;
    private com.skeleton.model.Profile.Response responseFinal;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        init(view);
        tvOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                popUp(ORIENTATION, tvOrientation, responseFinal.getData().getOrientation());
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup group, @IdRes final int checkedId) {
                if (checkedId == R.id.radio_a) {
                    mGender = 0;
                } else {
                    mGender = 1;
                }
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                imageChooser = new ImageChooser(new ImageChooser.Builder(SignUpFragment.this));
                imageChooser.selectImage(new ImageChooser.OnImageSelectListener() {
                    @Override
                    public void loadImage(final List<ChosenImage> list) {
                        file = new File(list.get(0).getOriginalPath());
                        if (file != null) {
                            // Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            //ivProfile.setImageBitmap(myBitmap);
                            //ivProfile.setImageURI(Uri.fromFile(file));
                            Glide.with(SignUpFragment.this)
                                    .load(list.get(0).getQueryUri())
                                    .into(ivProfile);
                            Log.d("debug", list.get(0).getQueryUri());
                        } else {
                            Log.e("debug", "Error");

                        }
                    }

                    @Override
                    public void croppedImage(final File mCroppedImage) {

                    }
                });

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                validation();


            }
        });
        return view;
    }

    /**
     * @param view view
     */
    private void init(final View view) {
        tvOrientation = (MaterialEditText) view.findViewById(R.id.tvOrientation);
        ivProfile = (ImageView) view.findViewById(R.id.iv_profile_image);
        etName = (MaterialEditText) view.findViewById(R.id.etName);
        etPhoneNumber = (MaterialEditText) view.findViewById(R.id.etPhoneNumber);
        etEmail = (MaterialEditText) view.findViewById(R.id.etEmail);
        etDateOfBirth = (MaterialEditText) view.findViewById(R.id.etDOB);
        etPassword = (MaterialEditText) view.findViewById(R.id.etPassword);
        etConfirmPassword = (MaterialEditText) view.findViewById(R.id.etConfirmPassword);
        btnRegister = (Button) view.findViewById(R.id.btnSignup);
        radioGroup = (RadioGroup) view.findViewById(R.id.genderSelection);
        rbMale = (RadioButton) view.findViewById(R.id.radio_a);
        rbMale.isChecked();
        enableFoatingEditText(etName, etConfirmPassword, etPhoneNumber, etEmail, etDateOfBirth, etPassword);
        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.profileConstant().enqueue(new ResponseResolver<com.skeleton.model.Profile.Response>(getActivity(), true, true) {
            @Override
            public void success(final com.skeleton.model.Profile.Response response) {
                responseFinal = response;
                Log.e("debug", " in Profile 1 Sucess");
            }

            @Override
            public void failure(final APIError error) {
                Log.e("debug", "failure in profile 1");

            }
        });


    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        imageChooser.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        imageChooser.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Checks if date is in valid format
     *
     * @param editText : editTextDOB containing date
     * @return : true if valid, else returns false
     */
    private boolean checkDOB(final EditText editText) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = editText.getText().toString();
        try {
            date = df.parse(s);
            return true;

        } catch (ParseException e) {
            editText.setError(getString(R.string.error_date));
            return false;
        }
    }

    /**
     * validation
     */
    private void validation() {
        if (ValidateEditText.checkEmail(etEmail)
                && validateEditText.checkPassword(etPassword, false)
                && validateEditText.checkPassword(etConfirmPassword, true)
                && validateEditText.checkName(etName, true)
                && validateEditText.checkPhoneNumber(etPhoneNumber)
                && validateEditText.comparePassword(etPassword, etConfirmPassword)
                && validateEditText.genericEmpty(etDateOfBirth, getString(R.string.error_date))) {
            flag = checkDOB(etDateOfBirth);


            if (flag) {
                if (mGender < 2) {
                    Log.e("debug", "Sucess check");
                    uploadData();
                } else {
                    Log.e("debug", "Error1");
                }
            } else {
                Log.e("debug", "Error2");
            }
        } else {
            Log.e("debug", "Error3");
        }
    }

    /**
     * Enable floating label for {@link MaterialEditText}
     *
     * @param editTexts :list of editText
     */
    public static void enableFoatingEditText(final MaterialEditText... editTexts) {
        for (MaterialEditText editText : editTexts) {
            editText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_HIGHLIGHT);
        }
    }

    /**
     * Make server request to upload data
     */
    private void uploadData() {
        Toast.makeText(getContext(), "Data ready", Toast.LENGTH_SHORT).show();
        HashMap<String, RequestBody> multipartParams = new MultipartParams.Builder()
                .add(KEY_FRAGMENT_FNAME, etName.getText().toString().trim())
                .add(KEY_FRAGMENT_LNAME, etName.getText().toString().trim())
                .add(KEY_FRAGMENT_DOB, etDateOfBirth.getText().toString().trim())
                .add(KEY_FRAGMENT_COUNTRY_CODE, COUNTRY_CODE)
                .add(KEY_FRAGMENT_PHONE, etPhoneNumber.getText().toString().trim())
                .add(KEY_FRAGMENT_EMAIL, etEmail.getText().toString().trim())
                .add(KEY_FRAGMENT_PASSWORD, etPassword.getText().toString().trim())
                .add(KEY_FRAGMENT_LANGUAGE, VALUE_FRAGMENT_LANGUAGE)
                .add(KEY_FRAGMENT_DEVICE_TYPE, VALUE_FRAGMENT_DEVICE_TYPE)
                .add(KEY_FRAGMENT_DEVICE_TOKEN, VALUE_RAGMENT_DEVICE_TOKEN)
                .add(KEY_FRAGMENT_APP_VERSION, VALUE_FRAGMENT_APP_VERSION)
                .add(KEY_FRAGMENT_GENDER, mGender)
                .add(KEY_FRAGMENT_ORIENTATION, tvOrientation.getText().toString().trim())
                .add(KEY_FRAGMENT_PROFILE_PIC, file).build().getMap();

        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.userRegister(multipartParams).enqueue(new ResponseResolver<Response>(getActivity(), true, true) {

            @Override
            public void success(final Response response) {
                if ("200".equals(response.getStatusCode().toString())) {
                    Log.e("debug", "POST Sucess");
                    clearFields(etName, etConfirmPassword, etDateOfBirth, etEmail, etPassword, etPhoneNumber);
                    //Paper DB
                    Paper.book().write(ACCESS_TOKEN, ACESS_START + response.getData().getAccessToken());
                    Paper.book().write(INTENT_KEY_COUNTRY_CODE, response.getData().getUserDetails().getCountryCode());
                    Paper.book().write(INTENT_KEY_PHONE_NUMBER, response.getData().getUserDetails().getPhoneNo());
                    Log.e("debug", ACESS_START + response.getData().getAccessToken());
                    Log.e("debug", response.getData().getUserDetails().getPhoneNo());
                    Log.e("debug", response.getData().getUserDetails().getCountryCode());
                    Intent intent = new Intent(getContext(), OTPActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void failure(final APIError error) {
                Log.e("debug", "POST Failure");
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

    /**
     * @param title            title of popup
     * @param materialEditText edit text for set the data
     * @param list             list send which will be displayed
     */
    private void popUp(final String title, final MaterialEditText materialEditText, final List<String> list) {
        Log.e("debug", title);
        final CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setItems(cs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        materialEditText.setText(cs[which]);
                    }
                });
        builder.create();
        builder.show();
    }


}
