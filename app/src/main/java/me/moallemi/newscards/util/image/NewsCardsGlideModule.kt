package me.moallemi.newscards.util.image

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import me.moallemi.newscards.app.NewsCardsApp
import java.io.InputStream

@Excludes(com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule::class)
@GlideModule
class NewsCardsGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        val okHttpClient = NewsCardsApp.instanse.appComponent.getOkHttp()
        val factory = OkHttpUrlLoader.Factory(okHttpClient)

        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }
}