//text를 그냥 누르면 숫자가 올라가고, 꾹누르면 색이 반전되는 예제
//modifier에 pointerInput을 사용해 꾹눌렀을때의 동작 실행

package com.example.testttt1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.sp
import com.example.testttt1.ui.theme.Testttt1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Testttt1Theme {
                var count by remember { mutableStateOf(0) }
                var isLongPressed by remember { mutableStateOf(false) }
                val bgColor = if (isLongPressed) Color.Black else Color.Transparent
                val textColor = if (isLongPressed) Color.White else Color.Black

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(bgColor),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$count!!",
                        color = textColor,
                        fontSize = 50.sp,
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = { count++ },
                                    onLongPress = {
                                        isLongPressed = !isLongPressed
                                        count = 0
                                    }
                                )
                            }
                    )
                }
            }
        }
    }
}



