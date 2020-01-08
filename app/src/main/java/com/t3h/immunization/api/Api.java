package com.t3h.immunization.api;
import com.t3h.immunization.model.BaByRespone;
import com.t3h.immunization.model.ResponeInjections;
import com.t3h.immunization.model.ResponeLogin;
import com.t3h.immunization.model.ResponeRegister;
import com.t3h.immunization.model.UpdateApp;
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
    Call<BaByRespone> getBaBy(@Field("id") int id);

    @POST("/addbaby")
    @FormUrlEncoded
    Call<ResponeRegister> addBaby(@Field("id") int id,
                                  @Field("name") String name,
                                  @Field("gender") String gender,
                                  @Field("birthday") String birthday,
                                  @Field("link_avatar") String link_avatar,
                                  @Field("note") String note,
                                  @Field("isSavedOnServer") boolean isSavedOnServer);

    @POST("/editbaby")
    @FormUrlEncoded
    Call<ResponeRegister> editBaby(@Field("id") int id,
                                   @Field("baby_id") int baby_id,
                                   @Field("name") String name,
                                   @Field("gender") String gender,
                                   @Field("birthday") String birthday,
                                   @Field("link_avatar") String link_avatar,
                                   @Field("note") String note);



    @POST("/deletebaby")
    @FormUrlEncoded
    Call<ResponeRegister> deleteBaby(@Field("baby_id") int baby_id);


    @POST("/getinjected")
    @FormUrlEncoded
    Call<ResponeInjections> getinjected(@Field("baby_id") int baby_id);

    @POST("/vacxininfo")
    @FormUrlEncoded
    Call<ResponeInjections> getVaccine (@Field("language") String language);

    @POST("/updateinjected")
    @FormUrlEncoded
    Call<ResponeInjections> updateInjections (@Field("baby_id") String baby_id,
                                              @Field("id") String id,
                                              @Field("user_id")String user_id,
                                              @Field("note")String note,
                                              @Field("injected_date")String injected_date,
                                              @Field("medicine")String medicine,
                                              @Field("isInjected")String isInjected);

    @POST("/updateapp")
    @FormUrlEncoded
    Call<UpdateApp> updateApp (@Field("platform") String platform,
                               @Field("version") int version);


}
