@file:Suppress("unused")

package com.rnd.standardapplicationrnd.kotlin_extension

import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * This file contains method that will provide method
 * for visibility manipulation of the view
 * */


/**
 * @property com.rnd.standardapplicationrnd.kotlin_extension.gone()
 * This method will hide the view can be called upon any view
 * it will set the visibility of the view as View.GONE
 * */
fun View.gone() {
    if (this is FloatingActionButton) {
        if (this.isOrWillBeShown) {
            this.hide()
        }
    } else {
        this.visibility = View.GONE
    }
}


/**
 * @property com.rnd.standardapplicationrnd.kotlin_extension.invisible()
 * This method will hide the view can be called upon any view
 * it will set the visibility of the view as View.INVISIBLE
 * */
fun View.invisible() {
    if (this is FloatingActionButton) {
        if (this.isOrWillBeShown) {
            this.hide()
        }
    } else {
        this.visibility = View.INVISIBLE
    }
}


/**
 * @property com.rnd.standardapplicationrnd.kotlin_extension.show()
 * This method will show the view can be called upon any view
 * it will set the visibility of the view as View.VISIBLE
 * */
fun View.show() {
    if (this is FloatingActionButton) {
        if (this.isOrWillBeHidden) {
            this.show()
        }
    } else {
        this.visibility = View.VISIBLE
    }
}

/**
 * @property com.rnd.standardapplicationrnd.kotlin_extension.isVisible()
 * @return Boolean values to indicate that
 * if the view is visible or not, upon which it is called
 * */
fun View.isVisible() = this.visibility == View.VISIBLE