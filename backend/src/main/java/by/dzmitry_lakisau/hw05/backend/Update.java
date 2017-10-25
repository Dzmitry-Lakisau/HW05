package by.dzmitry_lakisau.hw05.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Update {

    private boolean mForceUpdate;

    @Id
    private long mVersion;

    public void setForceUpdate(boolean mForceUpdate) {
        this.mForceUpdate = mForceUpdate;
    }

    public void setVersion(long mVersion) {
        this.mVersion = mVersion;
    }

    public long getVersion() {
        return mVersion;
    }

    public boolean isForceUpdate() {
        return mForceUpdate;
    }
}
