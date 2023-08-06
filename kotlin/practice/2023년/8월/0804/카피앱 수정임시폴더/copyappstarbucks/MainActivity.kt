package com.example.copyappstarbucks

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copyappstarbucks.ui.theme.CopyAppStarbucksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CopyAppStarbucksTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Top()
        Menu(R.drawable.ice_amer, "아이스 카페 아메리카노", "Iced Caffe Americano", 4500)
        Menu(R.drawable.cafe_latte, "아이스 카페 라떼", "Iced Caffe Latte", 5000)
        Menu(R.drawable.sea_salt, "씨솔트 카라멜 콜드 브루", "Sea Salt Caramel Cold Brew", 6300)
        Menu(R.drawable.ja_mong, "아이스 자몽 허니 블랙 티", "Iced Grapefruit Honey Black Tea", 5700)
        Menu(R.drawable.cold_brew, "콜드 브루", "Cold Brew", 6900)
        Menu(R.drawable.cafe_moca, "카페 모카", "Caffe Mocha", 6500)
        Menu(R.drawable.raben_der, "라벤더 카페 브레베", "Lavender Cafe Breve", 7700)
        Menu(R.drawable.the_green, "더 그린 쑥 크림 라떼", "The Green Mugwart Cream Latte", 8000)
    }
}


@Composable
fun Top() {
    Box {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("<", color = Color.Black, fontSize = 25.sp)
                }
                Text("추천", fontSize = 25.sp)
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("🔍", fontSize = 25.sp)
                }
            }
            Divider()
        }
    }
}

@Composable
fun Menu(imageResId: Int, coffeeName: String, engName: String, price: Int) {
    val context = LocalContext.current
    val painter: Painter = painterResource(id = imageResId)

    Row() {
        Button(
            onClick = {
                val intent = Intent(context, CoffeInfoPage::class.java)
                intent.putExtra("coffeeName", coffeeName)
                intent.putExtra("EngName", engName)
                intent.putExtra("Price", price)
                intent.putExtra("ImageID", imageResId)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier.clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(text = coffeeName, color = Color.Black)
                    Text(text = engName, color = Color.LightGray, fontSize = 12.sp)
                    Text(text = "${price}원", color = Color.Black)
                }
            }
        }
    }
}