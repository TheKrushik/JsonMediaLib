package info.krushik.android.jsonmedialib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    static TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHitVideo = (Button) findViewById(R.id.btnHitVideo);
        Button btnHitBook = (Button) findViewById(R.id.btnHitBook);
        tvData = (TextView) findViewById(R.id.tvJsonItem);

        btnHitVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTaskVideo().execute("http://fs152.www.ex.ua/get/e1f2711ed80af4961b98f790b17ecec4/272131896/Video.txt");
            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_down_video:
//                new JSONTaskVideo().execute("http://fs152.www.ex.ua/get/e1f2711ed80af4961b98f790b17ecec4/272131896/Video.txt");
//                return true;
//            case R.id.action_down_book:
//                new JSONTaskBook().execute("http://fs43.www.ex.ua/get/4f456ba3e702de7ab255988235ca7a43/272133377/Book.txt");
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
