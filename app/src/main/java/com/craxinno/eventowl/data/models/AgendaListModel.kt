package com.craxinno.eventowl.data.models

data class AgendaListModel (
    var id : Int? = null,
    var name : String? = null,
    var start_date : String? = null,
    var end_date : String? = null,
    var my_agenda : Int? = null,
    var attendees : List<AttendeeModel>? = null,
        ) {
}