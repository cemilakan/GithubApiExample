package com.example.githubapiexample.api

import com.example.githubapiexample.models.Repos
import retrofit2.Response
import retrofit2.http.GET

interface RepoAPI {
    //base url https://api.github.com/
    @GET("users/cemilakan/repos")
    suspend fun getRepos():Response<Repos>
}