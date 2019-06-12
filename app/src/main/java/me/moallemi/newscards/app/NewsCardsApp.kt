package me.moallemi.newscards.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.moallemi.newscards.di.component.AppComponent
import me.moallemi.newscards.di.component.DaggerAppComponent

class NewsCardsApp : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instanse = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val injector = DaggerAppComponent.factory().create(this)
        appComponent = injector as AppComponent
        return injector
    }

    companion object {
        lateinit var instanse: NewsCardsApp
    }
}