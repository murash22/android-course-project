package com.example.helloworld.features.doctor.statistics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.helloworld.R
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.PatientStatus
import kotlin.math.round

@Composable
fun StatisticScreen(
    navController: NavController,
    patients: List<PatientDTO>,
    modifier: Modifier = Modifier
) {
    val colors = mapOf<PatientStatus, Color>(
        PatientStatus.Cured to Color.Green,
        PatientStatus.Relapse to Color.Red,
        PatientStatus.Remission to Color.Blue
    )

    val allStatuses = mapOf<PatientStatus, Int>(
        PatientStatus.Cured to (patients.filter { it.status == PatientStatus.Cured }).size,
        PatientStatus.Relapse to (patients.filter { it.status == PatientStatus.Relapse }).size,
        PatientStatus.Remission to (patients.filter { it.status == PatientStatus.Remission }).size
    )

    val dataList: MutableList<PieChartData.Slice> = mutableListOf()
    for (name in colors) {
        dataList.add(
            PieChartData.Slice(
                name.key.name,
                findStatusProportion(name.key, allStatuses),
                name.value
            )
        )
    }

    val pieChartData = PieChartData(
        slices = dataList,
        plotType = PlotType.Pie
    )

    val pieChartConfig = PieChartConfig(
        isAnimationEnable = true,
        showSliceLabels = false,
        animationDuration = 1500
    )

    Column(
        modifier = modifier.padding(bottom = 25.dp),
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .padding(start = 15.dp, top = 10.dp)
                .alpha(0.6f)
                .clickable { navController.popBackStack() },
            imageVector = Icons.Filled.Close,
            contentDescription = null
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 9.dp),

            ) {
            items(3) { index ->
                if (index == 0) {
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        fontSize = 20.sp,
                        text = stringResource(
                            R.string.statistics
                        )
                    )
                }
                if (index == 1) {
                    PieChart(
                        modifier = Modifier
                            .width(400.dp)
                            .height(400.dp),
                        pieChartData,
                        pieChartConfig
                    )
                }
                if (index == 2) {
                    Column(
                        modifier = modifier.padding(vertical = 10.dp)
                    ) {
                        for (name in colors) {
                            Row(
                                modifier = modifier.padding(start = 15.dp, top = 15.dp)
                            ) {
                                Box(
                                    modifier = modifier
                                        .background(name.value)
                                        .height(20.dp)
                                        .width(40.dp)

                                )
                                Text(
                                    text = name.key.name + " (${round(findStatusProportion(name.key, allStatuses).toDouble()*100)}%)",
                                    modifier.padding(start = 15.dp, end = 40.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun findStatusProportion(status: PatientStatus, statusesMap: Map<PatientStatus, Int>): Float {
    var sum = 0
    for (stat in statusesMap) {
        sum += stat.value
    }
    val number = statusesMap[status]!!.toFloat()
    return number / sum.toFloat()
}