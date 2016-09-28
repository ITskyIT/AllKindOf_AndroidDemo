package com.tian.app.daydaystudy.interfaces;

import com.tian.app.daydaystudy.bean.CookGson;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jiujiu on 2016/9/22.
 */
public interface APIService {
    /*  @GET("/cook/category")
      Call<CookGson> getList(@Query("key")String key);*/
    @POST("/cook/category")
    @FormUrlEncoded
    Call<CookGson> getList(@Field("key") String key);
}
