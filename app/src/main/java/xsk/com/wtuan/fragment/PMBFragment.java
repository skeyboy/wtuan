package xsk.com.wtuan.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import xsk.com.wtuan.R;
import xsk.com.wtuan.activity.CreateActivity;
import xsk.com.wtuan.adapter.PMBFragmentStatePagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PMBFragment extends Fragment {


    public PMBFragment() {
        // Required empty public constructor
    }


    private ViewPager mViewPager1;
    private TabLayout mTabLayout;
    private String[] tabTitle = {"我的", "动态"};//每个页面顶部标签的名字


    private void initViews(View rootView) {
        mViewPager1 = (ViewPager) rootView.findViewById(R.id.pmb_viewpager);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.pmb_tablayout);
    }

    private void initData() {
        for (int i = 0; i < tabTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#7CCD7C"));
        mTabLayout.setTabTextColors(Color.GRAY, Color.parseColor("#FF4081"));

        //这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        mViewPager1.setAdapter(new PMBFragmentStatePagerAdapter(getActivity().getSupportFragmentManager(), tabTitle));

        mViewPager1.setCurrentItem(0);
        mViewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        //在设置viewpager页面滑动监听时，创建TabLayout的滑动监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //在选中的顶部标签时，为viewpager设置currentitem
                mViewPager1.setCurrentItem(tab.getPosition());
                Toast.makeText(getContext(), "" + tab.getPosition(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void otherView(View view) {
        TextView pmb_create = view.findViewById(R.id.pmb_create);
        pmb_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pmb, container, false);
        initViews(view);
        initData();
        otherView(view);
        return view;
    }

}
