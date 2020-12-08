package me.moallemi.newscards.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.moallemi.newscards.di.component.AppComponent
import me.moallemi.newscards.di.component.DaggerAppComponent
import me.moallemi.newscards.util.theme.ThemeManager
import javax.inject.Inject

class NewsCardsApp : DaggerApplication() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var themeManager: ThemeManager

    override fun onCreate() {
        super.onCreate()

        instanse = this

        themeManager.initTheme()
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