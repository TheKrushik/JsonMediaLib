package info.krushik.android.jsonmedialib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_down_video:
                new JSONTaskVideo().execute("http://...");
                return true;
            case R.id.action_down_book:
                new JSONTaskBook().execute("http://...");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
