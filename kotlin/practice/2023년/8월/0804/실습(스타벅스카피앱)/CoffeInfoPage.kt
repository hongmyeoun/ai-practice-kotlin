package com.example.starbuckscopyapp0804

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.starbuckscopyapp0804.ui.theme.StarbucksCopyApp0804Theme

class CoffeInfoPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarbucksCopyApp0804Theme {
                val coffeeName = intent.getStringExtra("CoffeeName") ?: ""
                val isHot = intent.getStringExtra("isHot") ?: ""
                val price = intent.getStringExtra("Price") ?: ""
                //Int는 default값을 넣어야됨
                val imageResID = intent.getIntExtra("ImageID", 0)
                Tiriri(coffeeName, isHot, price, imageResID)

            }
        }
    }
}

@Composable
fun NextPageTop() {
    val context = LocalContext.current
    Box() {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("<", color = Color.White)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("📤")
                }
            }
            Divider()
        }
    }
}


@Composable
fun Tiriri(coffee: String, hot: String, price: String, imageResId: Int) {
    val context = LocalContext.current
    val painter: Painter = painterResource(id = imageResId)
    val scrollState = rememberScrollState()

    StarbucksCopyApp0804Theme {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Box {
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )
                NextPageTop()
            }
            Column {
                Box(modifier = Modifier.padding(50.dp)) {
                    Column {

                        Text(text = coffee)
                        Text(text = hot)
                        Text(text = "설명설명")
                        Text(text = price)

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                shape = RoundedCornerShape(
                                    topStart = 30.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 30.dp,
                                    bottomEnd = 0.dp
                                )
                            ) {
                                Text("Hot")
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                                shape = RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 30.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 30.dp
                                )
                            ) {
                                Text("Iced")
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(20.dp)
                                .background(Color.LightGray)
                                .size(width = 300.dp, height = 100.dp)
                        ) {
                            Text("주문방법 어쩌구설명설명")
                        }

                        Button(
                            onClick = {
                                val intent = Intent(context, OrderDonePage::class.java)
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009900)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "주문하기")
                        }

                    }
                }
            }
        }
    }
}
