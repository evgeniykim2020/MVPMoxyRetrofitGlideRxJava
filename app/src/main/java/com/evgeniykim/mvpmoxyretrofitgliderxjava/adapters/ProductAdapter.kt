package com.evgeniykim.mvpmoxyretrofitgliderxjava.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evgeniykim.mvpmoxyretrofitgliderxjava.R
import com.evgeniykim.mvpmoxyretrofitgliderxjava.models.FullProduct

class ProductAdapter(baseContext: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    internal var context: Context
    internal var list: MutableList<FullProduct>

    init {
        this.context = baseContext
        this.list = ArrayList<FullProduct>() as MutableList<FullProduct>
    }



    fun addItem(product: FullProduct) {
        list?.add(product)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.item_name)
        var brand: TextView = itemView.findViewById(R.id.general_info)
        var image: ImageView = itemView.findViewById(R.id.item_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productViewHolder = holder as ViewHolder
        productViewHolder.name.text = list?.get(position)?.name
        productViewHolder.brand.text = list?.get(position)?.brand.name

        if (!list.get(position).images.isEmpty()) {
            Glide.with(context).load(list.get(position).images.get(0)).into(productViewHolder.image)
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}