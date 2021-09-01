package com.unionz.bokzip

import android.os.AsyncTask
import com.google.gson.Gson
import com.unionz.bokzip.adapter.LocationItemAdapter
import com.unionz.bokzip.model.LocationItem
import com.unionz.bokzip.util.Key
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


@Suppress("DEPRECATION")
class AutoCompleteParse() : AsyncTask<String, Void, ArrayList<LocationItem>>() {
    private var TMAP_API_KEY : String = Key.TMAP_API
    private var SEARCH_COUNT : Int = 30
    private lateinit var mListData : ArrayList<LocationItem>
    private lateinit var mAdapter : LocationItemAdapter

    constructor(adapter: LocationItemAdapter) : this() {
        this.mAdapter = adapter
        mListData = ArrayList()
    }

    override fun doInBackground(vararg word: String?): ArrayList<LocationItem> {
        return getAutoComplete(word[0])
    }

    override fun onPostExecute(autoCompleteItems: ArrayList<LocationItem>) {
        mAdapter.setData(autoCompleteItems)
        mAdapter.notifyDataSetChanged()
    }

    fun getAutoComplete(word: String?): ArrayList<LocationItem> {
        try{
            val encodeWord : String = URLEncoder.encode(word, "UTF-8");
            val acUrl = URL(
                "https://api2.sktelecom.com/tmap/pois?areaLMCode=&centerLon=&centerLat=&" +
                        "count=" + SEARCH_COUNT + "&page=&reqCoordType=&" + "" +
                        "searchKeyword=" + encodeWord + "&callback=&areaLLCode=&multiPoint=&searchtypCd=&radius=&searchType=&resCoordType=WGS84GEO&version=1&"+
                        "appKey="+TMAP_API_KEY
            )

            val acConn : HttpURLConnection = acUrl.openConnection() as HttpURLConnection
            acConn.setRequestProperty("Accept", "application/json")
            acConn.setRequestProperty("appKey", TMAP_API_KEY);

            val reader = BufferedReader(
                InputStreamReader(
                    acConn.getInputStream())
            )

            val line : String = reader.readLine()
            if(line == null){
                mListData.clear()
                return mListData
            }

            reader.close()

            mListData.clear()


            val searchPoiInfo = Gson().fromJson(line, TMapSearchInfo::class.java)

            val poi = searchPoiInfo.getSearchPoiInfo().getPois().getPoi()

            for (i in 0 until poi!!.size) {
                val fullAddr = poi!![i].getUpperAddrName() + " " + poi!![i].getMiddleAddrName() +
                        " " + poi!![i].getLowerAddrName() + " " + poi!![i].getDetailAddrName()
                mListData.add(LocationItem(poi!![i].getName(), fullAddr))
            }

        }catch (e : IOException){
            e.printStackTrace();
        }
        return mListData;
    }
}