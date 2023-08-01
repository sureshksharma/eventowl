package com.craxinno.eventowl.ui.home

import com.craxinno.eventowl.data.models.AgendaListModel

interface DataListener {
    fun onStarted()
    fun onSuccess(response : List<AgendaListModel>)
    fun onFailed(message : String)
}