//package info.krushik.android.jsonmedialib;
//
//import android.os.AsyncTask;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import info.krushik.android.jsonmedialib.models.BookModel;
//
//public class JSONTaskBook extends AsyncTask<String, String, List<BookModel>>{
//
//    @Override
//    protected List<BookModel> doInBackground(String... params) {
//        HttpURLConnection connection = null;
//        BufferedReader reader = null;
//        try {
//            URL url = new URL(params[0]);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.connect();
//            InputStream stream = connection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(stream));
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            while ((line = reader.readLine()) != null){
//                buffer.append(line);
//            }
//
//            String finalJson = buffer.toString();
//
//            JSONObject parentObject = new JSONObject(finalJson);
//            JSONArray parentArray = parentObject.getJSONArray("list");
//
//            List<BookModel> bookModelList = new ArrayList<>();
//
//            for (int i = 0; i < parentArray.length(); i++) {
//                JSONObject finalObject = parentArray.getJSONObject(i);
//                BookModel bookModel = new BookModel();
//                bookModel.setTitle(finalObject.getString("title"));
//                bookModel.setАuthor(finalObject.getString("аuthor"));
//                bookModel.setAnons(finalObject.getString("anons"));
//                bookModel.setPicture(finalObject.getString("picture"));
//
//                //внутренний масив Json
//                List<BookModel.Files> filesList = new ArrayList<>();
//                for (int j = 0; j < finalObject.getJSONArray("files").length(); j++) {
//                    JSONObject fileObject = finalObject.getJSONArray("files").getJSONObject(j);
//                    BookModel.Files files = new BookModel.Files();
//                    files.setType(fileObject.getString("type"));
//                    files.setUrl(fileObject.getString("url"));
//                    filesList.add(files);
//                }
//                bookModel.setFilesList(filesList);
//                //добавление финального объекта в список
//                bookModelList.add(bookModel);
//            }
//
//            return bookModelList;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(List<BookModel> result) {
//        super.onPostExecute(result);
//        // нужно отобразить данные в списке
//    }
//}
