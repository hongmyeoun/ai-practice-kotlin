package com.example.numberbookpractice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberbookpractice.data.NumberBookAppDatabase
import com.example.numberbookpractice.data.User
import com.example.numberbookpractice.ui.theme.NumberBookPracticeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberBookPracticeTheme {
                val scrollState = rememberScrollState()
                val context = LocalContext.current
                val db = remember { NumberBookAppDatabase.getDatabase(context) }
                val userList by db.userDao().getAll().collectAsState(initial = emptyList())
                val scope = rememberCoroutineScope()

                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "   ", fontSize = 30.sp)
                        Text(text = "연락처", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        //버튼을 누르면 연락처 추가 페이지로 넘어감
                        Button(
                            onClick = {
                                val intent = Intent(context, AddNumberPage::class.java)
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            ) {
                            Text("➕")
                        }
                    }
                    Column(
                        modifier = Modifier.verticalScroll(scrollState)
                    ) {
                        for (user in userList) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(7.dp)
                                    .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                                    .padding(16.dp)
                                    .clickable {
                                        val intent = Intent(context, EdittingPage::class.java)
                                        intent.putExtra("UserName", user.name)
                                        intent.putExtra("UserPhone", user.phone)
                                        intent.putExtra("UserEmail", user.email)
                                        context.startActivity(intent)
                                    }
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    UserItem(user, db, scope)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserItem(user: User, db: NumberBookAppDatabase, scope: CoroutineScope) {

    Image(painter = painterResource(id = R.drawable.profile), contentDescription = "profile")

    Text(text = "     ")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Name: ${user.name} ", fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = {
                scope.launch(Dispatchers.IO) { db.userDao().delete(user) }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        ) {
            Text(text = "-", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)
        }
    }
}