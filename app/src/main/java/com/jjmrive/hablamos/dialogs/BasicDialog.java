package com.jjmrive.hablamos.dialogs;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.jjmrive.hablamos.ElementDetailFragment;

public class BasicDialog extends DialogFragment {

    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String OK = "ok";
    private static final String CANCEL = "cancel";


    public static BasicDialog newInstance(String title, String message, String ok, String cancel){
        BasicDialog dlg = new BasicDialog();

        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        bundle.putString(OK, ok);
        bundle.putString(CANCEL, cancel);

        dlg.setArguments(bundle);

        return dlg;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString(TITLE);
        String message = getArguments().getString(MESSAGE);
        String ok = getArguments().getString(OK);
        String cancel = getArguments().getString(CANCEL);

        return createBasicDialog(title, message, ok, cancel);
    }

    public AlertDialog createBasicDialog(String title, String message, String ok, String cancel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ElementDetailFragment fragment = (ElementDetailFragment) getTargetFragment();
                                fragment.onPositiveButtonClick();
                            }
                        })
                .setNegativeButton(cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ElementDetailFragment fragment = (ElementDetailFragment) getTargetFragment();
                                fragment.onNegativeButtonClick();
                            }
                        });

        return builder.create();
    }

}

