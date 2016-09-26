package info.krushik.android.jsonmedialib.retrofit;

import info.krushik.android.jsonmedialib.model.VideoList;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

//        @POST("/api/v1/rates/get/bynumber")
//        Call<VideoList> getListVideo(@Body RequestUser requestUser);

        @GET("TheKrushik/JsonRetrofitRx/master/Video.txt")
        Call<VideoList> getListVideo();
}
