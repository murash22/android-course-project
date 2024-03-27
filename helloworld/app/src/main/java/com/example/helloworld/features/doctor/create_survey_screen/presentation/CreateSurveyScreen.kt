package com.example.helloworld.features.doctor.create_survey_screen.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloworld.R
import com.example.helloworld.data.CURRENT_MAX_SURVEYS
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.SurveyQuestion
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class QuestionOption(
    val id: String,  // questionId
    var value: String,
)

data class Question(
    val id: String,
    var title: String = "",
)


fun createSurvey(vModel: CreateSurveyViewModel, patientId: String, doctorId: String) : SurveyDTO? {
    if (vModel.isValidSurvey()) {
        CURRENT_MAX_SURVEYS += 1
        return SurveyDTO(
            id = CURRENT_MAX_SURVEYS.toString(),
            openDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            title = vModel.surveyName.value,
            completed = false,
            feedback = "",
            patientID = patientId,
            doctorID = doctorId,
            questions = vModel.questions.value.map { q ->
                SurveyQuestion(
                    id = q.id,
                    title = q.title,
                    options = vModel.addedOptions.value.filter { opt ->
                        opt.id == q.id
                    } .toList().map { option ->
                        option.value
                    }
                )
            },
            closeDate = null
        )
    }
    return null
}


@Composable
fun CreateSurveyScreen(
    onBack: () -> Unit,
    patient: PatientDTO,
    doctorId: String,
    onCreateSurvey: (SurveyDTO) -> Unit,
    modifier: Modifier = Modifier,
    vModel: CreateSurveyViewModel = viewModel(),
) {

    val surveyName by vModel.surveyName.collectAsState()
    val questions = vModel.questions.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Text(
                fontSize = 18.sp,
                text = "Создание опроса"
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                modifier = Modifier,
                onClick = {
                    val survey = createSurvey(vModel, patientId = patient.id, doctorId = doctorId)
                    if (survey != null) {
                        Log.d(
                            "SURVEY",
                            surveyName
                        )
                        onCreateSurvey(survey)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(40)
            ) {
                Text(
                    text = "Создать",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                )
            }
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                ) {
                    Text(
                        fontSize = 22.sp,
                        text = stringResource(id = R.string.patient_name, patient.name)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        fontSize = 22.sp,
                        text = "ID: #${patient.id}"
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                ) {
                    Text(
                        fontSize = 18.sp,
                        text = "Введите название опроса:",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(vertical = 3.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth(0.8f),
                        maxLines = 1,
                        value = surveyName,
                        isError = surveyName.isEmpty(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        placeholder = { Text(text = "Название опроса") },
                        onValueChange = { vModel.updateSurveyName(it) }
                    )
                }
            }
            items(questions.value) {
                Log.d("QUESTION", questions.value.size.toString() + "  " + it.id)
                EditableQuestionCard(
                    id = it.id,
                    vModel = vModel
                )
            }
            item {
                OutlinedButton(
                    onClick = {
                        vModel.addQuestion()
                    },
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
                        text = "Добавить вопрос",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
private fun EditableQuestionCard(
    vModel: CreateSurveyViewModel,
    id: String,
    modifier: Modifier = Modifier,
) {
    var isAddOption by rememberSaveable {
        mutableStateOf(false)
    }
    val options by vModel.addedOptions.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .border(
                1.dp,
                color = if (options.filter { it.id == id }.size < 2
                    || vModel.getQuestionById(id).title.isEmpty()) {
                    Color.Red
                } else Color.Green,
                shape = RoundedCornerShape(10)
            ),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Введите название вопроса",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        vModel.deleteQuestion(id)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Delete option"
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(0.8f),
                maxLines = 1,
                value = vModel.getQuestionById(id).title,
                isError = vModel.getQuestionById(id).title.isEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                placeholder = { Text(text = "Название вопроса") },
                onValueChange = {
                    vModel.updateQuestionName(id, it)
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            if (isAddOption) {
                AddQuestionOption(
                    questionId = id,
                    vModel = vModel,
                    onDismissDialog = {
                        isAddOption = false
                    }
                )
            }
            for (opt in options.filter { it.id == id }) {
                DisplayQuestionOption(
                    option = opt.value,
                    onDeleteOption = { vModel.removeOption(questionId = opt.id, option = opt.value) }
                )
            }
            TextButton(
                onClick = {
                    isAddOption = true
                },
                colors = ButtonDefaults.textButtonColors(contentColor = Color.DarkGray)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Text(text = "Добавить вариант")
            }
        }
    }

}


@Composable
fun AddQuestionOption(
    questionId: String,
    vModel: CreateSurveyViewModel,
    onDismissDialog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val option by vModel.editingOption.collectAsState()
    Dialog(
        onDismissRequest = {
            onDismissDialog()
            vModel.updateOption("")
        }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        fontSize = 18.sp,
                        color = Color.Black,
                        text = "Введите вариант ответа"
                    )
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            vModel.updateOption("")
                            onDismissDialog()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close",
                            tint = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 1,
                    value = option,
                    isError = option.isEmpty(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    placeholder = { Text(text = "Вариант ответа") },
                    onValueChange = {
                        vModel.updateOption(it)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = {
                        if (option.isNotEmpty()) {
                            vModel.addOptionToQuestion(questionId)
                            vModel.updateOption("")
                            onDismissDialog()
                        }
                    },
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        text = "Готово"
                    )
                }
            }
        }
    }

}


@Composable
fun DisplayQuestionOption(
    option: String,
    onDeleteOption: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        RadioButton(
            selected = false,
            onClick = null
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            modifier = Modifier,
            text = option
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onDeleteOption
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Delete option"
            )
        }
    }
}