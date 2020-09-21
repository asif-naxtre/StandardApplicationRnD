@file:Suppress("unused")

package com.rnd.standardapplicationrnd.kotlin_extension

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
import com.rnd.standardapplicationrnd.R

/**
 * Methods can be called from anywhere, where context is available.
 * */

/**
 * This method will show toast with given message for the given time span
 * @param message :message that you want to show in toast
 * @param length :time span for the toast, default value is <b>Toast.LENGTH_SHORT</b>
 * */
fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT)
{
    Toast.makeText(this, message, length).show()
}

/**
 * This method will show sneakBar, at given rootView or default window root
 * @param rootView :this view must be provided
 * if you want to show at a custom root else pass null value for default window as root
 * @param message :message that you want to show in toast
 * @param length :time span for the toast, default value is <b>Toast.LENGTH_SHORT</b>
 * */
fun Context.showSneakBar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    rootView: View? = null
)
{
    if (rootView != null) {
        Snackbar.make(rootView, message, length).show()
    }else if (this is Activity){
        this.showSneakBar(message)
    }
}


/**
 * This method will show sneakBar, at given rootView or default window root
 * this will show sneakBar will show at default window as root
 * @param message :message that you want to show in toast
 * @param length :time span for the toast, default value is <b>Toast.LENGTH_SHORT</b>
 * */
fun Activity.showSneakBar(message: String, length: Int = Snackbar.LENGTH_SHORT)
{
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

/**
 * @property com.rnd.standardapplicationrnd.kotlin_extension.showToast()
 * */
fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_SHORT)
{
    this.requireContext().showToast(message, length)
}


/**
 * @property com.rnd.standardapplicationrnd.kotlin_extension.showSneakBar()
 * */
fun Fragment.showSneakBar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    rootView: View? = null
)
{
    this.requireActivity().showSneakBar(message, length, rootView)
}

/**
 * This will return
 * @return LayoutInflater object.
 * */
val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)


/**
 * This method will directly open the gmail application to send mail with
 * @param recipients array of string, must be provied as it is minimum requirement
 * @param subject string, must be provided as mail sent without subject are considered as spam
 * @param body string, optional to provide, mails with blank body could be sent
 * @param ccRecipients array of strings, optional to provide, mails with no cc recipient could be sent
 * @param bccRecipients array of strings, optional to provide, mails with no bcc recipient could be sent
 * */
@Suppress("SpellCheckingInspection")
@Throws(IllegalArgumentException::class)
fun Context.sendGmail(
    recipients: Array<String>,
    subject: String,
    body: String,
    ccRecipients: Array<String>? = null,
    bccRecipients: Array<String>? = null,
)
{
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

/**
 * This method provides the functionality to
 * share the app, it open the share screen,with
 * available apps to share the link for the app
 * */
fun Context.shareApp()
{
    val playUrlPrefix = "https://play.google.com/store/apps/details?id="
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.title_share_app))
    val appUrl =
        "$playUrlPrefix$packageName"
    shareIntent.putExtra(Intent.EXTRA_TEXT, appUrl)
    this.startActivity(Intent.createChooser(shareIntent, getString(R.string.title_share_via)))
}