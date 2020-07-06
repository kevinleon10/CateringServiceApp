package com.gorillalogic.cateringserviceapp.util

import com.gorillalogic.cateringserviceapp.model.Menu

class MenuHolder {

    companion object {
        val instance = MenuHolder()
    }

    private var menu: Menu? = null

    fun setMenu(newMenu: Menu?) {
        menu = newMenu
    }

    fun getMenu(): Menu? {
        return menu
    }
}