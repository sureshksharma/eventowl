package com.craxinno.eventowl.ui.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.craxinno.eventowl.data.repository.AgendaRepository

@Suppress("UNCHECKED_CAST")
class AgendaDetailViewModelFactory (
    private val repository: AgendaRepository
        ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AgendaDetailViewModel(repository) as T
    }
}