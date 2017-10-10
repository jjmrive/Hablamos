package com.jjmrive.hablamos;

import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjmrive.hablamos.dialogs.BasicDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class ElementDetailFragment extends Fragment {

    private static final String EXTRA_ELEMENT = "element";
    private static final String BASIC_DIALOG = "basicDialog";

    private Element element;

    private TextToSpeech t2s;

    public static ElementDetailFragment newInstance(Element element) {
        ElementDetailFragment elementDetailFragment = new ElementDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_ELEMENT, element);
        elementDetailFragment.setArguments(bundle);
        return elementDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_elementdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        t2s = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = t2s.setLanguage(new Locale("spa", "ESP"));
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }

                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });

        element = getArguments().getParcelable(EXTRA_ELEMENT);

        TextView detailTextView = (TextView) view.findViewById(R.id.element_text);
        detailTextView.setText(element.getTxtToSpeach());

        ImageView imageView = (ImageView) view.findViewById(R.id.element_image);

        Picasso.with(getContext())
                .load(element.getUrlPhoto())
                .noFade()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                speak(element.getTxtToSpeach());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.delete_menu:

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag(BASIC_DIALOG);
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                BasicDialog dialog = new BasicDialog().newInstance(getResources().getString(R.string.dialog_basic_title),
                        getResources().getString(R.string.dialog_basic_description) + " " + element.getTitle(),
                        getResources().getString(R.string.dialog_basic_delete), getResources().getString(R.string.dialog_basic_cancel));
                dialog.setTargetFragment(this, 1);
                dialog.show(getFragmentManager().beginTransaction(), BASIC_DIALOG);

                return true;
        }
        return false;
    }


    public void onPositiveButtonClick() {
        Toast.makeText(getContext(), getResources().getString(R.string.element_deleted_init) + " " + element.getTitle() + " " + getResources().getString(R.string.element_deleted_final), Toast.LENGTH_LONG).show();

        ArrayList<Element> elementsList = (ArrayList<Element>) DataHolder.getElementsList();

        elementsList.remove(elementsList.indexOf(element));
        DataHolder.saveElementsList(getContext());

        getActivity().onBackPressed();

    }


    public void onNegativeButtonClick() {
    }

    private void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            t2s.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);

        }else{
            t2s.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}
