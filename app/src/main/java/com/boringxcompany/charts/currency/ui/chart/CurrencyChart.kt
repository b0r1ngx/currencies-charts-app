package com.boringxcompany.charts.currency.ui.chart

import android.graphics.PointF
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.boringxcompany.charts.currency.data.domain.Price
import com.boringxcompany.charts.currency.ui.theme.Coral
import com.boringxcompany.charts.currency.ui.theme.Green
import com.boringxcompany.charts.currency.ui.theme.Pink
import com.boringxcompany.charts.currency.ui.theme.Purple

const val numberEntries = 48 // 48 blocks of 30 minutes
const val chartTimeFrameInSeconds = 30 * 60 // 30 minutes

sealed class DataPoint {
    data object NoMeasurement : DataPoint()
    data class Measurement(
        val averageMeasurementTime: Float,
        val minPrice: Float,
        val maxPrice: Float,
        val averagePrice: Float,
    ) : DataPoint()
}

@Composable
fun CurrencyChart(data: List<Price>, modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(width = 200.dp, height = 100.dp)) {
        Chart(data = data, modifier = Modifier.padding(16.dp))
    }
}

@Composable
private fun Chart(
    data: List<Price>,
    modifier: Modifier = Modifier,
    waveLineColors: List<Color> = listOf(Pink, Purple, Green),
    pathBackground: Color = Coral.copy(alpha = 0.2f),
) {
    if (waveLineColors.size < 2) {
        throw IllegalArgumentException("waveLineColors requires 2+ colors; $waveLineColors")
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawWithCache {
                drawCandlestick(data, size)
//                val paths = generateSmoothPath(data, size)
//                val lineBrush = Brush.verticalGradient(waveLineColors)
//                onDrawBehind {
//                    drawPath(path = paths.second, color = pathBackground, style = Fill)
//                    drawPath(path = paths.first, brush = lineBrush, style = Stroke(2.dp.toPx()))
//                }
            }
    )
}

// TODO: refactor this code to be compile
//private fun generateSmoothPath(data: List<Price>, size: Size): Pair<Path, Path> {
//    val path = Path()
//    val variancePath = Path()
//
//    val totalSeconds = 60 * 60 * 24 // total seconds in a day
//    val widthPerSecond = size.width / totalSeconds
//    val maxValue = data.maxBy { it.price }.price
//    val minValue = data.minBy { it.price }.price
//    val chartTop = ((maxValue + 5) / 10f) * 10
//    val chartBottom = (minValue / 10f) * 10
//    val range = chartTop - chartBottom
//    val heightPxPerAmount = size.height / range
//
//    var previousX = 0f
//    var previousY = size.height
//    var previousMaxX = 0f
//    var previousMaxY = size.height
//    val groupedMeasurements = (0..numberEntries).map { bracketStart ->
//        data.filter {
//            (bracketStart * chartTimeFrameInSeconds..(bracketStart + 1) * chartTimeFrameInSeconds)
//                .contains(it.date.toSecondOfDay())
//        }
//    }.map { prices ->
//        if (prices.isEmpty()) DataPoint.NoMeasurement
//        else DataPoint.Measurement(
//            averageMeasurementTime = prices.map { it.date.toSecondOfDay() }.average().toFloat(),
//            minPrice = prices.minBy { it.price }.price,
//            maxPrice = prices.maxBy { it.price }.price,
//            averagePrice = prices.map { it.price }.average().toFloat()
//        )
//    }
//
//    groupedMeasurements.forEachIndexed { i, dataPoint ->
//        if (i == 0 && dataPoint is DataPoint.Measurement) {
//            path.moveTo(
//                0f, size.height - (dataPoint.averagePrice - chartBottom) * heightPxPerAmount
//            )
//            variancePath.moveTo(
//                0f, size.height - (dataPoint.maxPrice - chartBottom) * heightPxPerAmount
//            )
//        }
//
//        if (dataPoint is DataPoint.Measurement) {
//            val x = dataPoint.averageMeasurementTime * widthPerSecond
//            val y = size.height - (dataPoint.averagePrice - chartBottom) * heightPxPerAmount
//
//            // to do smooth curve chart - we use cubicTo, uncomment section below for non-curve
//            val controlPoint1 = PointF((x + previousX) / 2f, previousY)
//            val controlPoint2 = PointF((x + previousX) / 2f, y)
//            path.cubicTo(
//                controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, x, y
//            )
//            previousX = x
//            previousY = y
//
//            val maxX = dataPoint.averageMeasurementTime * widthPerSecond
//            val maxY = size.height - (dataPoint.maxPrice - chartBottom) * heightPxPerAmount
//            val maxControlPoint1 = PointF((maxX + previousMaxX) / 2f, previousMaxY)
//            val maxControlPoint2 = PointF((maxX + previousMaxX) / 2f, maxY)
//            variancePath.cubicTo(
//                maxControlPoint1.x, maxControlPoint1.y, maxControlPoint2.x, maxControlPoint2.y,
//                maxX, maxY
//            )
//
//            previousMaxX = maxX
//            previousMaxY = maxY
//        }
//    }
//
//    var previousMinX = size.width
//    var previousMinY = size.height
//    groupedMeasurements.reversed().forEachIndexed { index, dataPoint ->
//        val i = 47 - index
//        if (i == 47 && dataPoint is DataPoint.Measurement) {
//            variancePath.moveTo(
//                size.width, size.height - (dataPoint.minPrice - chartBottom) * heightPxPerAmount
//            )
//        }
//
//        if (dataPoint is DataPoint.Measurement) {
//            val minX = dataPoint.averageMeasurementTime * widthPerSecond
//            val minY = size.height - (dataPoint.minPrice - chartBottom) * heightPxPerAmount
//            val minControlPoint1 = PointF((minX + previousMinX) / 2f, previousMinY)
//            val minControlPoint2 = PointF((minX + previousMinX) / 2f, minY)
//            variancePath.cubicTo(
//                minControlPoint1.x, minControlPoint1.y, minControlPoint2.x, minControlPoint2.y,
//                minX, minY
//            )
//
//            previousMinX = minX
//            previousMinY = minY
//        }
//    }
//    return path to variancePath
//}


private fun CacheDrawScope.drawCandlestick(data: List<Price>, size: Size): DrawResult {
    if (data.isEmpty()) return onDrawBehind { }

    val maxPrice = data.maxOf { it.high }
    val minPrice = data.minOf { it.low }
    val priceRange = maxPrice - minPrice
    val candleWidth = size.width / data.size

    return onDrawBehind {
        data.forEachIndexed { index, price ->
            val x = index * candleWidth + candleWidth / 2f
            val yHigh = size.height - ((price.high - minPrice) / priceRange * size.height)
            val yLow = size.height - ((price.low - minPrice) / priceRange * size.height)
            val yOpen = size.height - ((price.open - minPrice) / priceRange * size.height)
            val yClose = size.height - ((price.close - minPrice) / priceRange * size.height)

            val candleColor = if (price.close >= price.open) Color.Green else Color.Red
            drawLine(
                color = candleColor,
                start = Offset(x, yHigh),
                end = Offset(x, yLow),
                strokeWidth = 2f
            )

            val top = minOf(yOpen, yClose)
            val bottom = maxOf(yOpen, yClose)
            val bodyHeight = bottom - top
            drawRect(
                color = candleColor,
                topLeft = Offset(x - candleWidth / 4f, top),
                size = Size(candleWidth / 2f, bodyHeight)
            )
        }
    }
}
