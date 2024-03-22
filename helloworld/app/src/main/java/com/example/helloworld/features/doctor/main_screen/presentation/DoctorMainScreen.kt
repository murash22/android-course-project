package com.example.helloworld.features.doctor.main_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.core.ui.search_text_field.SearchTextField

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DoctorMainScreen(
    modifier: Modifier = Modifier
) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchTextField()

        }

    }
}

