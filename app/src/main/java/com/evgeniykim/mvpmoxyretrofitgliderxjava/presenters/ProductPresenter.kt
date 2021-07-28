package com.evgeniykim.mvpmoxyretrofitgliderxjava.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.evgeniykim.mvpmoxyretrofitgliderxjava.models.ProductList
import com.evgeniykim.mvpmoxyretrofitgliderxjava.views.ProductView
import retrofit2.Response

@InjectViewState
class ProductPresenter : MvpPresenter<ProductView>() {
    fun creatingRetrofitAndAdapter() {
        viewState.retrofitCreate()
        viewState.setAdapter()
    }

    fun loadingList() {
        viewState.loadList()
    }

    fun onResponse(response: Response<ProductList>) {
        if (response.code() == 200) {
            viewState.forEach(response)
            viewState.showSuccess()
        } else {
            viewState.showError("Empty list of product")
        }
    }

    fun onFailure(t: Throwable) {
        viewState.showError(t.message.toString())
    }
}