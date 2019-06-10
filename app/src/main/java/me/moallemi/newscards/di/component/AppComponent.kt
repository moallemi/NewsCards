package me.moallemi.newscards.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.moallemi.newscards.app.NewsCardsApp
import me.moallemi.newscards.di.module.AppModule
import me.moallemi.newscards.di.module.NetworkModule
import me.moallemi.newscards.di.module.ViewModelFactoryModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<NewsCardsApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<NewsCardsApp>
}