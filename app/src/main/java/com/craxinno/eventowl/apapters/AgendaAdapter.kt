package com.craxinno.eventowl.apapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.craxinno.eventowl.data.models.AgendaListModel
import com.craxinno.eventowl.data.models.AttendeeModel
import com.craxinno.eventowl.databinding.LayoutAgendaBinding
import com.craxinno.eventowl.ui.agenda.AgendaDetailActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AgendaAdapter (private val agendaList : List<AgendaListModel>, private val context : Context) : RecyclerView.Adapter<AgendaAdapter.AgendaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutAgendaBinding.inflate(inflater, parent, false)
        return AgendaHolder(binding)
    }

    override fun onBindViewHolder(holder: AgendaHolder, position: Int) {
        holder.bind(agendaList[position])
        holder.setAgendaPeriod(agendaList[position].start_date!!, agendaList[position].end_date!!)
    }

    override fun getItemCount() : Int  = agendaList.size

    inner class AgendaHolder (private val binding : LayoutAgendaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(agendaModel : AgendaListModel) {
            binding.agendaModel = agendaModel
            binding.clickListener = View.OnClickListener {
                val intent = Intent(context, AgendaDetailActivity::class.java)
                intent.putExtra("id", agendaModel.id)
                context.startActivity(intent)
            }
            binding.executePendingBindings()
        }

        fun setAgendaPeriod(startDate: String, endDate: String) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val outPutFormat = SimpleDateFormat("hh:mm a", Locale.US)
            val startTime : Date? = inputFormat.parse(startDate)
            val endTime : Date? = inputFormat.parse(endDate)
            val period = "${outPutFormat.format(startTime!!)} - ${outPutFormat.format(endTime!!)} EST"
            binding.agendaPeriod = period
        }
    }
}