package com.skeleton.retrofit;


import com.skeleton.model.Response;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;

import static com.skeleton.constant.ApiKeyConstant.AUTHORIZATION;

/**
 * Developer: Sumit Thakur
 * Dated: 27-09-2016.
 */
public interface ApiInterface {
    String UPDATE_LOCATION = "api/v1/user/updateLocation";
    String USER_SIGNUP = "api/user/register";
    String USER_LOGIN = "api/user/login";
    String USER_GET_PROFILE = "api/user/getProfile";
    String USER_OTP_CHECK = "api/user/verifyOTP";
    String USER_OTP_RESEND = "api/user/resendOTP";

    /**
     * @param authorization auth
     * @param map           map
     * @return Post User_Login
     */
    @POST(USER_LOGIN)
    Call<CommonResponse> userLogin(@Header(AUTHORIZATION) String authorization,
                                   @Body HashMap<String, String> map);

    /**
     * @param map map
     * @return return response
     */
    @Multipart
    @POST(USER_SIGNUP)
    Call<Response> userRegister(@PartMap HashMap<String, RequestBody> map);

    /**
     * @param key key
     * @return return
     */
    @GET(USER_GET_PROFILE)
    Call<Response> getProfile(@Header(AUTHORIZATION) String key);

    /**
     * @param key authorization key
     * @return resend otp
     */
    @GET(USER_OTP_RESEND)
    Call<Response> resendOTP(@Header(AUTHORIZATION) String key);


    /**
     * @param authorization access token
     * @param map           Hashmap
     * @return user otp check correct or not
     */
    @PUT(USER_OTP_CHECK)
    Call<Response> otpVerify(@Header(AUTHORIZATION) String authorization,
                             @Body HashMap<String, String> map);

//    /**
//     * @param map
//     * @return
//     */
//    @Multipart
//    @POST("api/v1/user/createUser")
//    Call<LoginResponse> register(@PartMap HashMap<String, RequestBody> map);
//
//
//    /**
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/socialLogin")
//    Call<LoginResponse> socialLogin(@FieldMap HashMap<String, String> map);
//
//    /**
//     * @param authorization
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/loginToken")
//    Call<LoginResponse> accessTokenLogin(@Header(AUTHORIZATION) String authorization,
//                                         @FieldMap HashMap<String, String> map);
//
//
//    /**
//     * @param email
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/v1/user/forgotpassword")
//    Call<CommonResponse> forgotPassword(@Field("email") String email);
//
//    /**
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/loginCredential")
//    Call<LoginResponse> login(@FieldMap HashMap<String, String> map);


    /**
     * Update location call.
     *
     * @param authorization the authorization
     * @param map           the map
     * @return the call
     */
    @FormUrlEncoded
    @POST(UPDATE_LOCATION)
    Call<CommonParams> updateLocation(@Header(AUTHORIZATION) String authorization,
                                      @FieldMap HashMap<String, String> map);
}

