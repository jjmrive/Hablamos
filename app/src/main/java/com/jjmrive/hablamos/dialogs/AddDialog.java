package com.jjmrive.hablamos.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjmrive.hablamos.DataHolder;
import com.jjmrive.hablamos.Element;
import com.jjmrive.hablamos.R;

import java.util.ArrayList;

public class AddDialog extends DialogFragment {

    public interface OnAddDialogListener {
        void onAddElement(String title, String msg, String urlPhoto);
    }

    private OnAddDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createAddDialog();
    }

    public AlertDialog createAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View v = inflater.inflate(R.layout.dialog_new_element, null);

        final TextInputLayout title_layout = (TextInputLayout) v.findViewById(R.id.text_name);
        final TextInputLayout msg_layout = (TextInputLayout) v.findViewById(R.id.text_msg);
        final TextInputLayout url_layout = (TextInputLayout) v.findViewById(R.id.text_url);

        final EditText title = (EditText) v.findViewById(R.id.edit_name);
        final EditText msg = (EditText) v.findViewById(R.id.edit_msg);
        final EditText url = (EditText) v.findViewById(R.id.edit_url);

        builder.setView(v);

        title.addTextChangedListener(new TextValidator(title) {
            @Override
            public void validate(TextView textView, String text) {
                if (findElement(text)){
                    title_layout.setError(getResources().getString(R.string.dialog_add_errorName));
                } else {
                    title_layout.setError(null);
                    if (title.getText().toString().length() == 0 || title.getText().toString().length() > 20){
                        title.getBackground().clearColorFilter();
                        if (title.getText().toString().length() > 20) {
                            title_layout.setError(getResources().getString(R.string.dialog_add_errorSize));
                        }
                    } else {
                        title.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorCorrect), PorterDuff.Mode.SRC_ATOP);
                    }
                }
            }
        });

        msg.addTextChangedListener(new TextValidator(msg) {
            @Override
            public void validate(TextView textView, String text) {
                msg_layout.setError(null);
            }
        });

        url.addTextChangedListener(new TextValidator(url) {
            @Override
            public void validate(TextView textView, String text) {
                url_layout.setError(null);
            }
        });

        Button add = (Button) v.findViewById(R.id.button_add);
        Button cancel = (Button) v.findViewById(R.id.button_cancel);

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String ttl = title.getText().toString();
                        String ms = msg.getText().toString();
                        String pht = url.getText().toString();


                        if ((ttl.length() == 0) || (ttl.length() > 20) || findElement(ttl) || (ms.length() == 0) || (pht.length() == 0)) {
                            if (ttl.length() == 0) {
                                title_layout.setError(getResources().getString(R.string.dialog_add_noName));
                            }
                            if (ttl.length() > 20) {
                                title_layout.setError(getResources().getString(R.string.dialog_add_errorSize));
                            }
                            if (findElement(ttl)) {
                                title_layout.setError(getResources().getString(R.string.dialog_add_errorName));
                            }
                            if (ms.length() == 0) {
                                msg_layout.setError(getResources().getString(R.string.dialog_add_noMsg));
                            }
                            if (pht.length() == 0) {
                                url_layout.setError(getResources().getString(R.string.dialog_add_noUrl));
                            }
                        } else {
                            listener.onAddElement(ttl, ms, pht);
                            dismiss();
                        }
                    }
                }
        );

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                }
        );

        return builder.create();
    }

    public boolean findElement(String text){
        ArrayList<Element> list = DataHolder.getElementsList();

        boolean found = false;

        for (int i = 0; i < list.size(); i++){
            if (text.equals(list.get(i).getTitle())){
                found = true;
            }
        }

        return found;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnAddDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() +
                            " did not implement OnAddDialogListener");

        }
    }


}
