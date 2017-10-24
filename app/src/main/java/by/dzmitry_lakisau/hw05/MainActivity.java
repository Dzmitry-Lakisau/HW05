package by.dzmitry_lakisau.hw05;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean mForceUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runThread_btn = (Button) findViewById(R.id.button);
        runThread_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caller caller = new Caller(new ThreadRunner());
                caller.doSomethingAsynchronously();
            }
        });

        ServletGetAsyncTask s = new ServletGetAsyncTask(this);
        s.execute();
    }
}
