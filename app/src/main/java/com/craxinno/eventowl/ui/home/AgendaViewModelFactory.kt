package com.craxinno.eventowl.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.craxinno.eventowl.data.repository.AgendaRepository

@Suppress("UNCHECKED_CAST")
class AgendaViewModelFactory (
    private val repository: AgendaRepository
        ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AgendaViewModel(repository) as T
    }
}