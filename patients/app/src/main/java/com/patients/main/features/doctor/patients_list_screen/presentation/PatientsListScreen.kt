package com.patients.main.features.doctor.patients_list_screen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patients.main.R
import com.patients.main.data.PatientDTO

@Composable
fun PatientsListScreen(
    patients: List<PatientDTO>,
    onClickPatient: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(patients) {patient ->
            PatientCard(
                patient = patient,
                onClickPatient = onClickPatient,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun PatientCard(
    patient: PatientDTO,
    onClickPatient: (String) -> Unit,
    modifier: Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clickable {
                onClickPatient(patient.id)
            },

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth(),
                text = patient.name,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.age)
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = patient.age)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.sex)
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        modifier = Modifier,
                        text = patient.sex
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.diagnosis)
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = patient.diagnosis)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.status)
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        modifier = Modifier,
                        text = patient.status.name
                    )
                }
            }
        }
    }
}