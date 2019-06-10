package me.moallemi.newscards.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.moallemi.newscards.di.component.DaggerAppComponent

class NewsCardsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}