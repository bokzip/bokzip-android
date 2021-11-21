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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unionz.bokzip.DetailActivity
import com.unionz.bokzip.OnThrottleClickListener
import com.unionz.bokzip.R
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.model.type.TabName
import com.unionz.bokzip.service.RemoteLib
import com.unionz.bokzip.viewmodel.FilterViewModel
import kotlinx.coroutines.launch

class RecommendBokjiItemAdapter(
    private val context: Context,
    private val dataList: ArrayList<RecommendBokjiItem>,
    private val viewModel: FilterViewModel,
    private val tabName: TabName
) :
    RecyclerView.Adapter<RecommendBokjiItemAdapter.ItemViewHolder>() {
    private val TAG = "추천탭 어뎁터"

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
        private val scrap = itemView.findViewById<ImageButton>(R.id.scrap)
        private var isClicked = false

        fun bind(bokjiItem: RecommendBokjiItem, context: Context) {
            title.text = bokjiItem.title
            if (tabName == TabName.SCRAP) {
                Glide.with(context).load(bokjiItem.category).into(thumbnail)
            } else {
                Glide.with(context).load(bokjiItem.thumbnail).into(thumbnail)
            }
            thumbnail.background = context.resources.getDrawable(R.drawable.rounding_img, null)
            thumbnail.setClipToOutline(true)
            if (bokjiItem.isScrap == "true") {
                scrap.setImageDrawable(context.getDrawable(R.drawable.ic_scrap))
                isClicked = true
            }

            scrap.setOnClickListener { view ->
                if (isClicked) { // 스크랩 해제
                    viewModel.removeCenterScrap(bokjiItem.id)
                    isClicked = false
                    scrap.setImageDrawable(context.getDrawable(R.drawable.ic_unscrap))
                    Toast.makeText(context, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()
                } else { // 스크랩 하기
                    viewModel.saveCenterScrap(bokjiItem.id)
                    isClicked = true
                    scrap.setImageDrawable(context.getDrawable(R.drawable.ic_scrap))
                    Toast.makeText(context, "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendBokjiItemAdapter.ItemViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bokji, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendBokjiItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)

        holder.itemView.onThrottleClick {
            val intent = Intent(context, DetailActivity::class.java) // 상세조회로 이동
            intent.putExtra("itemId", dataList[position].id)
            intent.putExtra("itemPosition", position)
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