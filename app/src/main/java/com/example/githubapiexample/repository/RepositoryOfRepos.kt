package com.example.githubapiexample.repository

import com.example.githubapiexample.api.RepoInstance

class RepositoryOfRepos {
    suspend fun getAllRepos()=
        RepoInstance.api.getRepos()
}