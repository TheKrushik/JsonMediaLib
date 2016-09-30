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
import info.krushik.android.jsonmedialib.model.BookList;
import info.krushik.android.jsonmedialib.model.VideoList;
import info.krushik.android.jsonmedialib.view.adapter.BookAdapter;
import info.krushik.android.jsonmedialib.view.adapter.VideoAdapter;
import info.krushik.android.jsonmedialib.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String SAVE_INSTANCE = "instance";
    private static String SAVE_LAYOUT;
    private Context context;
    private ProgressBar progressBar;
    private VideoAdapter videoAdapter;
    private RecyclerView recyclerViewVideo;
    private VideoList videoList;
    private BookAdapter bookAdapter;
    private RecyclerView recyclerViewBook;
    private BookList bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initFields();
    }

    public void getVideo() {
        recyclerViewVideo.setVisibility(ProgressBar.GONE);
        RetrofitService.getApi().getListVideo().enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                videoList = response.body();
                setItemsToListVideo(response.body());
                hideProgressBar();
            }
            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {
                if (videoList != null) {
                    setItemsToListVideo(videoList);
                }
                hideProgressBar();
            }
        });
    }

    public void getBook() {
        recyclerViewBook.setVisibility(ProgressBar.GONE);
        RetrofitService.getApi().getListBook().enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                bookList = response.body();
                setItemsToListBook(response.body());
                hideProgressBar();
            }
            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                if (bookList != null) {
                    setItemsToListBook(bookList);
                }
                hideProgressBar();
            }
        });
    }

    private void setItemsToListVideo(VideoList currentVideo) {
        if (!recyclerViewVideo.isShown()) {
            recyclerViewVideo.setVisibility(View.VISIBLE);
        }
        videoAdapter = new VideoAdapter(currentVideo.getList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewVideo.setLayoutManager(layoutManager);
        recyclerViewVideo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVideo.setAdapter(videoAdapter);
    }

    private void setItemsToListBook(BookList currentBook) {
        if (!recyclerViewBook.isShown()) {
            recyclerViewBook.setVisibility(View.VISIBLE);
        }
        bookAdapter = new BookAdapter(currentBook.getList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewBook.setLayoutManager(layoutManager);
        recyclerViewBook.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBook.setAdapter(bookAdapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (SAVE_LAYOUT.equals("videoList")) {
            videoList = (VideoList) savedInstanceState.get(SAVE_INSTANCE);
            setItemsToListVideo(videoList);
        }
        if (SAVE_LAYOUT.equals("bookList")) {
            bookList = (BookList) savedInstanceState.get(SAVE_INSTANCE);
            setItemsToListBook(bookList);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (videoList != null) {
            outState.putParcelable(SAVE_INSTANCE, videoList);
            SAVE_LAYOUT = "videoList";
        }
        if (bookList != null) {
            outState.putParcelable(SAVE_INSTANCE, bookList);
            SAVE_LAYOUT = "bookList";
        }
        super.onSaveInstanceState(outState);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    private void initFields() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerViewVideo = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewBook = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        switch (item.getItemId()) {
            case R.id.refreshVideo:
                bookList = null;
                getVideo();
                return true;
            case R.id.refreshBook:
                videoList = null;
                getBook();
                return true;
        }
        hideProgressBar();
        return super.onOptionsItemSelected(item);
    }
}
