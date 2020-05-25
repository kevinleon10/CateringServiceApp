package com.gorillalogic.cateringserviceapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.model.FeaturedCateringService
import kotlinx.android.synthetic.main.item_featured_catering_service.view.*

class FeaturedCateringServiceListAdapter(private val featuredCateringServiceList: ArrayList<FeaturedCateringService>) :
    RecyclerView.Adapter<FeaturedCateringServiceListAdapter.FeaturedCateringServiceViewHolder>() {

    fun updateCateringServiceList(newFeaturedCateringServiceList: List<FeaturedCateringService>) {
        featuredCateringServiceList.clear()
        featuredCateringServiceList.addAll(newFeaturedCateringServiceList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeaturedCateringServiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_featured_catering_service, parent, false)
        return FeaturedCateringServiceViewHolder(view)
    }

    override fun getItemCount() = featuredCateringServiceList.size

    override fun onBindViewHolder(holder: FeaturedCateringServiceViewHolder, position: Int) {
        holder.view.featuredCateringServiceTitle.text = featuredCateringServiceList[position].title
    }

    class FeaturedCateringServiceViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}