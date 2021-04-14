package com.display.fitness.fitness

import android.graphics.Color
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.display.chartwidgetdemo.entity.BarChartEntity
import com.display.chartwidgetdemo.entity.ChartEntity
import com.display.chartwidgetdemo.entity.PieDataEntity
import com.display.chartwidgetdemo.widget.*
import com.display.fitness.R
import com.display.fitness.http.HttpCallback
import com.display.fitness.http.HttpClientCenter
import com.display.fitness.model.EachSportTime
import com.display.fitness.model.SportInfoBean
import com.display.fitness.toolbar.CustomToolBar
import java.io.Serializable

class FitnessChartDataActivity : AppCompatActivity() {
private val mColors = intArrayOf(-0x232395, -0xFEC561, -0x1bd9ca, -0x800000, -0x249C02, -0x7397, -0x7f7f80,-0x194800, -0x830400)

    private val handler = Handler(Looper.getMainLooper())
    private var pieChart: PieChart? = null
    private var barChartNew3: BarAndChartTest? = null
    private var lineChart: LineChartNew ?= null
    private var mHandlerThread: HandlerThread? = null
    private var workHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_chart_data)
        initView()
        initData()
    }

    fun initView() {
        val mToolbar = findViewById<CustomToolBar>(R.id.toolbar)
        mToolbar.setNavigationOnClickListener{ finish() }
        mToolbar.setMyCenterTitle("健身数据",true)
        pieChart = findViewById(R.id.pie_chart)
        barChartNew3 = findViewById(R.id.chart_new_3)
        lineChart = findViewById(R.id.chart_line)
    }


    fun initData() {
        mHandlerThread = HandlerThread("getSportInfo")
        mHandlerThread!!.start()
        getFitnessData()
        workHandler = object : Handler(mHandlerThread!!.looper) {
            // 消息处理的操作
            override fun handleMessage(msg: Message) {
                //设置了两种消息处理操作,通过msg来进行识别
                when (msg.what) {
                    1 -> {
                        val bundle = msg.data
                        val sportAndCountInfo = bundle?.getSerializable("SportAndCountInfo") as SportAndCountInfo
                        handler.post(Runnable {
                            barChartNew3!!.setData(sportAndCountInfo.barList, intArrayOf(Color.parseColor("#6FC5F4")), "分组", "时长", sportAndCountInfo.rightDatas2, BarAndChartTest.TEXT_TYPE_SLANTING)
                        })
                    }

                    2 -> {
                        val bundle = msg.data
                        val listPieInfo = bundle?.getSerializable("ListPieInfo") as ListPieInfo
                        handler.post(Runnable {
                            pieChart?.setHollow(true)
                            pieChart?.setShowMiddleText(true)
                            pieChart?.setDataList(listPieInfo.barList)
                            pieChart?.startAnimation(1000)
                        })
                    }
                    else -> {
                    }
                }
            }
        }
    }


    fun getFitnessData() {
        val rightDatas2: MutableList<MutableList<Int>> = ArrayList()
        HttpClientCenter.getFitnessData("1", "day", object : HttpCallback<SportInfoBean>() {
            override fun onSuccess(t: SportInfoBean?) {
                val listBarChart: MutableList<BarChartEntity> = ArrayList()
                val listCount: MutableList<Int> = ArrayList()
                val listRankCount:MutableList<ChartEntity> = ArrayList()
                for (value in t?.data!!) {
                    listBarChart.add(BarChartEntity(value.strDateTime, value.sportTime))
                    listCount.add(value.rankCount)
                    listRankCount.add(ChartEntity(value.strDateTime, value.rankCount.toFloat()))
                }
                lineChart?.setData(listRankCount,true)
                lineChart?.startAnimation(6000)
                rightDatas2.add(listCount)

                val message = Message.obtain()
                message.what = 1
                val b = Bundle()
                b.putSerializable("SportAndCountInfo", SportAndCountInfo(listBarChart, rightDatas2))
                message.data = b
                workHandler?.sendMessage(message)
            }

            override fun onFail(e: Exception?) {
                Toast.makeText(this@FitnessChartDataActivity, e.toString(), Toast.LENGTH_LONG)
            }
        })

        var listPie: MutableList<PieDataEntity> = ArrayList()
        HttpClientCenter.getEachSportTime("1", object : HttpCallback<EachSportTime>() {
            override fun onSuccess(t: EachSportTime?) {
                for ((i, value) in t?.data!!.withIndex()) {
                    listPie.add(PieDataEntity(value.sport, value.sportTime, mColors[i % 9]))
                }

                val message = Message.obtain()
                message.what = 2
                val b = Bundle()
                b.putSerializable("ListPieInfo", ListPieInfo(listPie))
                message.data = b
                workHandler?.sendMessage(message)
            }

            override fun onFail(e: java.lang.Exception?) {
                Toast.makeText(this@FitnessChartDataActivity, e.toString(), Toast.LENGTH_LONG)
            }

        })
    }


    override fun onDestroy() {
        super.onDestroy()
//        mHandlerThread?.destroy()
    }
}

open class SportAndCountInfo(val barList: List<BarChartEntity>, val rightDatas2: List<List<Int>>) : Serializable
open class ListPieInfo(val barList: List<PieDataEntity>?) : Serializable