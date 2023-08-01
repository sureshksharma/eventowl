package com.craxinno.eventowl.ui.agenda

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.craxinno.eventowl.data.models.AgendaModel
import com.craxinno.eventowl.databinding.ActivityAgendaDetailBinding
import com.craxinno.eventowl.utils.hide
import com.craxinno.eventowl.utils.show
import com.craxinno.eventowl.utils.snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.Locale

class AgendaDetailActivity : AppCompatActivity(), AgendaDataListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AgendaDetailViewModelFactory by instance()
    private lateinit var binding: ActivityAgendaDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.setOnApplyWindowInsetsListener { _, insets ->
                val controller = window.insetsController
                if (controller != null) {
                    controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
                insets
            }
        }

        binding = ActivityAgendaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, factory)[AgendaDetailViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.dataListener = this

        viewModel.aid = intent.getIntExtra("id", 0)

        viewModel.getAgenda()

        binding.backClickListener = View.OnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun onStarted() {
        binding.progressBar.show()
    }

    override fun onSuccess(response: AgendaModel) {
        binding.progressBar.hide()

        binding.agendaModel = response
        //Set header image with glide
        Glide.with(binding.imageView).load(response.imgPath + response.header_img).into(binding.imageView)

        //set date and period
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        binding.agendaDate = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(inputFormat.parse(response.start_date!!)!!)
        val period = "${SimpleDateFormat("hh:mm a", Locale.getDefault()).format(inputFormat.parse(response.start_date!!)!!)} - ${SimpleDateFormat("hh:mm a", Locale.getDefault()).format(inputFormat.parse(response.end_date!!)!!)} EST"
        binding.agendaPeriod = period

        //set description
        binding.tvDescription.text = Html.fromHtml(response.description, Html.FROM_HTML_MODE_COMPACT)

        //set sponsor image if available else visible false
        if (response.sponsor_img != null && response.sponsor_img!!.isNotEmpty())
            Glide.with(binding.ivSponsor).load(response.imgPath + response.sponsor_img).into(binding.ivSponsor)
        else
            binding.ivSponsor.visibility = View.GONE

    }

    override fun onFailed(message: String) {
        binding.progressBar.hide()
        binding.rootLayout.snackbar(message)
    }


}