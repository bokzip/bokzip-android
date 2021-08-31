package com.unionz.bokzip.adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unionz.bokzip.DetailActivity
import com.unionz.bokzip.OnThrottleClickListener
import com.unionz.bokzip.R
import com.unionz.bokzip.model.RecommendBokjiItem

/**
 * @param type :
 * 1. type 이 1인 경우 : 추천과 스크랩 탭의 아이템에 해당하는 item_bokji뷰를 생성
 * 2. type 이 2인 경우 : 일반 탭의 아이템에 해당하는 item_bokji_general뷰를 생성
 */
class RecommendBokjiItemAdapter(private val context: Context, private val dataList: ArrayList<RecommendBokjiItem>) :
    RecyclerView.Adapter<RecommendBokjiItemAdapter.ItemViewHolder>() {
//    var strUtil = StrUtil()

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
        private val scrap = itemView.findViewById<ImageButton>(R.id.scrap)
        private var isClicked = false

        fun bind(bokjiItem: RecommendBokjiItem, context: Context) {

            title.text = bokjiItem.title
//            title.text = strUtil.setNewLine(bokjiItem.title) @TODO : 타이틀 띄어쓰기 밎 즐바꿈 조정하기

            Glide.with(context).load(bokjiItem.thumbnail).into(thumbnail)
            thumbnail.background = context.resources.getDrawable(R.drawable.rounding_img, null)
            thumbnail.setClipToOutline(true)
            if(bokjiItem.isScrap == "true"){
                scrap.setImageDrawable(context.getDrawable(R.drawable.ic_scrap))
                isClicked = true
            }

            scrap.setOnClickListener { view ->
                if (isClicked) { // 스크랩 해제
                    isClicked = false
                    scrap.setImageDrawable(context.getDrawable(R.drawable.ic_unscrap))
                    Toast.makeText(context, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()
                } else { // 스크랩 하기
                    isClicked = true
                    scrap.setImageDrawable(context.getDrawable(R.drawable.ic_scrap))
                    Toast.makeText(context, "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendBokjiItemAdapter.ItemViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bokji, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendBokjiItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)

        holder.itemView.onThrottleClick {
            val intent = Intent(context, DetailActivity::class.java) // 상세조회로 이동
            intent.putExtra("itemId", dataList[position].id)
            Log.i(ContentValues.TAG, "bind: " + dataList[position].id) // 복지 정보 id값 넘기기
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    // 중복 클릭 방지
    fun View.onThrottleClick(action: (v: View) -> Unit) {
        val listener = View.OnClickListener { action(it) }
        setOnClickListener(OnThrottleClickListener(listener))
    }
}