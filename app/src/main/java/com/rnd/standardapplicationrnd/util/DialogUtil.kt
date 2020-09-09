@file:Suppress("unused")

package com.rnd.standardapplicationrnd.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.rnd.standardapplicationrnd.kotlin_extension.layoutInflater

object DialogUtil {

    class DialogProperties {
        @LayoutRes
        var viewId: Int? = null
        @IdRes
        var cancelButtonId: Int? = null
        @IdRes
        var positiveButtonId: Int? = null
        var dialogListener: DialogListener? = null
        var cancelable: Boolean = true

        val valid: Boolean
            get() = viewId != null &&
                    cancelButtonId != null &&
                    dialogListener != null
    }

    interface DialogListener {
        fun okClick(dialogView: View)
    }

    fun Context.showDialog(
        dialogProperties: DialogProperties
    ) {
        require(dialogProperties.valid) { "Minimum properties are viewId, cancel button Id and listener" }
        val view = this.layoutInflater.inflate(dialogProperties.viewId!!, null, false)
        val dialog = AlertDialog.Builder(this).create()
            .apply {
                this.setCancelable(dialogProperties.cancelable)
                this.setView(view)
            }
        view.findViewById<View>(dialogProperties.cancelButtonId!!)?.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }

        dialogProperties.positiveButtonId?.let { positiveButtonId ->
            view.findViewById<View>(positiveButtonId)?.setOnClickListener {
                dialogProperties.dialogListener!!.okClick(view)
            }
        }
        dialog.show()
    }

}