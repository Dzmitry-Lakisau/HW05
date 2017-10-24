package by.dzmitry_lakisau.hw05;

import java.util.Collections;

public class ThreadRunner {

    public static int ERROR_CODE = 1;

    public void doSomethingAsynchronously (final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    callback.onSuccess(Collections.EMPTY_LIST);
                } catch (InterruptedException e) {
                    callback.onFail(ERROR_CODE);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
