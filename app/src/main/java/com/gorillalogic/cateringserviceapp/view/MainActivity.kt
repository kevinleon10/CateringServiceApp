package com.gorillalogic.cateringserviceapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.gorillalogic.cateringserviceapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

}
