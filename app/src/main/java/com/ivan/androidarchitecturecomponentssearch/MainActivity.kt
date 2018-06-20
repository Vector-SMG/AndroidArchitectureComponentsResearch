package com.ivan.androidarchitecturecomponentssearch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.ivan.androidarchitecturecomponentssearch.data.api.protocol.response.GankItem
import com.ivan.androidarchitecturecomponentssearch.data.repository.GankRepository
import com.ivan.androidarchitecturecomponentssearch.data.resource.Resource
import com.ivan.androidarchitecturecomponentssearch.data.viewmodel.GankViewModel
import com.ivan.androidarchitecturecomponentssearch.data.viewmodel.MyFactory
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var viewModel: GankViewModel? = null
    private var gankRepository: GankRepository? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var category = "Android"
        var count = 10
        var page = 1

        gankRepository = GankRepository()
        viewModel = ViewModelProviders.of(this, MyFactory(gankRepository!!)).get(GankViewModel::class.java)
        viewModel!!.init(category, count, page)

        viewModel!!.getGank().observe(this, Observer {
            it?.let {
                when (it.status) {
                    1 -> showSuccessedUI(it)
                    2 -> showErrorUI(it)
                    3 -> showLoadingUI()
                }
            }
        })
    }

    private fun showSuccessedUI(it: Resource<List<GankItem>>) {
        Toast.makeText(this,"成功",Toast.LENGTH_LONG).show()
    }

    private fun showErrorUI(it: Resource<List<GankItem>>) {
        Toast.makeText(this,"请求失败:${it.message}(${it.status})",Toast.LENGTH_LONG).show()
    }

    private fun showLoadingUI() {
        Toast.makeText(this,"请求中..",Toast.LENGTH_LONG).show()
    }

}
