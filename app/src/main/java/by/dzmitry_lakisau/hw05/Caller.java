package by.dzmitry_lakisau.hw05;

import java.util.ArrayList;
import java.util.List;

public class Caller implements Callback {

    private final ThreadRunner mThreadRunner;

    private List<String> result = new ArrayList<String>();

    public Caller(ThreadRunner threadRunner) {
        this.mThreadRunner = threadRunner;
    }

    public void doSomethingAsynchronously() {
        mThreadRunner.doSomethingAsynchronously(this);
    }

    public List<String> getResult() {
        return this.result;
    }

    @Override
    public void onSuccess(List<String> result) {
        this.result = result;
        System.out.println("On success");
    }

    @Override
    public void onFail(int code) {
        System.out.println("On Fail");
    }
}
