package com.example.busticketprojectkotlin.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.busticketprojectkotlin.R

object LoadingDialog {
    private lateinit var dialog: Dialog
    fun displayLoadingWithText(context: Context?, text: String?, cancelable: Boolean) { // function -- context(parent (reference))
        dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(cancelable)
        val textView = dialog.findViewById<TextView>(R.id.text)
        textView.text = text
        try {
            dialog.show()
        } catch (e: Exception) {
        }
    }

    fun hideLoading() {
        try {
            dialog.dismiss()
        } catch (e: Exception) {
        }
    }
}