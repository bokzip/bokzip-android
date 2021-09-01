package com.unionz.bokzip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.unionz.bokzip.AutoCompleteParse
import com.unionz.bokzip.R
import com.unionz.bokzip.model.LocationItem
import java.util.concurrent.ExecutionException
import androidx.recyclerview.widget.RecyclerView as RecyclerView

class LocationItemAdapter(private val context: Context):
    RecyclerView.Adapter<LocationItemAdapter.CustomViewHolder>(){

    var itemLists :ArrayList<LocationItem> = java.util.ArrayList()

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.location_title)
        val subTitle = itemView.findViewById<TextView>(R.id.location_sub_title)

        fun bind(locationItem: LocationItem, context: Context) {
            title.text = locationItem.getTitle()
            subTitle.text = locationItem.getSubTitle()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_location, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (holder is CustomViewHolder){
            val viewHolder : CustomViewHolder = holder

            viewHolder.title.setText(itemLists.get(0).getTitle())
            viewHolder.subTitle.setText(itemLists.get(0).getSubTitle())

            viewHolder.itemView.setOnClickListener {
                //callback.showToast(ItemPosition)
                Toast.makeText(context, position,Toast.LENGTH_SHORT).show()
            }
        }
       holder.bind(itemLists[position], context)
    }

    override fun getItemCount(): Int {
        return itemLists.size
    }

    fun setData(itemLists:ArrayList<LocationItem>) {
        this.itemLists = itemLists
    }

    fun filter(keyword:String){
        if (keyword.length >= 2) {
            try {
                var parser = AutoCompleteParse(this)
                itemLists.addAll(parser.execute(keyword).get())
            } catch (e : InterruptedException) {
                e.printStackTrace()
            } catch (e : ExecutionException) {
                e.printStackTrace()
            }
        }
    }
}
