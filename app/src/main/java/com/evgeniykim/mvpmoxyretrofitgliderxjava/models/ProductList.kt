package com.evgeniykim.mvpmoxyretrofitgliderxjava.models

import com.google.gson.annotations.SerializedName

class ProductList {
    @SerializedName("results")
    var list: List<FullProduct>? = null
}