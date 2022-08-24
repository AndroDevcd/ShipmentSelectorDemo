package com.intuisoft.moderncalc.shipmentselector.androidwrappers

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<T: ViewBinding> : Fragment() {

    protected var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}