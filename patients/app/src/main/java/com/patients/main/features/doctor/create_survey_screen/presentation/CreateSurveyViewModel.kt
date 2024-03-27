package com.patients.main.features.doctor.create_survey_screen.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateSurveyViewModel : ViewModel() {

    private var _surveyName = MutableStateFlow("")
    val surveyName = _surveyName.asStateFlow()

    private var _questions = MutableStateFlow<MutableList<Question>>(mutableStateListOf())
    val questions = _questions.asStateFlow()

    private var _questionsUniqueIDs = MutableStateFlow(0)


    private var _editingOption = MutableStateFlow("")
    val editingOption = _editingOption.asStateFlow()

    private var _addedOptions = MutableStateFlow<MutableList<QuestionOption>>(mutableStateListOf())
    val addedOptions = _addedOptions.asStateFlow()

    fun updateOption(str: String) {
        _editingOption.value = str
    }

    fun addQuestion() {
        _questionsUniqueIDs.value += 1
        _questions.value.add(Question(id = _questionsUniqueIDs.value.toString()))
    }

    fun deleteQuestion(id: String) {
        _questions.value.removeIf { it.id == id }
        _addedOptions.value.removeAll{
            it.id == id
        }
    }

    fun removeOption(questionId: String, option: String) {
        for (i in 0 ..< _addedOptions.value.size) {
            if (_addedOptions.value[i].id == questionId && _addedOptions.value[i].value == option) {
                _addedOptions.value.removeAt(i)
                return
            }
        }
    }

    fun addOptionToQuestion(questionId: String) {
        _addedOptions.value.add(
            QuestionOption(
                id = questionId,
                value = editingOption.value
            ))
    }

    fun getQuestionById(id: String) : Question {
        return questions.value.find { it.id == id } as Question
    }

    fun updateQuestionName(id: String, name: String) {
        _questions.value.replaceAll {
            if (it.id == id) {
                it.copy(title = name)
            } else {
                it
            }
        }
    }

    fun updateSurveyName(name: String) {
        _surveyName.value = name
    }

    fun isValidSurvey() : Boolean {
        var res = true
        if (surveyName.value.isEmpty()) {
            Log.d("CHECK SURVEY", "survey name=" + surveyName.value)
            return false
        } else if (questions.value.size == 0) {
            Log.d("CHECK SURVEY", "question num=" + questions.value.size)
            return false
        } else if (questions.value.any { it.title.isEmpty() }) {
            Log.d("CHECK SURVEY", "empty q title=")
            return false
        }
        val questionOptions = mutableMapOf<String, Int>()
        for (opt in addedOptions.value) {
            val optNum = questionOptions[opt.id] ?: 0
            questionOptions[opt.id] = optNum + 1
        }
        questionOptions.map {
            Log.d("CHECKOPTIONS", it.key + "=" + it.value)
        }
        return !(questionOptions.any { it.value < 2 } || questionOptions.isEmpty())
    }

}