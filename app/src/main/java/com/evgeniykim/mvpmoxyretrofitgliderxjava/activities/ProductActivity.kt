package com.evgeniykim.mvpmoxyretrofitgliderxjava.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.evgeniykim.mvpmoxyretrofitgliderxjava.R
import com.evgeniykim.mvpmoxyretrofitgliderxjava.adapters.ProductAdapter
import com.evgeniykim.mvpmoxyretrofitgliderxjava.interfaces.ServiceInterface
import com.evgeniykim.mvpmoxyretrofitgliderxjava.models.ProductList
import com.evgeniykim.mvpmoxyretrofitgliderxjava.presenters.ProductPresenter
import com.evgeniykim.mvpmoxyretrofitgliderxjava.views.ProductView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductActivity: MvpAppCompatActivity(), ProductView {


    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var productPresenter: ProductPresenter
    lateinit var retrofit: Retrofit
    lateinit var service: ServiceInterface
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productPresenter.creatingRetrofitAndAdapter()
        productPresenter.loadingList()
    }


    override fun retrofitCreate() {
        retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun setAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.product_list)
        val layoutManager1 = LinearLayoutManager(baseContext)
        recyclerView?.layoutManager = layoutManager1
        adapter = ProductAdapter(application)
        recyclerView?.adapter = adapter
    }

    override fun loadList() {
        service = retrofit.create(ServiceInterface::class.java)
        val call = service.getProducts()
        call.enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                productPresenter.onResponse(response)
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                productPresenter.onFailure(t)
            }
        })
    }

    override fun forEach(response: Response<ProductList>) {
        for (product in response.body()!!.list!!) {
            adapter.addItem(product)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: Int) {
        Toast.makeText(applicationContext, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {

    }
}