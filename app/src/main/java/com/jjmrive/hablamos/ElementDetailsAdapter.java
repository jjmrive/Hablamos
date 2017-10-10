package com.jjmrive.hablamos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


public class ElementDetailsAdapter extends FragmentStatePagerAdapter {

    private List<Element> elementsList;

    public ElementDetailsAdapter(FragmentManager fm, List<Element> elementsList) {
        super(fm);
        this.elementsList = elementsList;
    }

    @Override
    public Fragment getItem(int position) {
        Element element = elementsList.get(position);
        return ElementDetailFragment.newInstance(element);
    }

    @Override
    public int getCount() {
        return elementsList.size();
    }

}
