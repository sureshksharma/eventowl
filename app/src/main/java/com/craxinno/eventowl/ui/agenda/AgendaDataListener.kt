package com.craxinno.eventowl.ui.agenda

import com.craxinno.eventowl.data.models.AgendaModel

interface AgendaDataListener {
    fun onStarted()
    fun onSuccess(response : AgendaModel)
    fun onFailed(message : String)
}