package com.gorillalogic.cateringserviceapp.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.gorillalogic.cateringserviceapp.R
import com.gorillalogic.cateringserviceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(dataBinding.bottomNavigationView, navController)

        dataBinding.mainLayout.setBackgroundColor(Color.WHITE)
    }

}
