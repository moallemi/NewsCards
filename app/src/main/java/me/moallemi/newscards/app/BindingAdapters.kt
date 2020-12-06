package me.moallemi.newscards.app

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import me.moallemi.newscards.extension.load

@BindingAdapter(
    value = ["imageUrl", "placeHolder", "roundedCorner"],
    requireAll = false
)
fun bindImageFromUrl(
    imageView: ImageView,
    imageUrl: String?,
    placeHolder: Drawable?,
    roundedCorner: Float? = 0F
) {
    if (!imageUrl.isNullOrEmpty()) {
        imageView.load(
            imageUrl,
            placeHolder,
            roundedCorner?.toInt() ?: 0
        )
    }
}