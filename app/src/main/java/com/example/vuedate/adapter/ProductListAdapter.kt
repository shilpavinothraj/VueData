package com.example.vuedate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vuedate.R

class ProductListAdapter(val mContext: Context, var names: List<String?>,var images: List<String?>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>(){
    lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.productlist, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return names?.size!!
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
            holder.bindItems(names.get(position),images.get(position), view, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(name: String?,image:String?, views: View, position: Int) {
            var productimage = view.findViewById<AppCompatImageView>(R.id.product_image)
            var nameview = view.findViewById<AppCompatTextView>(R.id.name)
            nameview.text= name
            when(image){
                "poster1.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster1)
                }
                "poster5.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster5)
                }
                "poster2.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster2)
                }
                "poster3.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster3)
                }
                "poster4.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster4)
                }
                "poster6.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster6)
                }
                "poster7.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster7)
                }
                "poster8.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster8)
                }
                "poster9.jpg"->{
                    productimage.setBackgroundResource(R.drawable.poster9)
                }
                "posterthatismissing.jpg"->{
                    productimage.setBackgroundResource(R.drawable.placeholder_for_missing_posters)
                }
            }
        }
    }
}