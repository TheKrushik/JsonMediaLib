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
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {

            videoList = (VideoList) savedInstanceState.get(SAVE_INSTANCE);
            setItemsToListVideo(videoList);

//            bookList = (BookList) savedInstanceState.get(SAVE_INSTANCE);
//            setItemsToListBook(bookList);
        }
    }

    public void getVideo(View view) {

        progressBar.setVisibility(ProgressBar.VISIBLE);
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

    public void getBook(View view) {

        progressBar.setVisibility(ProgressBar.VISIBLE);
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
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerViewVideo.setLayoutManager(mLayoutManager);
        recyclerViewVideo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVideo.setAdapter(videoAdapter);
    }

    private void setItemsToListBook(BookList currentBook) {
        if (!recyclerViewBook.isShown()) {
            recyclerViewBook.setVisibility(View.VISIBLE);
        }
        bookAdapter = new BookAdapter(currentBook.getList());
        RecyclerView.LayoutManager mLayoutManagerB = new LinearLayoutManager(context);
        recyclerViewBook.setLayoutManager(mLayoutManagerB);
        recyclerViewBook.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBook.setAdapter(bookAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (videoList != null) {
            outState.putParcelable(SAVE_INSTANCE, videoList);
        }
//        if (bookList != null) {
//            outState.putParcelable(SAVE_INSTANCE, bookList);
//        }
    }

    private void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.GONE);
    }

    private void initFields() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerViewVideo = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewBook = (RecyclerView) findViewById(R.id.recycler_view);
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
//                bookList = null;
                getVideo(recyclerViewVideo);
                return true;
            case R.id.refreshBook:
//                videoList = null;
                getBook(recyclerViewBook);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
