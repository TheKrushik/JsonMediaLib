package info.krushik.android.jsonmedialib.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitService {

    private static String BASE_URL = "https://raw.githubusercontent.com/";
    private static ApiInterface service;
    private static Retrofit retrofit;

    public static ApiInterface getApi() {
        if (service == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ApiInterface.class);
            return service;
        } else {
            return service;
        }
    }
}

