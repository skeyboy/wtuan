package xsk.com.wtuan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import xsk.com.wtuan.R;
import xsk.com.wtuan.adapter.MyFragmentStatePagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager1;
    private TabLayout mTabLayout;
    private String[] tabTitle = {"测试","我","社团"};//每个页面顶部标签的名字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager1 = (ViewPager) findViewById(R.id.mViewPager1);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        for (int i = 0; i < tabTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#7CCD7C"));
        mTabLayout.setTabTextColors(Color.GRAY, Color.parseColor("#FF4081"));
//这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        mViewPager1.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(), tabTitle));

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
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
