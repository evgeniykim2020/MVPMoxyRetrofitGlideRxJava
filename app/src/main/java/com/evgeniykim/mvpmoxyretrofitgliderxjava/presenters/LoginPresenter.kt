package com.evgeniykim.mvpmoxyretrofitgliderxjava.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpDelegate
import com.arellomobile.mvp.MvpPresenter
import com.evgeniykim.mvpmoxyretrofitgliderxjava.models.Token
import com.evgeniykim.mvpmoxyretrofitgliderxjava.views.LoginView
import retrofit2.Response

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {

    fun onLoginButtonClick(usernameText: String, passwordText: String) {
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            viewState.showError("please fill all")
        } else {
            viewState.startLogin()
        }
    }

    fun createRetrofit() {
        viewState.retrofitCreate()
    }

    fun onResponse(response: Response<Token>) {
        if (response.code() == 200) {
            val token = response.body()!!
            token.token?.let { Log.d("Success", it) }
            viewState.showSuccess()
            viewState.changeActivity()
        } else {
            Log.d("NOT", response.message())
            viewState.showError(response.message())
        }
    }

    fun onFailure(t: Throwable) {
        t.message?.let { viewState.showError(it) }
    }
}