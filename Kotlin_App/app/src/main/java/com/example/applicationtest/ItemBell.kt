package com.example.applicationtest

import java.time.LocalDateTime

data class ItemBell (
    val storeName : String,
    val storeInfo : String
    )

data class OrderBell (
    val FoodName : String,
    val Customer : String,
    val OrderTime : LocalDateTime,
    val FoodCost : String
        )