package com.craxinno.eventowl.data.network.responses

import com.craxinno.eventowl.data.models.AgendaModel

data class AgendaResponse (
    val replyCode : String?,
    val replyMsg : String?,
    val data : AgendaModel?,
    val cmd : String? = null,
    val imgPath : String? = null,
    val attendeeImgPath : String? = null,
    val speakerImgPath : String? = null,
        ) {
}