package com.suifeng.demo.hencoder1_4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.suifeng.demo.hencoder1_4.fragments.ViewFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private val mFragments = ArrayList<Fragment>()
    private val mTitles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        initFragments()
        viewPager.adapter = FragmentPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initFragments() {
        mTitles.add("ClipRectView")
        mTitles.add("ClipPathView")
        mTitles.add("TranslateView")
        mTitles.add("ScaleView")
        mTitles.add("RotateView")
        mTitles.add("SkewView")
        mTitles.add("SkewView")
        mTitles.add("MatrixTranslateView")
        mTitles.add("MatrixScaleView")
        mTitles.add("MatrixRotateView")
        mTitles.add("MatrixSkewView")
        mTitles.add("CameraRotateView")
        mTitles.add("CameraRotateFixedView")
        mTitles.add("CameraRotateHittingFaceView")
        mTitles.add("FlipboardView")
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_1))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_2))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_3))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_4))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_5))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_6))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_7))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_8))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_9))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_10))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_11))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_12))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_13))
        mFragments.add(ViewFragment.getViewFragment(R.layout.frag_14))

        mTitles.reverse()
        mFragments.reverse()
    }

    private inner class FragmentPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mTitles[position]
        }
    }
}
