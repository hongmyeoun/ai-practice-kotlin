package com.example.phonenumberbook

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonenumberbook.ui.theme.PhoneNumberBookTheme

class InformPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneNumberBookTheme {
                val context = LocalContext.current
                val username = intent.getStringExtra("UserName") ?: ""
                val userphone = intent.getStringExtra("UserPhone") ?: ""
                val useremail = intent.getStringExtra("UserEmail") ?: ""
                val userUid = intent.getIntExtra("UID",0)

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "profile",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )

                    Text(
                        text = "이름: $username",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "전화번호: $userphone",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = "이메일: $useremail",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    )

                    Button(onClick = {
                        val intent = Intent(context, EdittingPage::class.java)
                        intent.putExtra("name", username)
                        intent.putExtra("phone", userphone)
                        intent.putExtra("email", useremail)
                        intent.putExtra("uid", userUid)
                        context.startActivity(intent)
                    }) {
                        Text(text = "수정하기")
                    }
                }

            }
        }
    }
}