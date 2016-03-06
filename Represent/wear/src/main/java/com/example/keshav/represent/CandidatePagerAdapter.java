package com.example.keshav.represent;

import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.app.Fragment;

/**
 * Created by Keshav on 3/5/16.
 */
public class CandidatePagerAdapter extends FragmentPagerAdapter {
    private String[] candidates={"Barbara Boxer DEMOCRAT", "Dianne Feinstein DEMOCRAT"};
    private String zipCode;


    public CandidatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int getCount(){
        return candidates.length;
    }
    @Override
    public Fragment getItem(int position){
        return CandidateFragment.newInstance(candidates[position], zipCode);
    }
}
