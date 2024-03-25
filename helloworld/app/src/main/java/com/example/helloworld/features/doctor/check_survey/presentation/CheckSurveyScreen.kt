package com.example.helloworld.features.doctor.check_survey.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloworld.R
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.SurveyQuestion

@Composable
fun CheckSurveyScreen(
    navController: NavController,
    surveyCard: SurveyDTO,
    onCheck: (String, String) -> Unit,
    patient: PatientDTO,
    modifier: Modifier = Modifier,
) {
    var feedbackText by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(start = 15.dp, top = 10.dp)
                .alpha(0.6f)
                .clickable { navController.popBackStack() },
            imageVector = Icons.Filled.Close,
            contentDescription = null
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 9.dp)
        ) {

            itemsIndexed(surveyCard.questions) { index, question ->
                if (index == 0) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        text = stringResource(id = R.string.patient_name, patient.name),
                        fontSize = 20.sp
                    )
                    Text(
                        text = stringResource(R.string.survey_results, surveyCard.title),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                }
                QuestionCard(question = question)
                if (index == surveyCard.questions.size - 1) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.padding(vertical = 5.dp),
                            maxLines = 5,
                            minLines = 5,
                            value = feedbackText,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            placeholder = { Text(text = stringResource(R.string.leave_feedback)) },
                            onValueChange = {feedbackText = it}
                        )
                        TextButton(
                            modifier = Modifier.padding(vertical = 5.dp),
                            border = BorderStroke(1.dp, Color.Black),
                            onClick = {
                                onCheck(surveyCard.id, feedbackText)
                                navController.popBackStack()
                            }
                        ) {
                            Text(text = stringResource(R.string.submit_and_close_survey))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuestionCard(question: SurveyQuestion) {
    Column(
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
        Text(
            fontSize = 18.sp,
            text = question.title
        )
        for (opt in question.options) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
            ) {
                RadioButton(selected = question.answer == opt, onClick = null)
                Text(
                    modifier = Modifier.padding(start = 5.dp, bottom = 4.dp),
                    text = opt,
                    fontSize = 18.sp
                )
            }
        }
    }
}