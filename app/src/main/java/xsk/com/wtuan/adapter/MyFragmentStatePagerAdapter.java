package xsk.com.wtuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.Map;

import xsk.com.wtuan.fragment.CenterFragment;
import xsk.com.wtuan.fragment.PMBFragment;
import xsk.com.wtuan.fragment.TestFragment;


/**
 * Created by liyulong on 2018/1/8.
 */

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTilte;
    protected Map<String, Fragment> fragmentMap = new HashMap<>();

    public MyFragmentStatePagerAdapter(FragmentManager fm, String[] tabTitle) {
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
                tmp = new TestFragment();
                break;
            case 2:
                tmp = new PMBFragment();
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
