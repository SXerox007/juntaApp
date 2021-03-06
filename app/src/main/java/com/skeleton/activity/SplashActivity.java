package com.skeleton.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skeleton.R;
import com.skeleton.fcm.FCMTokenInterface;
import com.skeleton.fcm.MyFirebaseInstanceIdService;
import com.skeleton.model.Response;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.Util;
import com.skeleton.util.dialog.CustomAlertDialog;

import io.paperdb.Paper;

import static bolts.Task.delay;

/**
 * Landing Page of the App
 */
public class SplashActivity extends BaseActivity implements FCMTokenInterface {
    private static final String TAG = SplashActivity.class.getName();
    private Dialog mDialog;
    private Intent intent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    /**
     * initilization
     */
    private synchronized void init() {
        if (!Util.isNetworkAvailable(SplashActivity.this)) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                    .setMessage(R.string.error_internet_not_connected)
                    .setPositiveButton(R.string.text_retry, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            init();
                        }
                    })
                    .setNegativeButton(getString(R.string.text_exit), new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    })
                    .show();
            return;
        }
        if (!checkPlayServices()) {
            return;
        }
        MyFirebaseInstanceIdService.setCallback(this);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SCREEN_OVERLAY) {
            if (Settings.canDrawOverlays(this)) {
                init();
            }
        } else if (requestCode == REQ_CODE_PLAY_SERVICES_RESOLUTION
                && resultCode == Activity.RESULT_OK) {
            init();
        } else if (requestCode == REQ_CODE_LOGIN && resultCode == RESULT_OK) {
            callApiCheck();
        }
    }

    /**
     * @return true if google play service present & updated
     * false if not presented or not updated
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, REQ_CODE_PLAY_SERVICES_RESOLUTION)
                        .show();
            } else {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                        .setMessage(R.string.error_device_not_supported)
                        .setPositiveButton(R.string.text_ok,
                                new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick() {
                                        finish();
                                    }
                                })
                        .show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onTokenReceived(final String token) {
        //Log.e("debug", token);
        checkToken();

    }


    /**
     * check token
     */
    public void checkToken() {
        if (Paper.book().read(ACCESS_TOKEN) != null) {
            Log.e("debug", "OTP Activity");
            callApiCheck();
        } else {
            Log.e("debug", "LoginActivity");
            //Log.e("debug", (String) Paper.book().read(ACCESS_TOKEN));
            delay(DELAY_CODE);
            startLoginActivity();

        }
    }


    /**
     * check api call which activity next activate
     */
    private void callApiCheck() {
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
    }

    /**
     * intent Login Activity
     */
    private void startLoginActivity() {
        intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivityForResult(intent, REQ_CODE_LOGIN);
    }

    @Override
    public void onFailure() {
        if (isFinishing()) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /**
                 * in failure make another attempt to get device token
                 * or finish activity which in turn finish application.
                 */
                MyFirebaseInstanceIdService.retry(SplashActivity.this);
            }
        });
    }

    /**
     * @return boolean value of permission overlay
     */
    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQ_CODE_SCREEN_OVERLAY);
            return false;
        } else {
            return true;
        }
    }


}
