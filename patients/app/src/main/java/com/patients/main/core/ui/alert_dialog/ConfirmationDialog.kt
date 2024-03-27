package com.patients.main.core.ui.alert_dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.patients.main.R

object ConfirmationDialog {
    @Composable
    fun Show(onConfirmed: (Boolean) -> Unit) {
        var showDialog by remember { mutableStateOf(true) }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {  },
                title = { Text(stringResource(R.string.you_sure)) },
                text = { Text(stringResource(R.string.data_would_not_be_safe)) },
                confirmButton = {
                    Button(
                        onClick = {
                            onConfirmed(true)
                            showDialog = false
                        }
                    ) {
                        Text("Да")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            onConfirmed(false)
                            showDialog = false
                        }
                    ) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}