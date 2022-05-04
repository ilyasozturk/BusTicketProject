package com.example.busticketprojectkotlin.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.busticketprojectkotlin.R

fun ImageView.downloadImage(url: String?){
    val options = RequestOptions().placeholder(createPlaceHolder(context)).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}



fun createPlaceHolder(context:Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius= 40f
        start()
    }
}

@BindingAdapter("load_image")
fun loadImage(view: ImageView,url: String?){
    view.downloadImage(url)
}

fun Fragment.LogData(message:String){
    Log.d(this.javaClass.simpleName, "Log -->: $message")
}
