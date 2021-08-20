package com.unionz.bokzip.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.unionz.bokzip.R
import kotlinx.android.synthetic.main.item_tab.view.*

class MainActivity : AppCompatActivity() {

    /*
        ViewPager2는 FragmentStateAdapter에 FragmentViewHolder가 지정되어 구현되는 것이다.
        RecyclerView는 Adpater에 ViewHolder를 지정하여 구현되는 것과 동일한 방식이다.
     */

    // 1. activity_main.xml 에 존재하는 viewPager2 뷰 객체 초기화를 액티비티 lifecycle에 맞게 지연시킴
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var mContext : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = applicationContext

        // 2. 초기화 지연시킨 viewPager2 객체를 여기서 초기화함
        viewPager = findViewById(R.id.view_pager)

        // 3. viewPager2 뷰 객체에 어댑터 적용하기
        viewPager.adapter = ViewPagerAdapter(this)

        // 4. tablayout 뷰 객체 초기화하기
        tabLayout = findViewById(R.id.tab_layout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(applicationContext, "탭 눌림", Toast.LENGTH_SHORT).show()
            }
        })

        // 3. tabLayout과 viewPager 연결하기
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tabLayout.getTabAt(0)?.setCustomView(createView("추천"))
            tabLayout.getTabAt(1)?.setCustomView(createView("일반"))
            tabLayout.getTabAt(2)?.setCustomView(createView("스크랩"))
//            when(position) {
//                0 ->
//                1 ->
//                2 ->
//            }
        }.attach()
    }

    // TabLayout의 TabItem을 커스텀하기
    private fun createView(tabName: String): View {
        var tabView = LayoutInflater.from(mContext).inflate(R.layout.item_tab, null)
        tabView.tab_text.text = tabName
        return tabView
    }

    inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            if(position == 0) {
                return RecommendFragment()
            }else if(position == 1) {
                return GeneralFragment()
            }else {
                return ScrapFragment()
            }
        }
    }
}