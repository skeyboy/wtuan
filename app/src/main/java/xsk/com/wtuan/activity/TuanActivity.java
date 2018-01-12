package xsk.com.wtuan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import xsk.com.wtuan.R;
import xsk.com.wtuan.adapter.AlbumFragmentStatePagerAdapter;

public class TuanActivity extends AppCompatActivity {
    TabLayout albunTabLayout;
    ViewPager albunViewPager;
    private String[] tabTitle = {"动态", "精选", "相册", "管理中心"};//每个页面顶部标签的名字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuan);
        albunTabLayout = findViewById(R.id.album_TabLayout);
        albunViewPager = findViewById(R.id.album_viewpager);

        for (int i = 0; i < tabTitle.length; i++) {
            albunTabLayout.addTab(albunTabLayout.newTab().setText(tabTitle[i]));
        }

        albunTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置顶部标签指示条的颜色和选中页面时标签字体颜色
        albunTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#7CCD7C"));
        albunTabLayout.setTabTextColors(Color.GRAY, Color.parseColor("#FF4081"));
//这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
        albunViewPager.setAdapter(new AlbumFragmentStatePagerAdapter(getSupportFragmentManager(), tabTitle));

        albunViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                albunTabLayout.getTabAt(position).select();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        albunTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                albunViewPager.setCurrentItem(tab.getPosition());
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
