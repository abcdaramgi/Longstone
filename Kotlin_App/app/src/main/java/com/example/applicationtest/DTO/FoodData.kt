package com.example.applicationtest.DTO

import java.io.Serializable

class FoodData(
    var name: String? =null,
    var place: String? =null,
    var cost: Int? =null,
    var updatecost: Int? =null,
    var img: Int? =null,
    var count: Int? =null,
    var storename: String? =null,
    var storeimg: Int? =null,
    var refood: Int? =null,

    var pdId: Int? =null,
    var sellerId: String? =null
): Serializable {
    fun getData1(): String? {
        return name
    }
    fun setData1(name: String) {
        this.name = name
    }
    fun getData2(): String? {
        return place
    }
    fun setData2(address: String) {
        this.place = place
    }
    fun getData3(): Int? {
        return cost
    }
    fun setData3(type: Int) {
        this.cost = cost
    }
    fun getData4(): Int?{
        return img
    }
    fun setData4(type: Int) {
        this.img = img
    }
    fun getData5(): Int? {
        return updatecost
    }
    fun setData5(type: Int) {
        this.updatecost = updatecost
    }
}