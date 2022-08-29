package com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pageAdapter extends FragmentPagerAdapter {
    int numCount;

    public pageAdapter(@NonNull FragmentManager fm, int numCount) {
        super(fm);
        this.numCount = numCount;
    }

    public pageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                basic_activity java = new basic_activity();
                return java;

            case 1:
                Edu_detail php = new Edu_detail();
                return php;
            case 2:
                AddDetail css = new AddDetail();
                return css;
        }

            return null;
    }

    @Override
    public int getCount() {
        return numCount;
    }
}
