package me.moallemi.newscards.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.moallemi.newscards.app.NewsCardsApp
import me.moallemi.newscards.di.module.*
import me.moallemi.newscards.di.scope.AppScope
import okhttp3.OkHttpClient

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        HeadlinesModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<NewsCardsApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<NewsCardsApp>

    fun getOkHttp(): OkHttpClient
}