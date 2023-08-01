package com.craxinno.eventowl.ui.home

import androidx.lifecycle.ViewModel
import com.craxinno.eventowl.data.models.AgendaListModel
import com.craxinno.eventowl.data.repository.AgendaRepository
import com.craxinno.eventowl.utils.ApiException
import com.craxinno.eventowl.utils.Coroutines
import com.craxinno.eventowl.utils.NoInternetException

class AgendaViewModel (
    private val repository: AgendaRepository
        ) : ViewModel() {
    val eid : Int = 1989
    val pid : Int = 117195
    var dataListener : DataListener? = null

    fun getAgendaList() {
        dataListener?.onStarted()

        Coroutines.main {
            try {
                val agendaResponse = repository.agendaList(eid, pid)
                agendaResponse.data?.let {
                    dataListener?.onSuccess(agendaResponse.data)
                    return@main
                }
                dataListener?.onFailed(agendaResponse.replyMessage!!)
            } catch (e: ApiException) {
                dataListener?.onFailed(e.message!!)
            } catch (e : NoInternetException) {
                dataListener?.onFailed(e.message!!)
            }
        }
    }
}