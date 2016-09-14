package info.krushik.android.jsonmedialib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String HTTP_VIDEO = "https://raw.githubusercontent.com/TheKrushik/JsonMediaLib/master/Video.txt";
    private static String HTTP_BOOK = "https://raw.githubusercontent.com/TheKrushik/JsonMediaLib/master/Book.txt";

    ListView lvModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvModel = (ListView) findViewById(R.id.lvModel);
    }

//    public void OnClick(View view){
//        switch (view.getId()) {
//            case R.id.btnHitVideo:
//                new JSONTaskVideo().execute("https://raw.githubusercontent.com/TheKrushik/JsonMediaLib/master/Video.txt");
//                break;
//            case R.id.btnHitBook:
//                new JSONTaskBook().execute("https://raw.githubusercontent.com/TheKrushik/JsonMediaLib/master/Book.txt");
//                break;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh_video:
                new JSONTaskVideo().execute(HTTP_VIDEO);
                return true;
            case R.id.action_refresh_book:
                new JSONTaskBook().execute(HTTP_BOOK);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
