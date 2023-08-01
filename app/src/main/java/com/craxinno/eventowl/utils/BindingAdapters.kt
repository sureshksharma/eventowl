package com.craxinno.eventowl.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.craxinno.eventowl.apapters.ImageAdapter
import com.craxinno.eventowl.apapters.SpeakerAdapter
import com.craxinno.eventowl.data.models.AgendaSpeaker
import com.craxinno.eventowl.data.models.AttendeeModel

@BindingAdapter("attendees", "imageSize")
fun setImages(recyclerView: RecyclerView, attendees : List<AttendeeModel>?, imageSize : Int) {
    if (attendees.isNullOrEmpty()) return

    val density = recyclerView.context.resources.displayMetrics.density
    val screenWidth : Int = (recyclerView.context.resources.displayMetrics.widthPixels / density).toInt()
    val layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
    recyclerView.layoutManager = layoutManager
    val imageAdapter = ImageAdapter(attendees, screenWidth, imageSize)
    recyclerView.adapter = imageAdapter
}

@BindingAdapter("speakers")
fun setSpeakers(rvSpeakers: RecyclerView, speakers: List<AgendaSpeaker>?) {
    if (speakers.isNullOrEmpty()) return

    val layoutManager = LinearLayoutManager(rvSpeakers.context)
    rvSpeakers.layoutManager = layoutManager
    val speakerAdapter = SpeakerAdapter(speakers)
    rvSpeakers.adapter = speakerAdapter
}
