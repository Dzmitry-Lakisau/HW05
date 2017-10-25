package by.dzmitry_lakisau.hw05;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private boolean mForceUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        int versionCode = BuildConfig.VERSION_CODE;

        Button runThread_btn = (Button) findViewById(R.id.button);
        runThread_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caller caller = new Caller(new ThreadRunner());
                caller.doSomethingAsynchronously();
            }
        });

        Button btn1 = (Button) findViewById(R.id.fu_true_v_2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Build name data request params
                    Map<String, String> nameValuePairs = new HashMap<>();
                    nameValuePairs.put(Constants.FORCE_UPDATE, "true");
                    nameValuePairs.put(Constants.VERSION, "2");
                    String postParams = buildPostDataString(nameValuePairs);
                    ServletPostAsyncTask servletPostAsyncTask = new ServletPostAsyncTask();
                    servletPostAsyncTask.execute(new Pair<Context, String>(getApplicationContext(), postParams));
                } catch (UnsupportedEncodingException exc){
                    Toast.makeText(getApplicationContext(), exc.toString(), Toast.LENGTH_LONG);
                }
            }
        });

        Button btn2 = (Button) findViewById(R.id.fu_false_v_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Build name data request params
                    Map<String, String> nameValuePairs = new HashMap<>();
                    nameValuePairs.put(Constants.FORCE_UPDATE, "false");
                    nameValuePairs.put(Constants.VERSION, "2");
                    String postParams = buildPostDataString(nameValuePairs);
                    ServletPostAsyncTask servletPostAsyncTask = new ServletPostAsyncTask();
                    servletPostAsyncTask.execute(new Pair<Context, String>(getApplicationContext(), postParams));
                } catch (UnsupportedEncodingException exc){
                    Toast.makeText(getApplicationContext(), exc.toString(), Toast.LENGTH_LONG);
                }
            }
        });

        Button btn3 = (Button) findViewById(R.id.fu_true_v_1);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Build name data request params
                    Map<String, String> nameValuePairs = new HashMap<>();
                    nameValuePairs.put(Constants.FORCE_UPDATE, "true");
                    nameValuePairs.put(Constants.VERSION, "1");
                    String postParams = buildPostDataString(nameValuePairs);
                    ServletPostAsyncTask servletPostAsyncTask = new ServletPostAsyncTask();
                    servletPostAsyncTask.execute(new Pair<Context, String>(getApplicationContext(), postParams));
                } catch (UnsupportedEncodingException exc){
                    Toast.makeText(getApplicationContext(), exc.toString(), Toast.LENGTH_LONG);
                }
            }
        });

        Button btn4 = (Button) findViewById(R.id.fu_false_v_1);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Build name data request params
                    Map<String, String> nameValuePairs = new HashMap<>();
                    nameValuePairs.put(Constants.FORCE_UPDATE, "false");
                    nameValuePairs.put(Constants.VERSION, "1");
                    String postParams = buildPostDataString(nameValuePairs);
                    ServletPostAsyncTask servletPostAsyncTask = new ServletPostAsyncTask();
                    servletPostAsyncTask.execute(new Pair<Context, String>(getApplicationContext(), postParams));
                } catch (UnsupportedEncodingException exc){
                    Toast.makeText(getApplicationContext(), exc.toString(), Toast.LENGTH_LONG);
                }
            }
        });

        ServletGetAsyncTask s = new ServletGetAsyncTask(this);
        s.execute();
    }

    private String buildPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
