package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.databinding.FragmentUpcomingOrdersBinding
import com.gorillalogic.cateringserviceapp.viewmodel.UpcomingOrdersViewModel

class UpcomingOrdersFragment : Fragment() {

    private lateinit var viewModel: UpcomingOrdersViewModel
    private lateinit var dataBinding: FragmentUpcomingOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_orders, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UpcomingOrdersViewModel::class.java)

        val upcomingOrderListAdapter = UpcomingOrderListAdapter(arrayListOf())

        dataBinding.apply {
            viewModel = this@UpcomingOrdersFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            upcomingOrdersList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = upcomingOrderListAdapter
            }

            refreshLayout.setOnRefreshListener {
                this@UpcomingOrdersFragment.viewModel.refresh()
                refreshLayout.isRefreshing = false
            }
        }

        viewModel.upcomingOrders.observe(viewLifecycleOwner, Observer {
            upcomingOrderListAdapter.updateUpcomingOrderList(it)
        })

        viewModel.refresh()
    }
}
