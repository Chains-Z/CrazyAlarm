package com.crazy.crazyalarm.clockUtils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.crazy.crazyalarm.R
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.*


class ClockAdapter(val clockList: ArrayList<Clock>) :
    RecyclerView.Adapter<ClockAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clockTime: TextView = view.findViewById(R.id.time)
        val clockCaption: TextView = view.findViewById(R.id.caption)
        val switch : SwitchMaterial = view.findViewById(R.id.switch1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.clock_item,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val clock = clockList[position]
        holder.clockTime.text =
            String.format("%2d",clock.hour) + ':' + String.format("%2d",clock.minute)
        holder.clockCaption.text = clock.parseRepeat(clock.repeat,0)
        holder.switch.isChecked = true
        holder.switch.setOnCheckedChangeListener{button, isChecked->
            if(isChecked){
                //holder.clockCaption.text = clock.parseRepeat(clock.repeat,0)
            }
            else{
                //TODO:真正关闹钟
            }
        }
    }

    override fun getItemCount() = clockList.size
}
