package info.krushik.android.jsonmedialib.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import info.krushik.android.jsonmedialib.R;
import info.krushik.android.jsonmedialib.model.VideoList;
import info.krushik.android.jsonmedialib.view.adapter.VideoAdapter;
import info.krushik.android.jsonmedialib.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String SAVE_INSTANCE = "instance";
    private Context context;
    private ProgressBar progressBar;
    private VideoAdapter videoAdapter;
    private RecyclerView recyclerView;
    private VideoList videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initFields();
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            videoList = (VideoList) savedInstanceState.get(SAVE_INSTANCE);
            setItemsToList(videoList);
        }
    }

    public void getVideo(View view) {

        progressBar.setVisibility(ProgressBar.VISIBLE);
        recyclerView.setVisibility(ProgressBar.GONE);


        RetrofitService.getApi().getListVideo().enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                videoList = response.body();
                setItemsToList(response.body());
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {
                if (videoList != null) {
                    setItemsToList(videoList);
                }
                hideProgressBar();
            }
        });
    }

    private void setItemsToList(VideoList currentVideo) {
        if (!recyclerView.isShown()) {
            recyclerView.setVisibility(View.VISIBLE);
        }
        videoAdapter = new VideoAdapter(currentVideo.getList());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(videoAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (videoList != null) {
            outState.putParcelable(SAVE_INSTANCE, videoList);
        }
    }

    private void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    private void initFields() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refreshVideo:
                getVideo(recyclerView);
                return true;
            case R.id.refreshBook:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
