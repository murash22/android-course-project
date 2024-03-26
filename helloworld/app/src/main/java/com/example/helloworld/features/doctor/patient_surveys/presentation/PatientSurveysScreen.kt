package com.example.helloworld.features.doctor.patient_surveys.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.R
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.SurveyDTO

@Composable
fun PatientSurveysScreen(
    surveys: List<SurveyDTO>,
    patient: PatientDTO,
    onBack: () -> Unit,
    onPatientInfo: () -> Unit,
    onAddSurvey: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Icon(
                modifier = Modifier.clickable(onClick = onPatientInfo),
                painter = painterResource(id = R.drawable.edit_info),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            text = stringResource(R.string.patient_surveys_name, patient.name)
        )
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(surveys.sortedBy { it.closeDate }) {index, it ->
                var borderColor: Color = Color.Yellow
                var body = "Не проверен"
                if (it.closeDate != null) {
                    body = stringResource(id = R.string.close_date, it.closeDate!!)
                    borderColor = Color.LightGray
                } else if (!it.completed) {
                    body = "Не пройден"
                    borderColor = Color.Unspecified
                }
                SurveyCard(
                    title = it.title,
                    body = body,
                    borderColor = borderColor
                )
                if (index == surveys.size - 1) {
                    OutlinedButton(
                        onClick = onAddSurvey,
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.DarkGray),

                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(vertical = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(
                            text = stringResource(R.string.add_survey),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}