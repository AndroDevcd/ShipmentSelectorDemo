package com.intuisoft.moderncalc.shipmentselector.ui.managementPortal

import android.view.View
import androidx.core.view.ViewCompat
import com.intuisoft.moderncalc.shipmentselector.R
import com.intuisoft.moderncalc.shipmentselector.androidwrappers.BindingViewHolder
import com.intuisoft.moderncalc.shipmentselector.androidwrappers.ListItem
import com.intuisoft.moderncalc.shipmentselector.db.entities.Driver
import com.intuisoft.moderncalc.shipmentselector.ui.AppDateTimeFormatter
import kotlinx.android.synthetic.main.basic_driver_info_item.view.*

class BasicDriverInfoDetail(
    val driver: Driver,
    val onClick: (Driver) -> Unit,
    val timeFormatter: AppDateTimeFormatter
) : ListItem {
    override val layoutId: Int
        get() = R.layout.basic_driver_info_item

    override fun bind(holder: BindingViewHolder) {
        holder.itemView.apply {
            driverName.text = "${driver.firstName} ${driver.lastName}"

            if(driver.currentJob == -1) {
                driverStatus.text = "Un-assigned"
                driverStatus.setTextColor(this.context.getColor(R.color.driver_unassigned_color))
            } else {
                driverStatus.text = "Assigned"
                driverStatus.setTextColor(this.context.getColor(R.color.driver_assigned_color))
            }

            lastUpdated.lastUpdated.text = "Last updated: ${timeFormatter.formatTimeFromDate(driver.updateTime.toInstant())}"
            driverContainer.setOnClickListener {
                onClick(driver)
            }
        }
    }
}