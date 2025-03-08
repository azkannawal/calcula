package com.example.calcula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.calcula.ui.theme.CalculatorViewModel

val buttonList = listOf(
    "DEL","(",")","/",
    "7", "8", "9", "*",
    "4", "5", "6", "+",
    "1", "2", "3", "-",
    "AC", "0", ".", "="
)


@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel){

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.equationText.observeAsState()

    Box(modifier = modifier){
        Column(
           modifier=modifier.fillMaxSize().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = equationText.value?:"",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
                )
            
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = resultText.value?:"",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                items((buttonList)){
                    CalculatorButton(btn = it, onClick = {
                        viewModel.onButtonClick(it)
                    })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(btn : String, onClick : ()-> Unit){
    Box(modifier = Modifier.padding(5.dp), contentAlignment = Alignment.Center){
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            contentColor = getContentColor(btn),
            containerColor = getContainerColor(btn),
            elevation = FloatingActionButtonDefaults.elevation(2.dp)
        ) {
            Text(text = btn, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        }
    }
}

fun getContentColor(btn: String) : Color{
    if(btn == "DEL" || btn == "AC" || btn == "/" || btn == "*" || btn == "+" || btn == "-" || btn == "(" || btn == ")")
        return Color(0xFFFF5100)
    if(btn == "=")
        return Color.White
    return Color(0xFF000000)
}

fun getContainerColor(btn: String) : Color{
    if(btn == "=")
        return Color(0xFFFFF5100)
    return Color.White
}