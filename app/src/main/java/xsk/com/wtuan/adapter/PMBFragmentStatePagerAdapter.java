package xsk.com.wtuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.Map;

import xsk.com.wtuan.fragment.CenterFragment;
import xsk.com.wtuan.fragment.PMBAllFragment;
import xsk.com.wtuan.fragment.PMBMyFragment;


/**
 * Created by liyulong on 2018/1/9.
 */

public class PMBFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTilte;
    private Map<String, Fragment> fragmentMap = new HashMap<>();

    public PMBFragmentStatePagerAdapter(FragmentManager fm, String[] tabTitle) {
        super(fm);
        this.tabTilte = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment tmp = fragmentMap.get("" + position);
        if (tmp != null) {

            return tmp;
        }
        switch (position) {
            case 0:
                tmp = new PMBMyFragment();
                break;
            case 1:
                tmp = new PMBAllFragment();
                break;
            default:
                tmp = new CenterFragment();
        }

        fragmentMap.put(position + "", tmp);
        return tmp;
    }

    @Override
    public int getCount() {
        return tabTilte.length;
    }
}
