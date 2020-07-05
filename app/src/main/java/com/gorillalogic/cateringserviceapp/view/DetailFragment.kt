package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.databinding.FragmentDetailBinding
import com.gorillalogic.cateringserviceapp.util.getProgressDrawable
import com.gorillalogic.cateringserviceapp.util.loadImage

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

        dataBinding.apply {
            arguments?.let {
                cateringService = DetailFragmentArgs.fromBundle(it).cateringService
            }

            closeImage.setOnClickListener {
                this@DetailFragment.requireActivity().onBackPressed()
            }

            carouselView.setImageListener { position, imageView ->
                imageView.loadImage(
                    cateringService?.imageUrls?.get(position),
                    getProgressDrawable(root.context)
                )
            }
            cateringService.let { carouselView.pageCount = cateringService!!.imageUrls!!.size }
        }

    }

}
