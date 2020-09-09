@file:Suppress("unused")

package com.rahat.standardapplicationrnd.kotlin_extension

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rahat.standardapplicationrnd.R
import com.rahat.standardapplicationrnd.ui.dialog.DialogUtil

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.showSneakBar(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) {
    when (this) {
        is Activity -> {
            this.showSneakBar(message)
        }
        is Fragment -> {
            (requireActivity() as Activity).showSneakBar(message)
        }
        else -> {
            Snackbar.make(view, message, length).show()
        }
    }
}

fun Activity.showSneakBar(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    try {
        val view = this.window.decorView.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar
            .make(view, message, length)
        val sbView = snackbar.view
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        textView.setPadding(0, 0, 0, 0)
        textView.setTextColor(Color.WHITE)
        snackbar.show()
    } catch (e: Exception) {
        this.showToast(message, Toast.LENGTH_SHORT)
    }
}

fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    this.requireContext().showToast(message, length)
}

fun Fragment.showSneakBar(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) {
    this.requireContext().showSneakBar(view, message, length)
}

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)


/*
*
* */
@Suppress("SpellCheckingInspection")
@Throws(IllegalArgumentException::class)
fun Context.sendGmail(
    recipients: Array<String>,
    subject: String,
    body: String,
    ccRecipients: Array<String>? = null,
    bccRecipients: Array<String>? = null,
) {
    require(recipients.isNotEmpty()) { getString(R.string.err_no_recipients) }
    require(subject.isNotBlank()) { getString(R.string.err_no_sub_gmail) }
    val intent = Intent(Intent.ACTION_SEND).apply {
        this.putExtra(Intent.EXTRA_EMAIL, recipients)
        this.putExtra(Intent.EXTRA_SUBJECT, subject)
        this.putExtra(Intent.EXTRA_TEXT, body)
        ccRecipients?.let { this.putExtra(Intent.EXTRA_CC, ccRecipients) }
        bccRecipients?.let { this.putExtra(Intent.EXTRA_CC, bccRecipients) }
        this.type = "message/rfc822"
        this.setPackage("com.google.android.gm")
    }
    try {
        this.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        this.showToast(getString(R.string.err_gmail_not_found), Toast.LENGTH_LONG)
    }
}

fun Context.shareApp() {
    val playUrlPrefix="https://play.google.com/store/apps/details?id="
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.title_share_app))
    val appUrl =
        "$playUrlPrefix$packageName"
    shareIntent.putExtra(Intent.EXTRA_TEXT, appUrl)
    this.startActivity(Intent.createChooser(shareIntent, getString(R.string.title_share_via)))
}