package com.intuisoft.moderncalc.shipmentselector.androidwrappers

import androidx.annotation.LayoutRes

interface ListItem {
    @get:LayoutRes
    val layoutId: Int
    fun bind(holder: BindingViewHolder)
}