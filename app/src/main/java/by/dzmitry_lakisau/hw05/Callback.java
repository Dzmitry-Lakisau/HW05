package by.dzmitry_lakisau.hw05;

import java.util.List;

public interface Callback {
    public void onSuccess(List<String> result);
    public void onFail(int code);
}