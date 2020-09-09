@file:Suppress("unused")

package com.rnd.standardapplicationrnd.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected val mContext: Context
        get() = this.requireContext()

    protected val mActivity: Activity
        get() = this.requireActivity()


    protected var isAttached = false
        private set

    protected lateinit var mView: View

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun initialSetUp()

    abstract fun setUIData()

    abstract fun setUIListener()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.isAttached = true
    }

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