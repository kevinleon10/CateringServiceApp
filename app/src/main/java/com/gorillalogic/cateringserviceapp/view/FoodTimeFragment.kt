package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.databinding.FragmentFoodTimeBinding
import com.gorillalogic.cateringserviceapp.model.FoodTime
import com.gorillalogic.cateringserviceapp.util.getProgressDrawable
import com.gorillalogic.cateringserviceapp.util.loadImage

class FoodTimeFragment(var foodTime: FoodTime) : Fragment() {

    private lateinit var dataBinding: FragmentFoodTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_food_time, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.apply {
            saucer = foodTime.saucers!![0]
            firstItem.loadImage(
                foodTime.saucers!![0].imageUrl,
                getProgressDrawable(root.context)
            )

            secondItem.loadImage(
                foodTime.saucers!![1].imageUrl,
                getProgressDrawable(root.context)
            )

            thirdItem.loadImage(
                foodTime.saucers!![2].imageUrl,
                getProgressDrawable(root.context)
            )
        }
    }
}
