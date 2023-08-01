package com.craxinno.eventowl.data.repository

import com.craxinno.eventowl.data.network.ApiRequest
import com.craxinno.eventowl.data.network.SafeNetworkRequest
import com.craxinno.eventowl.data.network.responses.AgendaListResponse
import com.craxinno.eventowl.data.network.responses.AgendaResponse

class AgendaRepository (
    private val apiRequest: ApiRequest
        ) : SafeNetworkRequest() {
    suspend fun agendaList(eid : Int, pid : Int) : AgendaListResponse {
        return apiRequest { apiRequest.agendaList(eid, pid) }
    }

    suspend fun getAgenda(sid : Int, eid : Int, pid : Int, aid : Int) : AgendaResponse {
        return apiRequest { apiRequest.getAgenda(sid, eid, pid, aid) }
    }
}