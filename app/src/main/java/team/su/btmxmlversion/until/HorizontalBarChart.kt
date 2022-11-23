package team.su.btmxmlversion.until

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class HorizontalBarChart(
    private val scoreOfAreaType: List<Float>,
    private val horizontalBarChart: HorizontalBarChart
) {
    private lateinit var barList: ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData

    fun setBarChart() {
        val labelList = listOf("분석", "계산", "직감", "기억", "지각")

        barList = ArrayList()
        barList.add(BarEntry(0f, scoreOfAreaType[4]))
        barList.add(BarEntry(1f, scoreOfAreaType[3]))
        barList.add(BarEntry(2f, scoreOfAreaType[2]))
        barList.add(BarEntry(3f, scoreOfAreaType[1]))
        barList.add(BarEntry(4f, scoreOfAreaType[0]))

        barDataSet = BarDataSet(barList, null)
        barDataSet.apply {
            color = Color.GREEN
            valueTextColor = Color.BLACK
            valueTextSize = 15f
        }

        barData = BarData(barDataSet)
        barData.barWidth = 0.5f

        val xAxis = horizontalBarChart.xAxis
        xAxis.setDrawAxisLine(false)
        xAxis.granularity = 1f
        xAxis.textSize = 15f
        xAxis.gridLineWidth = 25f
        xAxis.gridColor = Color.parseColor("#80E5E5E5")
        xAxis.position = XAxis.XAxisPosition.BOTTOM // X 축 데이터 표시 위치
        xAxis.valueFormatter = IndexAxisValueFormatter(labelList)

        val axisLeft = horizontalBarChart.axisLeft
        axisLeft.setDrawGridLines(false)
        axisLeft.setDrawAxisLine(false)
        axisLeft.axisMinimum = 0f // 최솟값
        axisLeft.axisMaximum = 100f // 최댓값
        axisLeft.granularity = 1f // 최대값만큼 라인선 설정
        axisLeft.textSize = 14f

        val axisRight = horizontalBarChart.axisRight
        axisRight.setDrawLabels(false) // label 삭제
        axisRight.setDrawGridLines(false)
        axisRight.setDrawAxisLine(false)

        horizontalBarChart.data = barData
        horizontalBarChart.description.isEnabled = false
        horizontalBarChart.legend.isEnabled = false
        horizontalBarChart.setTouchEnabled(false)
        horizontalBarChart.invalidate()
    }

    fun setHealthState(
        veryGoodImage: ImageView,
        goodImage: ImageView,
        normalImage: ImageView,
        badImage: ImageView,
        veryBadImage: ImageView,
    ) {
        val avg = this.scoreOfAreaType.let {
            var total = 0f
            for (i in it.indices) {
                total += it[i]
            }
            total / 5
        }

        when (avg) {
            in 0f..20f -> {
                veryBadImage.visibility = View.VISIBLE
            }
            in 20.1f..40f -> {
                badImage.visibility = View.VISIBLE
            }
            in 40.1f..60f -> {
                normalImage.visibility = View.VISIBLE
            }
            in 60.1f..80f -> {
                goodImage.visibility = View.VISIBLE
            }
            in 80.1f..100f -> {
                veryGoodImage.visibility = View.VISIBLE
            }
        }
    }

}