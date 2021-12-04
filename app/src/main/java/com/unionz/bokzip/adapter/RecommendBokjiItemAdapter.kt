package com.unionz.bokzip.adapter

import android.content.Context
import android.content.Intent
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
import com.unionz.bokzip.R
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.model.type.TabName
import com.unionz.bokzip.util.onThrottleClick
import com.unionz.bokzip.viewmodel.FilterViewModel


class RecommendBokjiItemAdapter(
    private val context: Context,
    private val dataList: ArrayList<RecommendBokjiItem>,
    private val viewModel: FilterViewModel,
    private val tabName: TabName
) :
    RecyclerView.Adapter<RecommendBokjiItemAdapter.ItemViewHolder>() {
    private val TAG = "추천탭 어뎁터"

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
        private val scrap = itemView.findViewById<ImageButton>(R.id.scrap)
        private var isClicked = false

        fun bind(position: Int, context: Context) {
            val bokjiItem = dataList[position]
            title.text = bokjiItem.title
            if (tabName == TabName.SCRAP) {
                Glide.with(context).load(bokjiItem.category).into(thumbnail)
            } else {
                Glide.with(context).load(bokjiItem.thumbnail).into(thumbnail)
            }
            thumbnail.background = context.resources.getDrawable(R.drawable.rounding_img, null)
            thumbnail.clipToOutline = true

            if (bokjiItem.isScrap) {
                scrap.setImageDrawable(context.getDrawable(R.drawable.ic_scrap))
                isClicked = true
            }

            if (tabName == TabName.ALL) {
                scrap.setOnClickListener { view ->
                    if (isClicked) {
                        viewModel.removeCenterScrap(bokjiItem.id)
                        isClicked = false
                        scrap.setImageDrawable(context.getDrawable(R.drawable.ic_unscrap))
                        Toast.makeText(context, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.saveCenterScrap(bokjiItem.id)
                        isClicked = true
                        scrap.setImageDrawable(context.getDrawable(R.drawable.ic_scrap))
                        Toast.makeText(context, "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                scrap.setOnClickListener { view ->
                    remove(position)
                    viewModel.removeCenterScrap(bokjiItem.id)
                    Toast.makeText(context, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()
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
        holder.bind(position, context)
        holder.itemView.onThrottleClick {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ITEM_ID, dataList[position].id)
            intent.putExtra(ITEM_POSITION, position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun modify(position: Int, isScrap: Boolean) {
        if (dataList[position].isScrap != isScrap) {
            dataList[position].isScrap = isScrap
            notifyItemChanged(position)
        }
    }

    fun remove(position: Int) {
        notifyItemRemoved(position)
    }

    companion object {
        private const val ITEM_ID = "itemId"
        private const val ITEM_POSITION = "itemPosition"
    }
}