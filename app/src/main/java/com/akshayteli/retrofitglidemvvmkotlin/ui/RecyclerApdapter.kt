package com.akshayteli.retrofitglidemvvmkotlin.ui

import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshayteli.retrofitglidemvvmkotlin.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Akshay Teli on 27,May,2021
 */
class RecyclerApdapter: RecyclerView.Adapter<RecyclerApdapter.MyViewHolder>() {

    var lstData = ArrayList<RecyclerData>()

    fun setListData(data: ArrayList<RecyclerData>){
        this.lstData = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerApdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(lstData[position])
    }

    override fun getItemCount(): Int {
        return lstData.size
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtview = view.txtTitle
        var txtDesc = view.txtDescription
        var imgView = view.imageview

        fun bind(blog: RecyclerData){
            txtview.text = blog.name
            if(!TextUtils.isEmpty(blog.description)){
                txtDesc.text = blog.description
                txtDesc.setTextColor(Color.GRAY)
            }
            else
            {
                txtDesc.text = "No Description Found!"
                txtDesc.setTextColor(Color.RED)
            }

            val url = blog.owner.avatar_url

            Glide.with(imgView)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(imgView)


        }

    }
}