package com.example.pertemuan10.ui.view.mahasiswa

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan10.data.entity.Mahasiswa
import com.example.pertemuan10.ui.customwidget.TopAppBar
import com.example.pertemuan10.ui.viewmodel.HomeMhsViewModel
import com.example.pertemuan10.ui.viewmodel.HomeUiState
import com.example.pertemuan10.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeMhsView(
    viewModel: HomeMhsViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMhs: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val homeUiState by viewModel.homeUIState.collectAsState() // Mengamati UI State
    val snackbarHostState = remember { SnackbarHostState() } // Snackbar Host
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Daftar Mahasiswa",
                showBackButton = false,
                onBack = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMhs,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Mahasiswa"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) } // Menambahkan Snackbar Host
    ) { innerPadding ->
        BodyHomeMhsView(
            homeUiState = homeUiState,
            onClick = { nim -> onDetailClick(nim) },
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMhsView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit = {},
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    when {
        homeUiState.isLoading -> {
            // Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            // Menampilkan pesan error menggunakan Snackbar
            LaunchedEffect(homeUiState.errorMessage) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = homeUiState.errorMessage,
                        actionLabel = "Dismiss"
                    )
                }
            }
        }
        homeUiState.listMhs.isEmpty() -> {
            // Menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Mahasiswa",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            // Menampilkan daftar mahasiswa
            ListMahasiswa(
                listMhs = homeUiState.listMhs,
                onClick = onClick,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListMahasiswa(
    listMhs: List<Mahasiswa>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(listMhs) { mhs ->
            CardMhs(
                mhs = mhs,
                onClick = { onClick(mhs.nim) }
            )
        }
    }
}
