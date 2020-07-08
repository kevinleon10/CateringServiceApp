package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
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
            circleLayout.bringToFront()

            fillDropdown()
        }
    }

    private fun fillDropdown() {

        var guestQuantity =
            arrayOf<String?>()

        dataBinding.apply {
            for (i in cateringService?.minimumGuests!!..cateringService?.maximumGuests!!) {
                guestQuantity += "$i Guests"
            }

            val guestsAdapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
                dataBinding.root.context,
                R.layout.dropdown_menu_popup_item,
                guestQuantity
            )

            val datesAvailable = cateringService?.timeAvailability!!.toTypedArray()
            val datesAdapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
                dataBinding.root.context,
                R.layout.dropdown_menu_popup_item,
                datesAvailable
            )

            guestsDropdown.setAdapter(guestsAdapter)
            guestsDropdown.setText(guestQuantity[0], false)
            datesDropdown.setAdapter(datesAdapter)
            datesDropdown.setText(datesAvailable[0], false)
            var price = cateringService?.minimumGuests!! * cateringService?.pricePerGuest!!
            hireButton.text = "Hire for $$price"
            guestsDropdown.onItemClickListener = OnItemClickListener { _, _, _, _ ->
                price = guestsDropdown.text.toString().split(" ")[0].toInt() * cateringService?.pricePerGuest!!
                hireButton.text = "Hire for $$price"
            }

        }

    }

}
