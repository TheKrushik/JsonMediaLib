package info.krushik.android.jsonmedialib.retrofit;

import info.krushik.android.jsonmedialib.model.BookList;
import info.krushik.android.jsonmedialib.model.VideoList;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

        @GET("TheKrushik/JsonMediaLib/master/Video.txt")
        Call<VideoList> getListVideo();


        @GET("/TheKrushik/JsonMediaLib/master/Book.txt")
        Call<BookList> getListBook();
}
