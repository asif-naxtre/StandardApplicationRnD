package com.rnd.standardapplicationrnd.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    /**
     * @property getLayout()
     * Must be implemented
     * by child classes to
     *@return layout resource file id
     * */
    @LayoutRes
    abstract fun getLayout(): Int


    /**
     * @property initialSetUp()
     * This method must be overridden by child class where
     * there is need to call
     * a service
     * or network api
     * or load data from database
     * */
    open fun initialSetUp(){}

    /**
     * @property setUIData()
     * This method must be overridden by child class where there is
     * need to set some initial data on ui based on state of device,app
     * */
    open fun setUIData(){}


    /**
     * @property setUIListener()
     * This method must be implemented by child class to
     * setup event listener like(click, touch, longClick, etc.) for views
     * */
    abstract fun setUIListener()


    final override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.getLayout())
        this.initialSetUp()
        this.setUIData()
        this.setUIListener()
    }
}