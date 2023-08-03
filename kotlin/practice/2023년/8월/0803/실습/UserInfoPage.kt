package com.example.testttt

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testttt.ui.theme.TesttttTheme

class UserInfoPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesttttTheme {
                UserInfoPageContent()
            }
        }
    }
}

@Composable
fun UserInfoPageContent() {
    val c = LocalContext.current
    val intent = c as? ComponentActivity
    val name = intent?.intent?.getStringExtra("name") ?: ""
    val phone = intent?.intent?.getStringExtra("phone") ?: ""
    val email = intent?.intent?.getStringExtra("email") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Name: $name", fontSize = 18.sp)
        Text(text = "Phone: $phone", fontSize = 18.sp)
        Text(text = "Email: $email", fontSize = 18.sp)
        
        Button(onClick = {
            val i = Intent(c, MainActivity::class.java)
            c.startActivity(i)

        }) {
            Text(text = "이전")
        }
    }
}
