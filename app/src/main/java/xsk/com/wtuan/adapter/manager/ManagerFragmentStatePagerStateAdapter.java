package xsk.com.wtuan.adapter.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import xsk.com.wtuan.adapter.MyFragmentStatePagerAdapter;
import xsk.com.wtuan.fragment.manager.MsgManageFragment;
import xsk.com.wtuan.fragment.manager.TuanManagerFragment;

/**
 * Created by liyulong on 2018/1/12.
 */

public class ManagerFragmentStatePagerStateAdapter extends MyFragmentStatePagerAdapter {
    MsgManageFragment msgManageFragment = new MsgManageFragment();
    TuanManagerFragment tuanManagerFragment = new TuanManagerFragment();


    public ManagerFragmentStatePagerStateAdapter(FragmentManager fm, String[] tabTitle) {
        super(fm, tabTitle);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return msgManageFragment;
        } else {
            return tuanManagerFragment;
        }

    }


    @Override
    public int getCount() {
        return tabTilte.length;
    }
}
