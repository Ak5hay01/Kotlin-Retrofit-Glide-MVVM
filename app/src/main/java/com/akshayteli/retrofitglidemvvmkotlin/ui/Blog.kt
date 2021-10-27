package com.akshayteli.retrofitglidemvvmkotlin.ui

/**
 * Created by Akshay Teli on 27,May,2021
 */

//data class BLog(val title: String, val description: String)
data class Blog(val items: ArrayList<RecyclerData>)
data class RecyclerData(val name: String, val description: String, val owner: Owner)
data class Owner(val avatar_url: String)
