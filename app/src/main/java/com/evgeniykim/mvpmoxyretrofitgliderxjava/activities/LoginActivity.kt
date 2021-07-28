package com.evgeniykim.mvpmoxyretrofitgliderxjava.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.evgeniykim.mvpmoxyretrofitgliderxjava.R
import com.evgeniykim.mvpmoxyretrofitgliderxjava.interfaces.ServiceInterface
import com.evgeniykim.mvpmoxyretrofitgliderxjava.models.Token
import com.evgeniykim.mvpmoxyretrofitgliderxjava.presenters.LoginPresenter
import com.evgeniykim.mvpmoxyretrofitgliderxjava.views.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

class LoginActivity : MvpAppCompatActivity(), LoginView {
    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var loginPresenter: LoginPresenter
    lateinit var usernameText: String
    lateinit var passwordText: String
    lateinit var retrofit: Retrofit
    lateinit var service: ServiceInterface

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter.createRetrofit()
        login_button.setOnClickListener {
            usernameText = usernameText.text.text.toString()
            passwordText = passwordText.text.text.toString()
            loginPresenter.onLoginButtonClick(usernameText, passwordText)
        }
    }

    override fun startLogin() {
        val loginUser = service.login(usernameText, passwordText)
        loginUser.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                loginPresenter.onResponse(response)
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                loginPresenter.onFailure(t)
            }
        })
    }

    override fun retrofitCreate() {
        retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ServiceInterface::class.java)
    }

    override fun showError(message: String) {
    }

    override fun showError(message: Int) {

    }

    override fun showSuccess() {

    }

    override fun changeActivity() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }


}