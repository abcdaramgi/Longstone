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
    @JvmName("getName1")
    fun getName(): String? {
        return name
    }
    @JvmName("setName1")
    fun setName(name: String) {
        this.name = name
    }

    @JvmName("getPlace1")
    fun getPlace(): String? {
        return place
    }
    @JvmName("setPlace1")
    fun setPlace(address: String) {
        this.place = place
    }
    @JvmName("getCost1")
    fun getCost(): Int? {
        return cost
    }
    fun setCost(type: Int) {
        this.cost = cost
    }
    @JvmName("getImg1")
    fun getImg(): Int?{
        return img
    }
    fun setImg(type: Int) {
        this.img = img
    }
    @JvmName("getUpdatecost1")
    fun getUpdatecost(): Int? {
        return updatecost
    }
    fun setUpdatecost(type: Int) {
        this.updatecost = updatecost
    }
    @JvmName("getCount1")
    fun getCount(): Int?{
        return count
    }
    fun setCount(type: Int) {
        this.count = count
    }

    @JvmName("getStorename1")
    fun getStorename(): String?{
        return storename
    }
    fun setStorename(type: Int) {
        this.storename = storename
    }
}