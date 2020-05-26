package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.viewmodel.FeaturedCateringServicesViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: FeaturedCateringServicesViewModel
    private val featuredCateringServiceListAdapter = FeaturedCateringServiceListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeaturedCateringServicesViewModel::class.java)
        viewModel.featuredCateringServices.observe(viewLifecycleOwner, Observer {
            it?.let {
                featuredCateringServiceList.visibility = View.VISIBLE
                featuredCateringServiceListAdapter.updateCateringServiceList(it)
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            featuredCateringServiceListLoading.visibility = if(it) View.VISIBLE else View.GONE
            if(it){
                featuredCateringServiceListError.visibility = View.GONE
                featuredCateringServiceList.visibility = View.GONE
            }
        })
        viewModel.loadError.observe(viewLifecycleOwner, Observer {
            if(it) {
                featuredCateringServiceListError.visibility = View.VISIBLE
            }
        })
        viewModel.refresh()

        featuredCateringServiceList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = featuredCateringServiceListAdapter
        }

        refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

}
