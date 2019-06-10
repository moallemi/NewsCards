package me.moallemi.newscards.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.moallemi.newscards.app.NewsCardsApp
import me.moallemi.newscards.di.module.AppModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<NewsCardsApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<NewsCardsApp>
}