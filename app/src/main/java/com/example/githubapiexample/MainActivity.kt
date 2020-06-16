package com.example.githubapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapiexample.adapter.RepoAdapter
import com.example.githubapiexample.repository.RepositoryOfRepos
import com.example.githubapiexample.util.Resource
import com.example.githubapiexample.viewmodel.RepoViewModel
import com.example.githubapiexample.viewmodel.RepoViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.wait

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: RepoViewModel
    lateinit var repoAdapter: RepoAdapter
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelSettings()
        adapterSettings()

        viewModel.reposAll.observe(this, Observer {  response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { response ->
                        repoAdapter.differ.submitList(response)
                        head_text.text="Repo's Of "+response[0].owner.login
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun adapterSettings(){
        repoAdapter= RepoAdapter()
        rv_item.apply {
            adapter=repoAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun viewModelSettings() {
        val repoRepository = RepositoryOfRepos()
        val viewModelProviderFactory = RepoViewModelProviderFactory(repoRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(RepoViewModel::class.java)
    }
}