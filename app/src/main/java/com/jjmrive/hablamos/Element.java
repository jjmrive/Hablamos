package com.jjmrive.hablamos;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Element implements Serializable, Parcelable{

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Element> CREATOR = new Parcelable.Creator<Element>() {
        @Override
        public Element createFromParcel(Parcel in) {
            return new Element(in);
        }

        @Override
        public Element[] newArray(int size) {
            return new Element[size];
        }
    };

    private String title;
    private String urlPhoto;
    private String txtToSpeach;

    public Element(String title, String urlPhoto, String txtToSpeach){
        this.title = title;
        this.urlPhoto = urlPhoto;
        this.txtToSpeach = txtToSpeach;
    }

    protected Element(Parcel in) {
        title = in.readString();
        urlPhoto = in.readString();
        txtToSpeach = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(urlPhoto);
        dest.writeString(txtToSpeach);
    }

    public String getTitle(){
        return title;
    }

    public String getUrlPhoto(){
        return urlPhoto;
    }

    public String getTxtToSpeach(){
        return txtToSpeach;
    }

    @Override
    public boolean equals(Object o){
        boolean eq = false;
        Element vs = (Element) o;
        if ((this.title.equals(vs.title)) && (this.urlPhoto.equals(vs.urlPhoto)) && (this.txtToSpeach.equals(vs.txtToSpeach))){
            eq = true;
        }
        return eq;
    }


}
