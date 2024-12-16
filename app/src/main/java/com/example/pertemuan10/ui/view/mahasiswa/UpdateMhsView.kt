package com.example.pertemuan10.ui.view.mahasiswa

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.pertemuan10.ui.viewmodel.PenyediaViewModel
import com.example.pertemuan10.ui.viewmodel.UpdateMhsViewModel
import kotlinx.coroutines.launch

@Composable
fun UpdateMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMhsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.updateUIState // Ambil UI state dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackBarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { massage ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = massage,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()  // Reset snackbar message
            }
        }
    }
}