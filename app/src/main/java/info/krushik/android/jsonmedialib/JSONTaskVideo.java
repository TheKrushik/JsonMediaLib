package info.krushik.android.jsonmedialib;

import android.os.AsyncTask;

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

import info.krushik.android.jsonmedialib.models.VideoModel;

public class JSONTaskVideo extends AsyncTask<String, String, List<VideoModel> >{

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
            while ((line = reader.readLine()) != null){
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

        // нужно отобразить данные в списке
    }
}
