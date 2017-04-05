package com.example.rednone.fooddelivery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    SecondMenu secondMenu;
    MenuFragment menuFragment;



    public PagerAdapter(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount = tabCount;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                menuFragment = new MenuFragment();
                return menuFragment;
            case 1:
                secondMenu = new SecondMenu();
                return secondMenu;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public MenuFragment getMenuFragment()
    {
        return menuFragment;
    }
    public SecondMenu getDrinksFragment()
    {
        return secondMenu;
    }
}
