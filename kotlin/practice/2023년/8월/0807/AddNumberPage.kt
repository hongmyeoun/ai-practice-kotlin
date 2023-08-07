package com.example.numberbookpractice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.numberbookpractice.data.NumberBookAppDatabase
import com.example.numberbookpractice.data.User
import com.example.numberbookpractice.ui.theme.NumberBookPracticeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNumberPage : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberBookPracticeTheme {
                val context = LocalContext.current
                val db = remember { NumberBookAppDatabase.getDatabase(context) }
                val scope = rememberCoroutineScope()

                var name by remember { mutableStateOf("") }
                var phone by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }

                Column {
                    OutlinedTextField(value = name, onValueChange = { name = it })
                    OutlinedTextField(value = phone, onValueChange = { phone = it })
                    OutlinedTextField(value = email, onValueChange = { email = it })

                    Row() {
                        Button(onClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }) {
                            Text(text = "취소")
                        }
                        Button(onClick = {
                            val newUser = User(name = name, phone = phone, email = email)
                            scope.launch(Dispatchers.IO) { db.userDao().insertAll(newUser) }
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }) {
                            Text(text = "저장")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun TestFuncion() {
    NumberBookPracticeTheme {
        val context = LocalContext.current

        var name by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        Column {
            OutlinedTextField(value = name, onValueChange = { name = it })
            OutlinedTextField(value = phone, onValueChange = { phone = it })
            OutlinedTextField(value = email, onValueChange = { email = it })

            Button(onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "취소")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "저장")
            }
        }
    }
}