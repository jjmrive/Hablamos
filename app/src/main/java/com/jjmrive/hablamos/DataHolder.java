package com.jjmrive.hablamos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataHolder {

    private static final String ELEMENTSLIST = "ElementsList";

    private static ArrayList<Element> elementsList = new ArrayList<>();

    public static ArrayList<Element> getElementsList(){
        return elementsList;
    }

    public static void setElementsList(ArrayList<Element> elementsList){
        DataHolder.elementsList=elementsList;
    }

    public static void loadElementsList(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = appSharedPrefs.getString(ELEMENTSLIST, "");
        Type type = new TypeToken<ArrayList<Element>>() {
        }.getType();
        ArrayList<Element> elements = gson.fromJson(json, type);
        if (elements != null){
            DataHolder.setElementsList(elements);
        }

        boolean init = appSharedPrefs.getBoolean("inicio", true);

        if (init){
            DataHolder.getElementsList().add(new Element("Feliz", "http://stellae.usc.es/red2/mod/file/download.php?file_guid=1888", "Estoy feliz"));
            DataHolder.getElementsList().add(new Element("Triste", "https://images-na.ssl-images-amazon.com/images/I/217AR0gcmBL.jpg", "Estoy triste"));
            DataHolder.getElementsList().add(new Element("Enfermo", "https://thumbs.dreamstime.com/z/emoticon-enfermo-45726670.jpg", "Me encuentro mal"));
            DataHolder.getElementsList().add(new Element("Sueño", "https://openclipart.org/image/2400px/svg_to_png/22414/nicubunu-Emoticons-Sleeping-face.png", "Tengo sueño"));
            DataHolder.getElementsList().add(new Element("Enfadado", "https://rlv.zcache.com/angry_emoticon_classic_round_sticker-rd2b7de1fae81434fa8e80b997dd27cd1_v9waf_8byvr_540.jpg", "Estoy enfadado"));
            DataHolder.getElementsList().add(new Element("Hambre", "https://s-media-cache-ak0.pinimg.com/736x/6c/63/13/6c63136c05a81f802d9ddd6ab1203f2a.jpg", "Tengo hambre"));
            DataHolder.getElementsList().add(new Element("Nervioso", "http://icon-icons.com/icons2/683/PNG/512/nervous_icon-icons.com_61115.png", "Estoy nervioso"));
            DataHolder.getElementsList().add(new Element("Yo", "http://1.bp.blogspot.com/-gyVWBu9Wd2Q/Tr1AIoBWOtI/AAAAAAAAH14/NTmkpdZzCVQ/s1600/yo.png", "Yo"));
            DataHolder.getElementsList().add(new Element("Tu", "https://www.educima.com/dibujo-para-colorear-tu-dm12136.jpg", "Tú"));
            DataHolder.getElementsList().add(new Element("Me gusta", "https://s-media-cache-ak0.pinimg.com/736x/e6/92/89/e69289e1dcf41831133b50252470fa93--happy-faces-emojis.jpg", "Me gusta"));
            DataHolder.getElementsList().add(new Element("No me gusta", "https://s-media-cache-ak0.pinimg.com/736x/f8/f2/56/f8f256718f21bd97ac423757638f9f94--funny-emoji-smiley-faces.jpg", "No me gusta"));
            DataHolder.getElementsList().add(new Element("Jugar", "http://4.bp.blogspot.com/-nEEwTWgx9QE/T0ZZ29ys9kI/AAAAAAAAEnQ/5QlmKhS5jqU/s1600/niños_con_autismo.png", "Quiero jugar"));
            DataHolder.getElementsList().add(new Element("Pintar", "https://s-media-cache-ak0.pinimg.com/originals/96/09/ac/9609ac190c6bccd17590710af7f04799.png", "Quiero pintar"));
        }
    }

    public static void saveElementsList(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String jsonElementList = gson.toJson(DataHolder.getElementsList());
        prefsEditor.putString(ELEMENTSLIST, jsonElementList);
        prefsEditor.commit();

        prefsEditor.putBoolean("inicio", false);
        prefsEditor.commit();
    }

}
