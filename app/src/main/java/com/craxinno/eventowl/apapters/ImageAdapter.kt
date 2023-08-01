package com.craxinno.eventowl.apapters

import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.craxinno.eventowl.R
import com.craxinno.eventowl.data.models.AttendeeModel
import com.craxinno.eventowl.databinding.SingleImageViewBinding

class ImageAdapter (private val attendeeList: List<AttendeeModel>, private val screenWidth : Int, private val imageSize : Int) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    var maxImages : Int = 0
    var remainingImages : Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val imageSize : Int = imageSize
        val gapSize : Int = 20
        maxImages = (screenWidth / (imageSize + gapSize)) - 1
        remainingImages = attendeeList.size - (maxImages + 1)
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleImageViewBinding.inflate(
            inflater, parent,false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val attendee = attendeeList[position]
        holder.bindImage(attendee.image!!)

        if (position == maxImages) {
            holder.showRemainingText("$remainingImages")
        } else {
            holder.hideRemainingText()
        }

    }

    override fun getItemCount() : Int {
        return if (remainingImages > 0) maxImages + 1
        else attendeeList.size
    }

    inner class ImageViewHolder(private val binding: SingleImageViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindImage(imageUrl : String) {
            val sizeInPixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, imageSize.toFloat(), binding.root.resources.displayMetrics
            ).toInt()
            binding.circleImage.layoutParams = LinearLayout.LayoutParams(sizeInPixels, sizeInPixels)
            Glide.with(binding.circleImage).load(imageUrl).into(binding.circleImage)
        }

        @SuppressLint("NotifyDataSetChanged")
        fun showRemainingText(count : String) {
            binding.tvCount.visibility = View.VISIBLE
            binding.remainingCount = count
            binding.tvCount.setOnClickListener {
                binding.tvCount.visibility = View.GONE
                maxImages += remainingImages + 1
                remainingImages = 0
                notifyDataSetChanged()
            }
        }

        fun hideRemainingText() {
            binding.tvCount.visibility = View.GONE
            binding.tvCount.setOnClickListener(null)
        }
    }
}