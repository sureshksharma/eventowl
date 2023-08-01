package com.craxinno.eventowl.data.models

data class AgendaModel (
    var id : Int? = null,
    var user_id : Int? = null,
    var event_id : Int? = null,
    var name : String? = null,
    var start_date : String? = null,
    var end_date : String? = null,
    var description : String? = null,
    var sponsor_name : String? = null,
    var sponsor_img : String? = null,
    var location_name : String? = null,
    var header_img : String? = null,
    var my_agenda : Int? = null,
    var attendees : List<AttendeeModel>? = null,
    var register_links : List<RegisterLink>? = null,
    var agenda_documents : List<AgendaDocument>? = null,
    var agenda_speakers : List<AgendaSpeaker>? = null,
    var cmd : String? = null,
    var imgPath : String? = null,
    var attendeeImgPath : String? = null,
    var speakerImgPath : String? = null,
        ) {
}