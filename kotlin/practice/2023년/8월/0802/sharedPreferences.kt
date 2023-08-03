package com.example.buttonpractice0802

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.buttonpractice0802.ui.theme.ButtonPractice0802Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonPractice0802Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Main() {
    //대충 밑에 3줄은 count를 저장하고 실행시킬때 가져오는 것들
    val activity = LocalContext.current as? Activity
    val sharedPref = remember { activity?.getPreferences(Context.MODE_PRIVATE) }
    var count by remember {
        val countValue = sharedPref?.getInt("counter", 0) ?: 0
        mutableStateOf(countValue)
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            MyButtonCustom(
                onClick = {
                    count++
                    shareEdit(sharedPref, count)
                },
                buttonText = "+1버튼",
                buttonTextColor = Color.Yellow,
                containerColor = Color.Red,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 50.dp,
                    bottomStart = 50.dp,
                    bottomEnd = 0.dp
                )
            )
            MyButtonCustom(
                onClick = {
                    count--
                    shareEdit(sharedPref, count)
                },
                buttonText = "-1버튼",
                buttonTextColor = Color.Green,
                containerColor = Color.Blue,
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 50.dp
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TimeText()
            Button(
                onClick = {
                    count = 0
                    shareEdit(sharedPref, count)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.size(height = 100.dp, width = 150.dp)
            ) {
                Text(text = "초기화(숫자)", color = Color.White, fontSize = 15.sp)
            }
        }
        NumCountText(count)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            MyButtonCustom(
                onClick = {
                    count *= 2
                    shareEdit(sharedPref, count)
                },
                buttonText = "두배가돼",
                buttonTextColor = Color.Blue,
                containerColor = Color.Green,
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 50.dp
                )
            )
            MyButtonCustom(
                onClick = {
                    count /= 2
                    shareEdit(sharedPref, count)
                },
                buttonText = "반갈",
                buttonTextColor = Color.Red,
                containerColor = Color.Yellow,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 50.dp,
                    bottomStart = 50.dp,
                    bottomEnd = 0.dp
                )
            )
        }
    }
}

@Composable
fun MyButtonCustom(
    onClick: () -> Unit,
    buttonText: String,
    buttonTextColor: Color,
    containerColor: Color,
    shape: Shape,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = shape,
        modifier = Modifier.size(height = 100.dp, width = 150.dp)
    ) {
        Text(text = buttonText, color = buttonTextColor, fontSize = 25.sp)
    }
}

@Composable
fun NumCountText(count: Int) {
    Box(
        modifier = Modifier
            .background(brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)))
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$count",
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


fun shareEdit(sharedPref: SharedPreferences?, count: Int) {
    sharedPref?.edit {
        putInt("counter", count)
        //edit람다식은 apply가 자동처리됨
//        apply()
    }
}

@Composable
fun TimeText() {
    val activity = LocalContext.current as? Activity
    val sharedPref = remember { activity?.getPreferences(Context.MODE_PRIVATE) }
    var clickTime by remember { mutableStateOf(getSavedTime(sharedPref)) }

    Column(modifier = Modifier.padding(8.dp)) {
        Button(
            onClick = {
                // Save the current time when the button is clicked
                clickTime = System.currentTimeMillis()
                saveTime(sharedPref, clickTime)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier.size(height = 100.dp, width = 200.dp)
        ) {
            val formattedTime = formatTime(clickTime)
            Text(text = formattedTime, color = Color.White)
        }
    }
}

fun getSavedTime(sharedPref: SharedPreferences?): Long {
    return sharedPref?.getLong("lastClickedTime", 0L) ?: 0L
}

fun saveTime(sharedPref: SharedPreferences?, timeMillis: Long) {
    sharedPref?.edit {
        putLong("lastClickedTime", timeMillis)
    }
}

fun formatTime(timeMillis: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = Date(timeMillis)
    return dateFormat.format(date)
}
// date로 시간조정
//        Button(onClick = {
//            // Save the current time when the button is clicked
//            clickTime = Date().time
//            saveTime(sharedPref, clickTime)
//        }) {
//            // Display the last clicked time in the Button text
//            val currentTime = Date(clickTime)
//            Text(text = "$currentTime")
//        }
