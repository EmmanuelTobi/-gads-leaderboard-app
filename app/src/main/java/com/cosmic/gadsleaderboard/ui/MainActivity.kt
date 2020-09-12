package com.cosmic.gadsleaderboard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cosmic.gadsleaderboard.R
import com.cosmic.gadsleaderboard.ui.fragments.LearningLeaderboardFragment
import com.cosmic.gadsleaderboard.ui.fragments.SkillsLeaderboardFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noStatusBar()

        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.board_tabs)
        viewPager = findViewById(R.id.board_viewpager)
        setupViewPager(viewPager!!)

        submit_area.setOnClickListener {
            startActivity(Intent(this, SubmissionActivity::class.java))
            finish()
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {

        val adapter = ViewPagerAdapter(this.supportFragmentManager)

        adapter.addFragment(LearningLeaderboardFragment(), "Learning Leaders")
        adapter.addFragment(SkillsLeaderboardFragment(), "Skill IQ Leaders")

        viewPager.adapter = adapter
        viewPager.setCurrentItem(0, true)
        viewPager.offscreenPageLimit = 2

    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {

            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)

        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

    fun noStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}