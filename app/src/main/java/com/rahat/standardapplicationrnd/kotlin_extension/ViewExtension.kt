@file:Suppress("unused")
package com.rahat.standardapplicationrnd.kotlin_extension

import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun View.gone() {
    if (this is FloatingActionButton) {
        if (this.isOrWillBeShown) {
            this.hide()
        }
    } else {
        this.visibility = View.GONE
    }
}

fun View.invisible() {
    if (this is FloatingActionButton) {
        if (this.isOrWillBeShown) {
            this.hide()
        }
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun View.show() {
    if (this is FloatingActionButton) {
        if (this.isOrWillBeHidden) {
            this.show()
        }
    } else {
        this.visibility = View.VISIBLE
    }
}

fun View.isVisible()=this.visibility == View.VISIBLE