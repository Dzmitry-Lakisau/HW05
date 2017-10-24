package by.dzmitry_lakisau.hw05;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class ServletGetAsyncTask extends AsyncTask<Void, Void, String> {

    private final Activity mContext;

    public ServletGetAsyncTask(Activity context){
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL(Constants.SERVLET_URL + "/update");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
//            connection.setDoOutput(true);
            connection.connect();

            String temp = connection.getRequestMethod();

            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                return response.toString();
            }
            return "Error: " + responseCode + " " + connection.getResponseMessage();

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        boolean mForceUpdate = false;
        int version = 1;

//        result = "{" +
//                "\"force_update\": \"false\"," +
//                "\"version\": \"2\"" +
//                "}";

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            mForceUpdate = jsonObject.getBoolean("force_update");
            version = jsonObject.getInt("version");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (version > BuildConfig.VERSION_CODE) {
            DialogFragment dialogFragment = new DialogFragment();
            Bundle args = new Bundle();
            args.putBoolean("force_update", mForceUpdate);
            dialogFragment.setArguments(args);
            dialogFragment.show(mContext.getFragmentManager(), "DialogFragment");
        }
    }
}