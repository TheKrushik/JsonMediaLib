package info.krushik.android.jsonmedialib;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import info.krushik.android.jsonmedialib.models.BookModel;
import info.krushik.android.jsonmedialib.models.VideoModel;

public class MainActivity extends AppCompatActivity {

    private static String HTTP_VIDEO = "https://raw.githubusercontent.com/TheKrushik/JsonMediaLib/master/Video.txt";
    private static String HTTP_BOOK = "https://raw.githubusercontent.com/TheKrushik/JsonMediaLib/master/Book.txt";


    private ListView lvModel;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        lvModel = (ListView) findViewById(R.id.lvModel);

    }

    public class JSONTaskVideo extends AsyncTask<String, String, List<VideoModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<VideoModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("list");

                List<VideoModel> videoModelList = new ArrayList<>();

                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    VideoModel videoModel = new VideoModel();
                    videoModel.setTitle(finalObject.getString("title"));
                    videoModel.setPicture(finalObject.getString("picture"));
                    videoModel.setDesc(finalObject.getString("desc"));
                    videoModel.setLength(finalObject.getString("length"));
                    videoModel.setDt(finalObject.getString("dt"));
                    videoModel.setVideo(finalObject.getString("video"));
                    // adding the final object in the list
                    videoModelList.add(videoModel);

                }
                return videoModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<VideoModel> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            VideoAdapter adapter = new VideoAdapter(getApplicationContext(), R.layout.row_video, result);
            lvModel.setAdapter(adapter);

        }
    }


    public class VideoAdapter extends ArrayAdapter {

        private List<VideoModel> videoModelList;
        private int resource;
        private LayoutInflater inflater;

        public VideoAdapter(Context context, int resource, List<VideoModel> objects) {
            super(context, resource, objects);
            videoModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolderVideo holder = null;

            if (convertView == null) {
                holder = new ViewHolderVideo();
                convertView = inflater.inflate(resource, null);
                holder.ivPictureVideo = (ImageView) convertView.findViewById(R.id.ivPictureVideo);
                holder.tvTitleVideo = (TextView) convertView.findViewById(R.id.tvTitleVideo);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
                holder.tvLength = (TextView) convertView.findViewById(R.id.tvLength);
                holder.tvDt = (TextView) convertView.findViewById(R.id.tvDt);
                holder.tvVideoHttp = (TextView) convertView.findViewById(R.id.tvVideoHttp);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolderVideo) convertView.getTag();
            }

            final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

            // Then later, when you want to display image
            ImageLoader.getInstance().displayImage(videoModelList.get(position).getPicture(), holder.ivPictureVideo, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });

            holder.tvTitleVideo.setText(videoModelList.get(position).getTitle());
            holder.tvDesc.setText(videoModelList.get(position).getDesc());
            holder.tvLength.setText("Длинна видео: " + videoModelList.get(position).getLength());
            holder.tvDt.setText("Дата: " + videoModelList.get(position).getDt());
            holder.tvVideoHttp.setText(videoModelList.get(position).getVideo());


            return convertView;
        }

        class ViewHolderVideo {
            private ImageView ivPictureVideo;
            private TextView tvTitleVideo;
            private TextView tvDesc;
            private TextView tvLength;
            private TextView tvDt;
            private TextView tvVideoHttp;

        }
    }


    public class JSONTaskBook extends AsyncTask<String, String, List<BookModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<BookModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("list");

                List<BookModel> bookModelList = new ArrayList<>();

                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    BookModel bookModel = new BookModel();
                    bookModel.setTitle(finalObject.getString("title"));
                    bookModel.setАuthor(finalObject.getString("аuthor"));
                    bookModel.setAnons(finalObject.getString("anons"));
                    bookModel.setPicture(finalObject.getString("picture"));

                    List<BookModel.Files> filesList = new ArrayList<>();
                    for (int j = 0; j < finalObject.getJSONArray("files").length(); j++) {
                        BookModel.Files files = new BookModel.Files();
                        files.setType(finalObject.getJSONArray("files").getJSONObject(j).getString("type"));
                        files.setUrl(finalObject.getJSONArray("files").getJSONObject(j).getString("url"));
                        filesList.add(files);
                    }
                    bookModel.setFilesList(filesList);

                    // adding the final object in the list
                    bookModelList.add(bookModel);

                }
                return bookModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<BookModel> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            BookAdapter adapter = new BookAdapter(getApplicationContext(), R.layout.row_book, result);
            lvModel.setAdapter(adapter);
        }
    }


    public class BookAdapter extends ArrayAdapter {

        private List<BookModel> bookModelList;
        private int resource;
        private LayoutInflater inflater;

        public BookAdapter(Context context, int resource, List<BookModel> objects) {
            super(context, resource, objects);
            bookModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolderBook holder = null;

            if (convertView == null) {
                holder = new ViewHolderBook();
                convertView = inflater.inflate(resource, null);
                holder.ivPictureBook = (ImageView) convertView.findViewById(R.id.ivPictureBook);
                holder.tvTitleBook = (TextView) convertView.findViewById(R.id.tvTitleBook);
                holder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
                holder.tvAnons = (TextView) convertView.findViewById(R.id.tvAnons);
                holder.tvTypePdf = (TextView) convertView.findViewById(R.id.tvTypePdf);
                holder.tvUrlPdf = (TextView) convertView.findViewById(R.id.tvUrlPdf);
                holder.tvTypeEpub = (TextView) convertView.findViewById(R.id.tvTypeEpub);
                holder.tvUrlEpub = (TextView) convertView.findViewById(R.id.tvUrlEpub);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolderBook) convertView.getTag();
            }

            final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

            // Then later, when you want to display image
            ImageLoader.getInstance().displayImage(bookModelList.get(position).getPicture(), holder.ivPictureBook, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });

            holder.tvTitleBook.setText(bookModelList.get(position).getTitle());
            holder.tvAuthor.setText("Автор: " + bookModelList.get(position).getАuthor());
            holder.tvAnons.setText(bookModelList.get(position).getAnons());

            StringBuffer stringBuffer = new StringBuffer();
            for (BookModel.Files files : bookModelList.get(position).getFilesList()) {
                stringBuffer.append(files.getType());

            }
            Log.e("MyLog", stringBuffer.toString());
            holder.tvTypePdf.setText(stringBuffer);

//            holder.tvTypePdf.setText(bookModelList.get(position).getFilesList().get());
//            holder.tvUrlPdf.setText(bookModelList.get(position).getVideo());
//            holder.tvTypeEpub.setText(bookModelList.get(position).getVideo());
//            holder.tvUrlEpub.setText(bookModelList.get(position).getVideo());


            return convertView;
        }

        class ViewHolderBook {
            private ImageView ivPictureBook;
            private TextView tvTitleBook;
            private TextView tvAuthor;
            private TextView tvAnons;
            private TextView tvTypePdf;
            private TextView tvUrlPdf;
            private TextView tvTypeEpub;
            private TextView tvUrlEpub;

        }
    }



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
