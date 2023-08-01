package com.craxinno.eventowl

import android.app.Application
import com.craxinno.eventowl.data.network.ApiRequest
import com.craxinno.eventowl.data.network.NetworkConnectionInterceptor
import com.craxinno.eventowl.data.repository.AgendaRepository
import com.craxinno.eventowl.ui.agenda.AgendaDetailViewModelFactory
import com.craxinno.eventowl.ui.home.AgendaViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiRequest(instance()) }
        bind() from singleton { AgendaRepository(instance()) }
        bind() from provider { AgendaViewModelFactory(instance()) }
        bind() from provider { AgendaDetailViewModelFactory(instance()) }
    }
}