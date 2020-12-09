package me.moallemi.newscards.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import me.moallemi.newscards.app.NewsCardsApp
import me.moallemi.newscards.di.scope.AppScope

@Module
class AppModule {

    @AppScope
    @Provides
    fun provideContext(app: NewsCardsApp): Context = app.applicationContext

    @AppScope
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("AppPreference", Context.MODE_PRIVATE)
    }
}