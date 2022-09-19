package com.example.modelfashion.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.modelfashion.Fragment.CategoryFragment;
import com.example.modelfashion.R;
import com.google.android.material.tabs.TabLayout;

public class InnerTabLayout extends TabLayout implements TabLayout.OnTabSelectedListener {

    public InnerTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnTabSelectedListener(this);
    }

    @NonNull
    @Override
    public Tab newTab() {
        Tab tab = super.newTab();
        tab.setCustomView(R.layout.layout_inner_tab);
        return tab;
    }

    @Override
    public void onTabSelected(Tab tab) {

    }

    @Override
    public void onTabUnselected(Tab tab) {

    }

    @Override
    public void onTabReselected(Tab tab) {

    }
}
