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
import com.gorillalogic.cateringserviceapp.util.getVisibility
import com.gorillalogic.cateringserviceapp.viewmodel.UpcomingOrdersViewModel

class UpcomingOrdersFragment : Fragment() {

    private lateinit var viewModel: UpcomingOrdersViewModel
    private lateinit var dataBinding: FragmentUpcomingOrdersBinding
    private val upcomingOrderListAdapter = UpcomingOrderListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_orders, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UpcomingOrdersViewModel::class.java)

        dataBinding.apply {
            upcomingOrdersList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = upcomingOrderListAdapter
            }

            refreshLayout.setOnRefreshListener {
                viewModel.refresh()
                refreshLayout.isRefreshing = false
            }
        }

        setObservers()

        viewModel.refresh()
    }

    private fun setObservers() {
        dataBinding.apply {
            viewModel.errorVisible.observe(viewLifecycleOwner, Observer {
                dataBinding.upcomingOrdersListError.visibility = getVisibility(it)
            })

            viewModel.loadingVisible.observe(viewLifecycleOwner, Observer {
                dataBinding.upcomingOrdersListLoading.visibility = getVisibility(it)
            })

            viewModel.upcomingOrders.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    upcomingOrdersList.visibility = getVisibility(true)
                    upcomingOrderListAdapter.updateUpcomingOrderList(it)
                } else {
                    upcomingOrdersList.visibility = getVisibility(false)
                }
            })
        }
    }
}
