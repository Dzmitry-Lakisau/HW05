package by.dzmitry_lakisau.hw05;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class DialogFragment extends android.app.DialogFragment {

    private boolean mForceUpdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        android.support.v4.app.Fragment fragment = getTargetFragment();
        mForceUpdate = getArguments().getBoolean("force_update");
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int negativeButtonString;

        if (mForceUpdate)
            negativeButtonString = R.string.close;
        else negativeButtonString = R.string.no;

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
//                .setTitle("Title!")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =  new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.LINK_TO_UPDATE));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(negativeButtonString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mForceUpdate){
                            getActivity().finish();
                        }
                    }
                })
                .setMessage(R.string.message_text);
        return adb.create();
    }
}
