package com.evgeniykim.mvpmoxyretrofitgliderxjava.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.evgeniykim.mvpmoxyretrofitgliderxjava.models.ProductList
import retrofit2.Response

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ProductView : MvpView {
    fun retrofitCreate()
    fun setAdapter()
    fun loadList()
    fun forEach(response: Response<ProductList>)
    fun showError(message: String)
    fun showError(message: Int)
    fun showSuccess()
}