package me.moallemi.newscards.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.moallemi.newscards.ui.MainActivity

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}