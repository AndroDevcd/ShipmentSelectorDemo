package com.intuisoft.moderncalc.shipmentselector.ui.managementPortal

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.intuisoft.moderncalc.shipmentselector.R
import com.intuisoft.moderncalc.shipmentselector.androidwrappers.BindingViewHolder
import com.intuisoft.moderncalc.shipmentselector.databinding.BasicDriverInfoItemBinding
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.ui.AppDateTimeFormatter

class BasicDriverInfoListAdapter(
    private val onDriverSelected: (Driver) -> Unit,
    private val timeFormatter: AppDateTimeFormatter
) : RecyclerView.Adapter<BindingViewHolder>() {

    var drivers = arrayListOf<BasicDriverInfoDetail>()
    private var lastPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder {
        return when (viewType) {
            R.layout.basic_driver_info_item -> {
                BindingViewHolder.create(parent, BasicDriverInfoItemBinding::inflate)
            }
            else -> throw IllegalArgumentException("Invalid BindingViewHolder Type")
        }
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        drivers[position].bind(holder)
        setAnimation(holder.itemView, position)
        lastPosition = position
    }

    override fun getItemCount(): Int = drivers.size

    override fun getItemViewType(position: Int): Int {
        return drivers[position].layoutId
    }

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(view.context, R.anim.fall_down)
            view.startAnimation(animation)
        } else {
            view.clearAnimation()
        }
    }

    fun addDrivers(availDrivers: ArrayList<Driver>) {
        drivers.clear()
        drivers.addAll(availDrivers.mapIndexed { index, driver ->
            BasicDriverInfoDetail(driver, onDriverSelected, timeFormatter)
        })

        notifyDataSetChanged()
    }
}
