package com.example.pertemuan10.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pertemuan10.repository.RepositoryMhs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

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
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900) // Delay untuk simulasi loading
        }
}