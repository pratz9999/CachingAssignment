package com.bayut.caching.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bayut.caching.AppController
import com.bayut.caching.component.image.BitmapUtils


@BindingAdapter("url")
fun loadImage(view: ImageView, urls: List<String?>?) {
    BitmapUtils.display(AppController.instance, view, urls?.get(0))

//    Glide.with(view.context)
//        .load(urls?.get(0) ?: R.drawable.outline_broken_image_24)
//        .apply(RequestOptions().centerCrop())
//        .placeholder(R.drawable.baseline_downloading_24)
//        .diskCacheStrategy(DiskCacheStrategy.ALL)
//        .into(view)
}



