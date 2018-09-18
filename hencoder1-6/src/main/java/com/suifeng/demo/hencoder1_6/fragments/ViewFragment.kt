package com.suifeng.demo.hencoder1_6.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suifeng.demo.hencoder1_6.R


/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class ViewFragment: Fragment() {

    companion object {

        const val VIEW_LAYOUT_ID = "VIEW_LAYOUT_ID"

        fun getViewFragment(viewType: Int): ViewFragment {
            val viewFragment = ViewFragment()
            val bundle = Bundle()
            bundle.putInt(VIEW_LAYOUT_ID, viewType)
            viewFragment.arguments = bundle
            return viewFragment
        }
    }

    private var layoutId: Int = R.layout.frag_1

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(arguments != null) {
            layoutId = arguments!!.getInt(VIEW_LAYOUT_ID, R.layout.frag_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}