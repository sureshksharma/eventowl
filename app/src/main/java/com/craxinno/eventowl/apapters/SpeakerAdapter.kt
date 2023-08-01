package com.craxinno.eventowl.apapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.craxinno.eventowl.data.models.AgendaSpeaker
import com.craxinno.eventowl.databinding.SingleSpeakerLayoutBinding

class SpeakerAdapter(private val list : List<AgendaSpeaker>) : RecyclerView.Adapter<SpeakerAdapter.SpeakerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleSpeakerLayoutBinding.inflate(inflater, parent, false)
        return SpeakerHolder(binding)
    }

    override fun onBindViewHolder(holder: SpeakerHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class SpeakerHolder (private val binding : SingleSpeakerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(agendaSpeaker : AgendaSpeaker) {
            binding.speakerModel = agendaSpeaker
            setImage(agendaSpeaker.image!!)
            binding.executePendingBindings()
        }

        private fun setImage(imageUrl : String) {
            Glide.with(binding.speakerImage).load(imageUrl).into(binding.speakerImage)
        }
    }
}