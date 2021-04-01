package com.display.chartwidgetdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.display.chartwidgetdemo.entity.BarChartEntity;
import com.display.chartwidgetdemo.entity.PieDataEntity;
import com.display.chartwidgetdemo.entity.SportData;
import com.display.chartwidgetdemo.widget.BarAndChartTest;
import com.display.chartwidgetdemo.widget.HollowPieChart;

import java.util.ArrayList;
import java.util.List;

import static com.display.chartwidgetdemo.widget.BarAndChartTest.TEXT_TYPE_SLANTING;


public class TestActivity extends AppCompatActivity {
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        HollowPieChart pieChart = findViewById(R.id.chart);
        BarAndChartTest barChartNew3 = findViewById(R.id.chart_new_3);


        List<PieDataEntity> dataEntities = new ArrayList<>();
        dataEntities.add(new PieDataEntity("跑步", 20, mColors[0 % 9]));
        dataEntities.add(new PieDataEntity("举重", 40, mColors[1 % 9]));
        dataEntities.add(new PieDataEntity("跑步", 20, mColors[2 % 9]));

        pieChart.setDataList(dataEntities);

        pieChart.setOnItemPieClickListener(new HollowPieChart.OnItemPieClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(TestActivity.this, dataEntities.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        List<SportData> shortList = new ArrayList<>();
        shortList.add(new SportData("03-04", 5, 20.0f));
        shortList.add(new SportData("03-05", 3, 10.0f));
        shortList.add(new SportData("03-06", 1, 30.0f));
        shortList.add(new SportData("03-07", 2, 40.0f));


        List<Integer> tem = new ArrayList<>();

        List<List<Integer>> rightDatas2 = new ArrayList<>();

        List<BarChartEntity> datas2 = new ArrayList<>();

        tem.add(3);
        tem.add(4);
        tem.add(9);
        tem.add(8);
        tem.add(3);
        tem.add(4);
        tem.add(9);

        datas2.add(new BarChartEntity("01-01", 20f));
        datas2.add(new BarChartEntity("01-02", 10f));
        datas2.add(new BarChartEntity("01-03", 30f));
        datas2.add(new BarChartEntity("01-04", 40f));


        rightDatas2.add(tem);
        barChartNew3.setOnItemBarClickListener(new BarAndChartTest.OnItemBarClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(TestActivity.this, "排名："+tem.get(position).toString() +"  时长："+datas2.get(position).getyValue(), Toast.LENGTH_SHORT).show();
            }
        });
        //Color.parseColor("#78DA9F"), Color.parseColor("#FCAE84")
        barChartNew3.setData(datas2,
                new int[]{Color.parseColor("#6FC5F4")}
                , "分组", "时长", rightDatas2, TEXT_TYPE_SLANTING);
        barChartNew3.startAnimation();
    }
}