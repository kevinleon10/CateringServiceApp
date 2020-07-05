package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dataBinding.cateringService = DetailFragmentArgs.fromBundle(it).cateringService
        }

        dataBinding.closeImage.setOnClickListener {
            this@DetailFragment.requireActivity().onBackPressed()
        }

    }

}
