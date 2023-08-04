package com.example.starbuckscopyapp0804

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.starbuckscopyapp0804.ui.theme.StarbucksCopyApp0804Theme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarbucksCopyApp0804Theme {

            }
        }
    }
}