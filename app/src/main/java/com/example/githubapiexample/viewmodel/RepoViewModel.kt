package com.example.githubapiexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapiexample.models.Repos
import com.example.githubapiexample.repository.RepositoryOfRepos
import com.example.githubapiexample.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class RepoViewModel(
    private val repoRepository:RepositoryOfRepos
) : ViewModel() {
    val reposAll: MutableLiveData<Resource<Repos>> = MutableLiveData()

    init {
        getRepos()
    }


    private fun getRepos()=viewModelScope.launch {
        reposAll.postValue(Resource.Loading())
        val response = repoRepository.getAllRepos()
        reposAll.postValue(handleGetRepos(response))
    }

    private fun handleGetRepos(response: Response<Repos>):Resource<Repos>{
//        Log.e("dsadasdasd", "handleGetRepos: " )
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}