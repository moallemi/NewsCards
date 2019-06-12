package me.moallemi.newscards.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import me.moallemi.newscards.util.image.GlideApp

fun ImageView.load(url: String?, placeHolder: Drawable? = null, roundedCorner: Int = 0) {
    url?.let {
        GlideApp.with(this)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions().apply {
                placeholder(placeHolder)
                if (roundedCorner > 0) {
                    transform(MultiTransformation(CenterCrop(), RoundedCorners(roundedCorner)))
                }
            })
            .into(this)
    }
}