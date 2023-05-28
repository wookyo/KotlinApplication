package com.app.v1.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {

    /**
     * Remove All Fragment
     *
     * @param fragmentManager
     */
    fun removeAllFragment(fragmentManager: androidx.fragment.app.FragmentManager) {
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
    }

    /**
     * Replace Fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    @JvmStatic
    fun replaceFragment(fragmentManager: androidx.fragment.app.FragmentManager,
                        fragment: androidx.fragment.app.Fragment, frameId: Int, tag: String) {
        fragmentManager.beginTransaction().apply {
            replace(frameId, fragment, tag)
            disallowAddToBackStack()
            commit()
        }
    }

    /**
     * Add Fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     * @param tag
     * @param addBackStack
     */
    fun addFragment(fragmentManager: FragmentManager,
                    fragment: Fragment,
                    frameId: Int,
                    tag: String,
                    addBackStack: Boolean) {
        fragmentManager.beginTransaction().apply {
            add(frameId, fragment, tag)
            if (addBackStack) {
                addToBackStack(tag)
            } else {
                disallowAddToBackStack()
            }
            commit()
        }
    }

    /**
     * Remove Fragment
     *
     * @param fragmentManager
     * @param tag
     */
    fun removeFragment(fragmentManager: androidx.fragment.app.FragmentManager, tag: String) {
        fragmentManager.findFragmentByTag(tag)?.let { fragment ->
            fragmentManager.beginTransaction().apply {
                remove(fragment)
                commit()
            }
        }
    }

    fun isFragmentShowing(fragmentManager: FragmentManager, tag: String): Boolean {
        return fragmentManager.findFragmentByTag(tag)?.isVisible?:false
    }
}