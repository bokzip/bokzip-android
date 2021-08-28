package com.unionz.bokzip.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.unionz.bokzip.R
import com.unionz.bokzip.util.PreferenceUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*
        ViewPager2는 FragmentStateAdapter에 FragmentViewHolder가 지정되어 구현되는 것이다.
        RecyclerView는 Adpater에 ViewHolder를 지정하여 구현되는 것과 동일한 방식이다.
     */
    private lateinit var mContext : Context
    companion object { lateinit var prefs: PreferenceUtil }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = applicationContext
        prefs = PreferenceUtil(mContext)

        // 어댑터 적용하기
        view_pager.adapter = ViewPagerAdapter(this)

        // 스와이프 기능 끄기
        view_pager.isUserInputEnabled = false

        // 탭의 동작을 감지 및 처리
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })

        // tabLayout과 viewPager 연결하기
        val tabTitles = listOf("추천", "일반", "스크랩")
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    // 뷰페이저 아이템으로 탭별 프래그먼트 등록
    inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> RecommendFragment()
                1 -> GeneralFragment()
                else -> ScrapFragment()
            }
        }
    }
}