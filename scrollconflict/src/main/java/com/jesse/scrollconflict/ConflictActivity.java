package com.jesse.scrollconflict;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;


/**
 * @author wuzhao
 */
public class ConflictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflict);
        initView();
    }


    private void initView() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter());
    }


    private static class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            Button button = new Button(container.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
            button.setLayoutParams(params);
            button.setText(String.valueOf(position));

            LinearLayout linearLayout = new LinearLayout(container.getContext());
            linearLayout.setBackgroundColor(container.getContext().getResources().getColor(R.color.colorLinearLayout));
            ScrollView.LayoutParams linearLayoutParam = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(linearLayoutParam);

            linearLayout.addView(button,params);

            ScrollView scrollView = new ScrollView(container.getContext());
            scrollView.setBackgroundColor(container.getContext().getResources().getColor(R.color.colorScrollView));

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //add buton to layout
            scrollView.addView(linearLayout,linearLayoutParam);

            final int page = position;
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(container.getContext(), "You clicked: " + page + ". page.", Toast.LENGTH_SHORT).show();
                }
            });
            //to container add layout instead of button
            container.addView(scrollView,layoutParams);
            //return layout instead of button
            return scrollView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            ClassLoader classLoader;
        }
    }
}