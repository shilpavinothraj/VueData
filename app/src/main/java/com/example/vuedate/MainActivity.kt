package com.example.vuedate

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vuedate.adapter.ProductListAdapter
import com.example.vuedate.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.*


class MainActivity : AppCompatActivity() {
    val name = arrayListOf<String>()
    val images = arrayListOf<String>()
    var isscrolling=false
    var currentitems=0
    var totalitems=0
    var scrolleditems=0
    var page=0

    private var productListAdapter: ProductListAdapter?=null
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        readjson("CONTENTLISTINGPAGE-PAGE1.json")
       productListAdapter = ProductListAdapter(this,name
            ,images)
        val mGridLayoutManager = GridLayoutManager(this, 3)
        mGridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvProducts.setLayoutManager(mGridLayoutManager)
        binding.rvProducts.setNestedScrollingEnabled(false)
        binding.rvProducts.setAdapter(productListAdapter)
        binding.rvProducts
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int, dy: Int
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                   currentitems=mGridLayoutManager.childCount
                    totalitems=mGridLayoutManager.itemCount
                    scrolleditems=mGridLayoutManager.findFirstVisibleItemPosition()
                    if (isscrolling&&(currentitems+scrolleditems==totalitems)){
                        if (page==1){
                            readjson("CONTENTLISTINGPAGE-PAGE2.json")
                        }else if (page ==2){
                            readjson("CONTENTLISTINGPAGE-PAGE3.json")
                        }
                        Log.d("shilpa","scrollede")
                    }
                    Log.d("shilpa","scrollede111")

                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    Log.d("shilpa","yesscroll")


                    if (newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                        isscrolling=true
                    }
                }
            })

        binding.toolbarSearch.setOnClickListener(View.OnClickListener {
            binding.headerLyout.visibility=View.GONE
            binding.searchLyout.visibility=View.VISIBLE
        })
        binding.close.setOnClickListener(View.OnClickListener {
            binding.headerLyout.visibility=View.VISIBLE
            binding.searchLyout.visibility=View.GONE
        })


    }
    fun readjson(filename:String){
        var json:String?=null
        try {
            val inputStream:InputStream=assets.open(filename)
            json=inputStream.bufferedReader().use { it.readText() }
            var jsonarray=JSONObject(json)
               var jsonObject=jsonarray.getJSONObject("page").getJSONObject("content-items").getJSONArray("content")
            for (i in 0 until jsonObject.length()){
                name.add(jsonObject.getJSONObject(i).getString("name"))
                images.add(jsonObject.getJSONObject(i).getString("poster-image"))
            }
            Log.d("shilpa","page")
            page++
            if (page>1){
                productListAdapter?.notifyDataSetChanged()
            }
        }catch (e:IOException){

        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val orientation = newConfig.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            val mGridLayoutManager = GridLayoutManager(this, 3)
            mGridLayoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rvProducts.setLayoutManager(mGridLayoutManager)
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val mGridLayoutManager = GridLayoutManager(this, 5)
            mGridLayoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rvProducts.setLayoutManager(mGridLayoutManager)
        }
         else Log.w("tag", "other: $orientation")
    }


}