package com.patients.main.features.patient.take_survey_screen.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patients.main.R
import com.patients.main.core.ui.alert_dialog.ConfirmationDialog
import com.patients.main.data.SurveyDTO
import com.patients.main.data.SurveyQuestion


//@Preview(showBackground = true)
@Composable
fun PatientTakeSurveyScreen(
    modifier: Modifier = Modifier,
    surveyCard: SurveyDTO,
    nav: NavController,
    onSubmitSurvey: (String) -> Unit,
    onSubmitQuestionAnswer: (String, String, String) -> Unit,
    navController: NavController
) {
    var showConfirmationDialog by rememberSaveable {
        mutableStateOf(false)
    }
    BackHandler(
        enabled = true,
        onBack = {
            showConfirmationDialog = true
        }
    )

    if (showConfirmationDialog) {
        ConfirmationDialog.Show { confirmed ->
            if (confirmed) {
                navController.popBackStack()
            } else {
                showConfirmationDialog = false
            }
        }
    }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp, top = 5.dp)
                    .alpha(0.6f)
                    .clickable { showConfirmationDialog = true },
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    onSubmitSurvey(surveyCard.id)
                    nav.popBackStack()
                }
            ) {
                Text(stringResource(R.string.submit))
            }
        }

        Text(
            text = surveyCard.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 9.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        LazyColumn {
            items(surveyCard.questions) {
                QuestionCard(question = it, unSubmitAnswer = onSubmitQuestionAnswer, surveyId = surveyCard.id)
            }
        }
    }

}

@Composable
private fun QuestionCard(
    modifier: Modifier = Modifier,
    question: SurveyQuestion,
    surveyId: String,
    unSubmitAnswer: (String, String, String) -> Unit
) {
    var selectedAns by rememberSaveable{
        mutableStateOf(question.options[0])
    }

    val onSelect: (String) -> Unit = {ans ->
        selectedAns = ans
        unSubmitAnswer(surveyId, question.id, ans)
    }

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        text = question.title,
        textAlign = TextAlign.Center
    )
    Column{
        for (option in question.options) {
            Row(
                modifier = Modifier
                    .clickable { onSelect(option) }
                    .fillMaxWidth()
                    .padding(3.dp),//.border(BorderStroke(1.dp, Color.Black)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedAns == option,
                    onClick = { onSelect(option) },
                )
                Text(text = option)
            }
        }
    }
}
