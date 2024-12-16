package com.example.pertemuan10.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pertemuan10.repository.RepositoryMhs
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class HomeMhsViewModel(
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){
    val homeUIState: StateFlow<HomeUiState> = repositoryMhs.getAllMhs()
        .filterNotNull() // Pastikan repository mengembalikan data non-null
        .map { listMahasiswa ->
            HomeUiState(
                listMhs = listMahasiswa.toList(),
                isLoading = false
            )
        }
}