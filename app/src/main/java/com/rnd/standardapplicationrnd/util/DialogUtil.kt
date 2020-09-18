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
        /**
         * @property viewId must be set for showing dialog
         * */
        @LayoutRes
        var viewId: Int? = null

        /**
         * @property cancelButtonId must be set for showing dialog,
         * holds id for the cancel button view
         * */
        @IdRes
        var cancelButtonId: Int? = null

        /**
         * @property positiveButtonId should be set if dialog contains two buttons
         * holds id for the ok button view
         * */
        @IdRes
        var positiveButtonId: Int? = null

        /**
         * @property dialogListener should be set if dialog contains ok button
         * contains callback to the caller and it would invoke the method
         * when ok button pressed with param
         * @property viewId view, form which all
         * the required values can be retrieved
         * */
        var dialogListener: DialogListener? = null


        /**
         * @property cancelable will indicate that
         * if the dialog can be cancelled
         * upon back click or click on outside
         * */
        var cancelable: Boolean = true

        /**
         * @property valid will
         * @return boolean value to indicate that
         * if the provided dialog property object is valid
         * and can be used to show the dialog
         * */
        val valid: Boolean
            get() = viewId != null &&
                    cancelButtonId != null &&
                    dialogListener != null
    }

    /**
     * @property DialogListener
     * must be implemented by the caller of
     * @property showDialog() to show dialog
     * that contains a positive button upon
     * click of which information for UI
     * must be retrieved form dialog UI
     * */
    interface DialogListener {
        fun okClick(dialogView: View)
    }

    /**
     * Call this method with
     * @param dialogProperties to show a dialog
     * it is generic method to show any type of dialog
     * which contains one or two button
     * */
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