@file:Suppress("unused","MemberVisibilityCanBePrivate")

package com.rnd.standardapplicationrnd.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.rnd.standardapplicationrnd.BuildConfig
import com.rnd.standardapplicationrnd.kotlin_extension.showToast

abstract class BaseFragment : Fragment() {

    /**
     * @property mContext
     * returns the non_null Context for the fragment,
     * it is a utility variable to get context from fragment
     * */
    protected val mContext: Context
        @NonNull
        get() = this.requireContext()

    /**
     * @property mActivity
     * returns the non_null Activity for the fragment,
     * it is a utility variable to get Activity from fragment
     * */
    protected val mActivity: Activity
        @NonNull
        get() = this.requireActivity()


    /**
     * @property isAttached
     * Holds boolean value to indicate if
     * the fragment is currently attached to activity
     * */
    protected var isAttached = false
        private set


    /**
     * @property mView
     * Holds View object inflated into the fragment
     * */
    protected lateinit var mView: View


    /**
     * @property getLayout()
     * Must be implemented
     * by child classes to
     * @return layout resource file id
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.isAttached = true
    }

    @Suppress("DEPRECATION")
    final override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.isAttached = true
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(this.getLayout(), container, false)
        return mView
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initialSetUp()
        this.setUIData()
        this.setUIListener()
    }

    override fun onDetach() {
        super.onDetach()
        this.isAttached = false
    }
}