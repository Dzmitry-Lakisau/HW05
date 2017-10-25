package by.dzmitry_lakisau.hw05;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import by.dzmitry_lakisau.hw05.backend.updateApi.UpdateApi;
import by.dzmitry_lakisau.hw05.backend.updateApi.model.Update;


public class GetUpdateInfoTask extends AsyncTask<Context, Void, String[]> {

    private final static String BACKEND_URL = "https://hw05-666666.appspot.com/_ah/api";

    private Activity mContext;
    private static UpdateApi myApiService = null;
    private Update update;

    @Override
    protected String[] doInBackground(Context... context) {

        mContext = (Activity) context[0];

        if(myApiService == null){  // Only do this once
        UpdateApi.Builder builder = new UpdateApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl(BACKEND_URL)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        myApiService = builder.build();
    }
    try{
        update = myApiService.getUpdate().execute();
        String[] result = {String.valueOf(update.getForceUpdate()), String.valueOf(update.getVersion())};
        return result;
    } catch(IOException e)
    {
        return new String[]{e.getMessage()};
    }
}

    @Override
    protected void onPostExecute(String[] result) {
        if (result.length == 1) {
            Toast.makeText(mContext, result[0], Toast.LENGTH_LONG).show();
        } else {


            boolean forceUpdate = Boolean.parseBoolean(result[0]);
            int version = Integer.parseInt(result[1]);

            if (version > BuildConfig.VERSION_CODE) {
                DialogFragment dialogFragment = new DialogFragment();
                Bundle args = new Bundle();
                args.putBoolean("force_update", forceUpdate);
                dialogFragment.setArguments(args);
                dialogFragment.show(mContext.getFragmentManager(), "DialogFragment");
            }
        }
    }
}
