package xsk.com.wtuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import xsk.com.wtuan.fragment.Essence.EssenceFragment;
import xsk.com.wtuan.fragment.manager.ManagerFragment;
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
        final int last = tabTilte.length - 1;

        if (last == position) {
            return new ManagerFragment();
        }
        Fragment tmp = fragmentMap.get("" + position);
        if (tmp != null) {
            return tmp;
        }
        switch (position) {

            case 1:
                tmp = new EssenceFragment();
                break;

            default:
                tmp = new AlbumFragment();
        }

        fragmentMap.put(position + "", tmp);
        return tmp;
    }
}
