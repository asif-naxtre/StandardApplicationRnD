package com.rahat.standardapplicationrnd.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes abstract fun getLayout():Int

    abstract fun initialSetUp()

    abstract fun setUIData()

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