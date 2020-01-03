package com.t3h.immunization.api;
import com.t3h.immunization.model.BaByRespone;
import com.t3h.immunization.model.ResponeLogin;
import com.t3h.immunization.model.ResponeRegister;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("/register")
    @FormUrlEncoded
    Call<ResponeRegister> register(@Field("username") String user_name,
                                   @Field("password") String password,
                                   @Field("device") String device,
                                   @Field("name") String name,
                                   @Field("phone")String phone,
                                   @Field("email") String email,
                                   @Field("dob") String dob,
                                   @Field("platform") String platform);


    @POST("/login")
    @FormUrlEncoded
    Call<ResponeLogin> login(@Field("username") String userName,
                             @Field("password") String password);

    @POST("/forgot")
    @FormUrlEncoded
    Call<ResponeRegister> forgot (@Field("email") String email);

    @POST("verifycode/")
    @FormUrlEncoded
    Call<ResponeRegister> verifycode (@Field("verifyCode") String verifyCode);

    @POST("/changepass/")
    @FormUrlEncoded
    Call<ResponeRegister> changepass (@Field("password") String password);

    @POST("/getbaby")
    @FormUrlEncoded
    Call<BaByRespone> getBaBy(@Field("id") String id);

    @POST("/addbaby")
    @FormUrlEncoded
    Call<ResponeRegister> addBaby(@Field("id") String id,
                                  @Field("name") String name,
                                  @Field("gender") String gender,
                                  @Field("birthday") String birthday,
                                  @Field("link_avatar") String link_avatar,
                                  @Field("note") String note,
                                  @Field("isSavedOnServer") boolean isSavedOnServer);

    @POST("/editbaby")
    @FormUrlEncoded
    Call<ResponeRegister> editBaby(@Field("id") String id,
                                   @Field("baby_id") String baby_id);

    @POST("/deletebaby")
    @FormUrlEncoded
    Call<ResponeRegister> deleteBaby(@Field("baby_id") String  baby_id);

}
