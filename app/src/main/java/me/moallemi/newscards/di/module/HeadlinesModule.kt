package me.moallemi.newscards.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.newscards.data.datasource.ArticleRemoteDataSource
import me.moallemi.newscards.remote.datasource.ArticleRemoteDataSourceImpl
import me.moallemi.newscards.ui.headline.HeadlinesFragment
import me.moallemi.newscards.ui.headline.HeadlinesViewModel

@Module
abstract class HeadlinesModule {

    @ContributesAndroidInjector
    abstract fun headlinesFragment(): HeadlinesFragment

    @Binds
    @IntoMap
    @ViewModelKey(HeadlinesViewModel::class)
    abstract fun bindHeadlinesViewModel(headlinesViewModel: HeadlinesViewModel): ViewModel

    @Binds
    abstract fun bindArticleRemoteDataSource(
        articleRemoteDataSourceImpl: ArticleRemoteDataSourceImpl
    ): ArticleRemoteDataSource
}