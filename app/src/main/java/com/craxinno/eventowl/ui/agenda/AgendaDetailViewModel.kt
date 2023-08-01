package com.craxinno.eventowl.ui.agenda

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModel
import com.craxinno.eventowl.data.repository.AgendaRepository
import com.craxinno.eventowl.utils.ApiException
import com.craxinno.eventowl.utils.Coroutines
import com.craxinno.eventowl.utils.NoInternetException

class AgendaDetailViewModel (
    private val repository: AgendaRepository
        ) : ViewModel() {

    val sid : Int = 1
    val eid : Int = 1989
    val pid : Int = 117195
    var aid : Int? = null

    var dataListener : AgendaDataListener? = null

    fun getAgenda() {
        dataListener?.onStarted()

        Coroutines.main {
            try {
                val agendaResponse = repository.getAgenda(sid, eid, pid, aid!!)
                agendaResponse.data?.let {
                    agendaResponse.data.imgPath = agendaResponse.imgPath
                    dataListener?.onSuccess(agendaResponse.data)
                    return@main
                }
                dataListener?.onFailed(agendaResponse.replyMsg!!)
            } catch (e: ApiException) {
                dataListener?.onFailed(e.message!!)
            } catch (e : NoInternetException) {
                dataListener?.onFailed(e.message!!)
            }
        }
    }

    fun launchUrl(view : View, url : String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        view.context.startActivity(intent)
    }
}