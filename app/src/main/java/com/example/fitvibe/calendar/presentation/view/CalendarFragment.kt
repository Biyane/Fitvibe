package com.example.fitvibe.calendar.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitvibe.calendar.presentation.adapter.CalendarDayAdapter
import com.example.fitvibe.calendar.presentation.adapter.CalendarDayListener
import com.example.fitvibe.calendar.presentation.adapter.CalendarTimeAdapter
import com.example.fitvibe.calendar.presentation.adapter.CalendarTrainingAdapter
import com.example.fitvibe.calendar.presentation.adapter.CalendarTrainingListener
import com.example.fitvibe.databinding.CalendarFragmentBinding
import com.example.fitvibe.utils.Trainer
import com.example.fitvibe.utils.calendarHm
import com.example.fitvibe.utils.getFullDayOfTheWeek
import java.util.*

class CalendarFragment : Fragment(), CalendarDayListener, CalendarTrainingListener {

    private var _binding: CalendarFragmentBinding? = null
    private val binding get() = _binding!!

    private val timeList: MutableSet<String> = mutableSetOf()
    private val trainerList: MutableList<Trainer> = mutableListOf()
    private var timeAdapter: CalendarTimeAdapter? = null
    private var trainerAdapter: CalendarTrainingAdapter? = null
    private val hm: HashMap<Trainer, String> = hashMapOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalendarFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDayClick(day: Day) {
        val data = calendarHm[day.day]
        if (data.isNullOrEmpty()) {
            timeList.clear()
            trainerList.clear()
        } else {
            for (d in data) {
                if (timeList.add(d.timeValue)) {
                    trainerList.add(d.trainer)
                    hm[d.trainer] = d.timeValue
                }
            }
        }
        updateLeftRecycler()
        updateRightRecycle()
    }

    override fun onDeleteClick(trainer: Trainer) {
        trainerList.remove(trainer)
        timeList.remove(hm[trainer])
        trainerAdapter?.setList(trainerList)
        timeAdapter?.setList(timeList.toList())
    }

    private fun updateRightRecycle() {
        trainerAdapter?.setList(trainerList.toList())
    }

    private fun updateLeftRecycler() {
        timeAdapter?.setList(timeList.toList())
    }

    private fun initViews() {
        val calendar = Calendar.getInstance()
        binding.topDateNumber.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
        binding.topDayName.text = getFullDayOfTheWeek(calendar.get(Calendar.DAY_OF_WEEK))
        binding.month.text = getMonth(calendar.get(Calendar.MONTH)) + " 2022"
        initTopRecycler()
        initLeftRecycler()
        initMainRecycler()
    }

    private fun getMonth(month: Int): String = when (month) {
        Calendar.MAY -> "Май"
        Calendar.JUNE -> "Июнь"
        else -> "Остальные"
    }

    private fun initTopRecycler() {
        val adapter = CalendarDayAdapter(this)
        binding.daysRecyclerView.adapter = adapter
    }

    private fun initLeftRecycler() {
        timeAdapter = CalendarTimeAdapter()
        binding.timeRecyclerView.adapter = timeAdapter
    }

    private fun initMainRecycler() {
        trainerAdapter = CalendarTrainingAdapter(this)
        binding.trainingRecyclerView.adapter = trainerAdapter
    }

    companion object {
        const val TAG = "calendar_fragment"
    }

}

data class Day(
    val day: String,
    val time: String
)