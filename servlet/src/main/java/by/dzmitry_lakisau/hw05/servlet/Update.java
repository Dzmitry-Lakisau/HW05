package by.dzmitry_lakisau.hw05.servlet;

public class Update {

    private boolean mForceUpdate;

    private int mVersion;


    public boolean getForceUpdate() {
        return mForceUpdate;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setForceUpdate(boolean mForceUpdate) {
        this.mForceUpdate = mForceUpdate;
    }

    public void setVersion(int mVersion) {
        this.mVersion = mVersion;
    }
}
