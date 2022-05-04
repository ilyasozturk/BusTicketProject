package com.example.busticketprojectkotlin.ui.home_screen

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.busticketprojectkotlin.model.DateModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class HomeFragmentViewModel : ViewModel() {
    private var dateNow = LocalDate.now()


    private val _selectDatePicker = MutableLiveData<DateModel>()
    val selectDatePicker: LiveData<DateModel> = _selectDatePicker


    private val _selectToday = MutableLiveData<DateModel>()
    val selectToday: LiveData<DateModel> = _selectToday

    private val _selectTomorrow = MutableLiveData<DateModel>()
    val selectTomorrow: LiveData<DateModel> = _selectTomorrow

    init {
        selectedToday()
        selectedTomorrow()
    }

    private fun selectedToday() {

        val day = dateNow.dayOfMonth
        val month = dateNow.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val year = dateNow.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val today = DateModel(day.toString(), month, year)
        _selectToday.value = today
    }

    private fun selectedTomorrow() {

        val day = dateNow.plusDays(1).dayOfMonth
        val month = dateNow.plusDays(1).month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val year = dateNow.plusDays(1).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val tomorrow = DateModel(day.toString(), month, year)
        _selectTomorrow.value = tomorrow
    }

    fun datePickerDialogShow(manager: FragmentManager) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setTitleText("Gidiş Tarihi Seçiniz")
            .build()
        datePicker.show(manager, "tag")


        datePicker.addOnPositiveButtonClickListener {

            val dateMonthFormatter = SimpleDateFormat("MMMM", Locale.getDefault())
            val month = dateMonthFormatter.format(Date(it))

            val dateDayFormatter = SimpleDateFormat("dd", Locale.getDefault())
            val day = dateDayFormatter.format(Date(it))

            val dateDayOfWeekFormatter = SimpleDateFormat("EEE", Locale.getDefault())
            val dayOfWeek = dateDayOfWeekFormatter.format(Date(it))

            val selectDatePicker = DateModel(day,month,dayOfWeek)
            _selectDatePicker.value = selectDatePicker

        }

    }

}