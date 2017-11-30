package com.example.rhp8r.final_project_final_spearow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabVocab tab1 = new TabVocab();
                return tab1;
            case 1:
                TabPic tab2 = new TabPic();
                return tab2;
            case 2:
                TabAudio tab3 = new TabAudio();
                return tab3;
            case 3:
                TabLink tab4 = new TabLink();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}