package com.example.githubapiexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapiexample.repository.RepositoryOfRepos

class RepoViewModelProviderFactory(
    val repositoryOfRepos: RepositoryOfRepos
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoViewModel(repositoryOfRepos) as T
    }
}