package com.crazy.crazyalarm.clockUtils

import android.content.Context

class Clock(context: Context, id: Int) {
    val mode: Int
    val noticeFlag: Int
    val repeat: Int
    val hour: Int
    val minute: Int
    init {
        val intent = AlarmManagerUtil.getIntent(context, id)

        noticeFlag = intent.getIntExtra(AlarmManagerUtil.NOTICEFLAG, 0)
        repeat = intent.getIntExtra(AlarmManagerUtil.REPEAT, 0)
        mode = intent.getIntExtra(AlarmManagerUtil.MODE, 0)
        hour = intent.getIntExtra(AlarmManagerUtil.HOUR, 0)
        minute = intent.getIntExtra(AlarmManagerUtil.MINUTE, 0)
    }
    fun parseRepeat(r: Int, flag: Int): String {
        var repeat = r
        var cycle = ""
        var weeks = ""
        if (repeat == 0) {
            repeat = 127
        }
        if (repeat % 2 == 1) {
            cycle = "周一"
            weeks = "1"
        }
        if (repeat % 4 >= 2) {
            if ("" == cycle) {
                cycle = "周二"
                weeks = "2"
            } else {
                cycle = "$cycle,周二"
                weeks = "$weeks,2"
            }
        }
        if (repeat % 8 >= 4) {
            if ("" == cycle) {
                cycle = "周三"
                weeks = "3"
            } else {
                cycle = "$cycle,周三"
                weeks = "$weeks,3"
            }
        }
        if (repeat % 16 >= 8) {
            if ("" == cycle) {
                cycle = "周四"
                weeks = "4"
            } else {
                cycle = "$cycle,周四"
                weeks = "$weeks,4"
            }
        }
        if (repeat % 32 >= 16) {
            if ("" == cycle) {
                cycle = "周五"
                weeks = "5"
            } else {
                cycle = "$cycle,周五"
                weeks = "$weeks,5"
            }
        }
        if (repeat % 64 >= 32) {
            if ("" == cycle) {
                cycle = "周六"
                weeks = "6"
            } else {
                cycle = "$cycle,周六"
                weeks = "$weeks,6"
            }
        }
        if (repeat / 64 == 1) {
            if ("" == cycle) {
                cycle = "周日"
            } else {
                cycle = "$cycle,周日"
                weeks = "$weeks,7"
            }
        }
        if (cycle == "周一,周二,周三,周四,周五,周六,周日"){
            cycle = "每天"
        }
        return if (flag == 0) cycle else weeks
    }
}