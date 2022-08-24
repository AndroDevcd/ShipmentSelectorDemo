package com.intuisoft.moderncalc.shipmentselector

import androidx.navigation.navOptions

object Constants {
    object JobStatus {
        val STATUS_UNASSIGNED = "Un-assigned"
        val STATUS_ASSIGNED = "Assigned"
    }

    object Navigation {

        val ANIMATED_ENTER_EXIT_RIGHT_NAV_OPTION =
            navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    popEnter = android.R.anim.slide_in_left
                }
            }
    }
}