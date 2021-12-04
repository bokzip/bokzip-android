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
import com.unionz.bokzip.GeneralDetaillActivity
import com.unionz.bokzip.R
import com.unionz.bokzip.model.RecommendBokjiItem
import com.unionz.bokzip.util.onThrottleClick
import com.unionz.bokzip.viewmodel.FilterViewModel

class GeneralBokjiItemAdapter(
    private val context: Context,
    private val viewModel: FilterViewModel
) :
    RecyclerView.Adapter<GeneralBokjiItemAdapter.ItemViewHolder>() {
    private val TAG = "일반탭 어뎁터"
    private lateinit var dataSet: ArrayList<RecommendBokjiItem>

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
        private val scrap = itemView.findViewById<ImageButton>(R.id.scrap)
        private var isClicked = false

        fun bind(bokjiItem: RecommendBokjiItem, context: Context) {
            title.text = bokjiItem.title

            Glide.with(context).load(bokjiItem.thumbnail).into(thumbnail)
            thumbnail.background = context.resources.getDrawable(R.drawable.rounding_img, null)
            thumbnail.setClipToOutline(true)

            if (bokjiItem.isScrap) {
                scrap.setImageDrawable(context.getDrawable(R.drawable.ic_scrap))
                isClicked = true
            }

            scrap.setOnClickListener { _ ->
                if (isClicked) {
                    viewModel.removeGeneralScrap(bokjiItem.id)
                    isClicked = false
                    scrap.setImageDrawable(context.getDrawable(R.drawable.ic_unscrap))
                    Toast.makeText(context, "스크랩 해제되었습니다.", Toast.LENGTH_SHORT).show()

                } else {
                    viewModel.saveGeneralScrap(bokjiItem.id)
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
    ): GeneralBokjiItemAdapter.ItemViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bokji_general, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralBokjiItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(dataSet[position], context)
        holder.itemView.onThrottleClick {
            val intent = Intent(context, GeneralDetaillActivity::class.java)
            intent.putExtra(ITEM_ID, dataSet[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(data: ArrayList<RecommendBokjiItem>) {
        dataSet = data
        notifyDataSetChanged()
    }

    companion object {
        private const val ITEM_ID = "itemId"
    }
}