package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.databinding.FragmentHomeBinding
import com.gorillalogic.cateringserviceapp.viewmodel.FeaturedCateringServicesViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: FeaturedCateringServicesViewModel
    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeaturedCateringServicesViewModel::class.java)

        val featuredCateringServiceListAdapter = FeaturedCateringServiceListAdapter(arrayListOf())

        dataBinding.apply {
            viewModel = this@HomeFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            featuredCateringServiceList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = featuredCateringServiceListAdapter
            }

            refreshLayout.setOnRefreshListener {
                this@HomeFragment.viewModel.refresh()
                refreshLayout.isRefreshing = false
            }
        }

        viewModel.featuredCateringServices.observe(viewLifecycleOwner, Observer {
            featuredCateringServiceListAdapter.updateCateringServiceList(it)
        })

        viewModel.refresh()
    }

}
