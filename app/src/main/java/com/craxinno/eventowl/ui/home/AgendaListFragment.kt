package com.craxinno.eventowl.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.craxinno.eventowl.R
import com.craxinno.eventowl.apapters.AgendaAdapter
import com.craxinno.eventowl.data.models.AgendaListModel
import com.craxinno.eventowl.data.repository.AgendaRepository
import com.craxinno.eventowl.databinding.FragmentAgendaListBinding
import com.craxinno.eventowl.utils.hide
import com.craxinno.eventowl.utils.show
import com.craxinno.eventowl.utils.snackbar
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class AgendaListFragment() : Fragment(), DataListener, KodeinAware {

    override val kodein by kodein()
    private lateinit var binding : FragmentAgendaListBinding

    private val factory: AgendaViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda_list, container, false)

        val repository: AgendaRepository by kodein.instance()

        val viewModel = ViewModelProvider(this, factory)[AgendaViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.dataListener = this
        viewModel.getAgendaList()

        return binding.root
    }

    override fun onStarted() {
        binding.progressBar.show()
    }

    override fun onSuccess(response: List<AgendaListModel>) {
        binding.progressBar.hide()
        val adapter = AgendaAdapter(response, requireContext())
        binding.rvAgenda.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAgenda.adapter = adapter
    }

    override fun onFailed(message: String) {
        binding.progressBar.hide()
        binding.rootLayout.snackbar(message)
    }
}