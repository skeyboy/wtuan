package xsk.com.wtuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import xsk.com.wtuan.fragment.album.AlbumFragment;

/**
 * Created by liyulong on 2018/1/10.
 */

public class AlbumFragmentStatePagerAdapter extends MyFragmentStatePagerAdapter {


    public AlbumFragmentStatePagerAdapter(FragmentManager fm, String[] tabTitle) {
        super(fm, tabTitle);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment tmp = fragmentMap.get("" + position);
        if (tmp != null) {
            return tmp;
        }
        switch (position) {


            default:
                tmp = new AlbumFragment();
        }

        fragmentMap.put(position + "", tmp);
        return tmp;
    }
}
