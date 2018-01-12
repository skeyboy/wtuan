package xsk.com.wtuan.fragment.manager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xsk.com.wtuan.R;
import xsk.com.wtuan.adapter.manager.ManagerFragmentStatePagerStateAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerFragment extends Fragment {
    TabLayout mTabLayout;
    ViewPager viewPager;
    String[] tabTitle = new String[]{"消息管理","社团管理"};
    ManagerFragmentStatePagerStateAdapter adapter;

    public ManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manager, container, false);
        mTabLayout = view.findViewById(R.id.manager_tuan_tab);
        viewPager = view.findViewById(R.id.manger_tuan_viewpager);
        AppCompatActivity activity = (AppCompatActivity) getContext();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < tabTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }

        adapter = new ManagerFragmentStatePagerStateAdapter(activity.getSupportFragmentManager(), new String[]{"消息管理", "社团管理"});
        viewPager.setAdapter(adapter);
        return view;
    }

}
