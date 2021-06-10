package com.crazy.crazyalarm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.crazy.crazyalarm.clockUtils.AlarmManagerUtil
import com.crazy.crazyalarm.clockUtils.Clock
import com.crazy.crazyalarm.clockUtils.ClockAdapter
import com.crazy.crazyalarm.clockUtils.Configuration
import com.crazy.crazyalarm.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadConfiguration()
        binding.fab.setOnClickListener {
            val intent = Intent(this, SetClockActivity::class.java)
            startActivity(intent)
        }
        setClockList()
    }

    override fun onRestart() {
        super.onRestart()
        setClockList()
    }
    private fun setClockList(){
        val idList =  AlarmManagerUtil.getAllParentID(this)
        val clockList = ArrayList<Clock>()
        for (id in idList){
            clockList.add(Clock(this,id))
        }
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = ClockAdapter(clockList)
        binding.recyclerView.adapter = adapter
    }
    private fun loadConfiguration() {
        val SETTING = "setting"
        val MATH_CODE = "mathCode"
        val SCAN_STRING = "scanString"
        val prefs :SharedPreferences = getSharedPreferences(SETTING, Context.MODE_PRIVATE)
        val code = prefs.getInt(MATH_CODE, 0)
        Configuration.MathConf.modeCode = code
        Configuration.ScanConf.scanString = prefs.getString(SCAN_STRING, "") ?: ""
    }
}
