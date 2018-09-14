package com.suifeng.demo.hencoder1_3

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.suifeng.demo.hencoder1_3.fragments.ViewFragment

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
        mTitles.add("DrawText")
        mTitles.add("StaticLayout")
        mTitles.add("TextSize")
        mTitles.add("Typeface")
        mTitles.add("FakeBold")
        mTitles.add("StrikeThru")
        mTitles.add("UnderLine")
        mTitles.add("TextSkewX")
        mTitles.add("TextScaleX")
        mTitles.add("TextAlign")
        mTitles.add("FontSpacing")
        mTitles.add("MeasureText")
        mTitles.add("TextBounds")
        mTitles.add("FontMetrics")
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
