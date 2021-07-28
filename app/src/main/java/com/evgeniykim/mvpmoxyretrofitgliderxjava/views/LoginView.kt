package com.evgeniykim.mvpmoxyretrofitgliderxjava.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface LoginView : MvpView{
    fun retrofitCreate()
    fun startLogin()
    fun showError(message: String)
    fun showError(message:Int)
    fun showSuccess()
    fun changeActivity()
}