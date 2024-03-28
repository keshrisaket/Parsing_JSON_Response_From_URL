package com.example.parsingjsonresponsefromurl;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

////public class JsonParser extends AsyncTask<String,String,Void> {
////    public static  String result="";
////    BufferedInputStream inputStream;
////    JSONArray jsonArray;
//////    ProgressDialog progressDialog;
////    Activity activity;
////    Context context;
////
////    public  JsonParser(Activity activity,Context context){
////        this.activity=activity;
////        this.context=context;
//////        this.progressDialog=new ProgressDialog(this.context);
////    }
////
//////    @Override
//////    protected void onPreExecute() {
//////        super.onPreExecute();
//////        progressDialog.setMessage("Loading....");
//////        progressDialog.show();
//////    }
//////
//////
//////    @Override
//////    protected void onCancelled() {
//////        super.onCancelled();
//////        // Handle task cancellation here
//////    }
////
////
//////    @Override
//////    protected Void doInBackground(String... strings) {
//////        HttpURLConnection httpURLConnection;
//////
//////        try {
//////            URL url=new URL(Url.fetchdata);
//////            httpURLConnection= (HttpURLConnection) url.openConnection();
//////            inputStream=new BufferedInputStream(httpURLConnection.getInputStream());
//////            result=readStream();
//////
//////            System.out.println(result);
//////        } catch (MalformedURLException e) {
//////            throw new RuntimeException(e);
//////        } catch (IOException e) {
//////            throw new RuntimeException(e);
//////        }
//////
//////
//////        return null;
//////    }
////
////
////    @Override
////    protected Void doInBackground(String... strings) {
////        HttpURLConnection httpURLConnection = null;
////        try {
////            URL url = new URL(Url.fetchdata);
////            httpURLConnection = (HttpURLConnection) url.openConnection();
////            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
////            result = readStream();
////            System.out.println(result);
////        } catch (MalformedURLException e) {
////            // Handle MalformedURLException gracefully
////            e.printStackTrace();
////        } catch (IOException e) {
////            // Handle IOException gracefully
////            e.printStackTrace();
////        } finally {
////            if (httpURLConnection != null) {
////                httpURLConnection.disconnect();
////            }
////        }
////        return null;
////    }
////
////    private  String readStream() throws  IOException{
////        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
////        StringBuilder stringBuilder=new StringBuilder();
////        String line;
////       try {
////
////
////           while ((line = bufferedReader.readLine()) != null) {
////               stringBuilder.append(line);
////           }
////
////       }catch (Exception ex){
////           ex.printStackTrace();
////       }
////
////       return stringBuilder.toString();
////    }
////
////
////
////}
//
//
//
//// Import statements...
//
//public class JsonParser extends AsyncTask<Void, Void, String> {
//
//
//    private Context context;
//
//    private  static  String result="";
//    public JsonParser(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    protected String doInBackground(Void... voids) {
//        HttpURLConnection httpURLConnection = null;
//        result = "";
//
//        try {
//            URL url = new URL(Url.fetchdata);
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//            result = readStream(inputStream);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (httpURLConnection != null) {
//                httpURLConnection.disconnect();
//            }
//        }
//
//        return result;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        // Process the fetched JSON data (e.g., parse JSON string)
//        // Update UI or pass the data to the caller using callbacks
//
//        ArrayList<String> arrayList=new ArrayList<>();
//
//
//
//        try {
//            JSONArray jsonArray=new JSONArray(JsonParser.result);
//
//            if (jsonArray!=null) {
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    String s = jsonObject.getString("vendorName");
//                    arrayList.add(s);
//                }
//
//            }
//
//            System.out.println(jsonArray);
//
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    private String readStream(InputStream inputStream) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//
//        try {
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//        } finally {
//            inputStream.close(); // Close the InputStream to prevent resource leak
//        }
//
//        return stringBuilder.toString();
//    }
//}




// Import statements...

public class JsonParser extends AsyncTask<Void, Void, String> {

    private Context context;
    Activity activity;

    public ProgressDialog progressDialog;
    private static final String TAG = JsonParser.class.getSimpleName();

    public JsonParser(Context context,Activity activity) {
        this.context = context;
        this.activity=activity;
        this.progressDialog=new ProgressDialog(this.context);
    }


    @Override
    protected  void onPreExecute(){
        super.onPreExecute();
        progressDialog.dismiss();
        progressDialog.setMessage("Loading>>>");
        progressDialog.show();
    }




    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection httpURLConnection = null;
        String result = "";

        try {
            URL url = new URL(Url.fetchdata);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            result = readStream(inputStream);

        } catch (IOException e) {
            Log.e(TAG, "Error fetching JSON data", e);
            result = ""; // Return empty string as result
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the fetched JSON data (e.g., parse JSON string)
        // Update UI or pass the data to the caller using callbacks

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("ram");

        try {
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String s = jsonObject.getString("name");
                arrayList.add(s);
            }
            progressDialog.dismiss();

            // Example: Update ListView with arrayList

            ListView listView;
            listView=(ListView)this.activity.findViewById(R.id.list);
            ArrayAdapter<String> arradp=new ArrayAdapter<>(activity.getApplication(), android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arradp);


        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON data", e);
            // Display error message to the user or handle the error gracefully
        }
    }

    private String readStream(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        }



    }
}
