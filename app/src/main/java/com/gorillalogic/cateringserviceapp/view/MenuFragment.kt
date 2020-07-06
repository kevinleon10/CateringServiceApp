package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.databinding.FragmentMenuBinding
import com.gorillalogic.cateringserviceapp.util.MenuHolder


class MenuFragment : Fragment() {

    private lateinit var dataBinding: FragmentMenuBinding
    private var menu = MenuHolder.instance.getMenu()
    private val tabCount = 3
    private val pageTitles = arrayListOf("Breakfast", "Lunch", "Dinner")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewPager.adapter = PagerAdapter(this.requireActivity().supportFragmentManager)
        dataBinding.menuLayout.setBackgroundColor(
            ContextCompat.getColor(
                dataBinding.root.context,
                R.color.colorAccent
            )
        )
    }

    inner class PagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int = tabCount

        override fun getItem(pos: Int): Fragment {
            return FoodTimeFragment(menu?.foodTimes!![pos])
        }

        override fun getPageTitle(position: Int): CharSequence {
            return pageTitles[position]
        }
    }
}


