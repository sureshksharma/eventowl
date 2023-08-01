package com.craxinno.eventowl.data.network.responses

import com.craxinno.eventowl.data.models.AgendaListModel

data class AgendaListResponse (
    val replyCode : String?,
    val replyMessage : String?,
    val data : List<AgendaListModel>?
        ) {
}