package com.example.applicationtest

import java.io.Serializable

data class ItemCart (
    var isChecked: Boolean = false,
    var StoreName : String? = null,
    var FoodName : String? = null,
    var food_img : Int? = null,
    var cost : Int? = null,
    var food_count : Int? = null
): Serializable {
    fun getData1(): Boolean {
        return isChecked
    }
    fun setData1(check: Boolean) {
        this.isChecked = check
    }
}

data class BuyListItem (
    var buyliste_fd_name : String? =null,
    var buyliste_st_name : String? =null,
    var buyliste_fd_count : String? =null,
    var buylist_img : Int? =null,
        )