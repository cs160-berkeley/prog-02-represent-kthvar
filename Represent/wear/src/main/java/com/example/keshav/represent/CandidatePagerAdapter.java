package com.example.keshav.represent;

import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.app.Fragment;

/**
 * Created by Keshav on 3/5/16.
 */
public class CandidatePagerAdapter extends FragmentPagerAdapter {
    private String zipCode;
    private String [] names;
    private String [] parties;

    public CandidatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setNames(String[] names) {
        this.names = names;
    }
    public void setParties(String[] parties) {
        this.parties = parties;
    }

    @Override
    public int getCount(){
        return names.length;
    }
    @Override
    public Fragment getItem(int position){
        return CandidateFragment.newInstance( zipCode, names[position], parties[position]);
    }
}
