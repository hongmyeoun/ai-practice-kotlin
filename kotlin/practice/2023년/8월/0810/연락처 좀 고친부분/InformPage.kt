package com.example.numberbookpractice

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberbookpractice.ui.theme.EdittingPage
import com.example.numberbookpractice.ui.theme.NumberBookPracticeTheme

class InformPage : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberBookPracticeTheme {
                val context = LocalContext.current
                val username = intent.getStringExtra("UserName") ?: ""
                val userphone = intent.getStringExtra("UserPhone") ?: ""
                val useremail = intent.getStringExtra("UserEmail") ?: ""
                val uid = intent.getIntExtra("UID", 0)


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OneImageLoader()
//                    Image(
//                        painter = painterResource(id = R.drawable.profile),
//                        contentDescription = "profile",
//                        modifier = Modifier
//                            .size(100.dp)
//                            .clip(CircleShape)
//                    )

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
                        intent.putExtra("UserUid",uid)
                        context.startActivity(intent)
                    }) {
                        Text(text = "수정")
                    }
                }
            }
        }
    }
}

@Composable
private fun OneImageLoader() {
    var selectUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        //url == 유니크한 경로
        onResult = { uri ->
            selectUri = uri
        }
    )

    if (selectUri != null) {
        val context = LocalContext.current
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    selectUri!!
                )
            )
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, selectUri)
        }
        Image(
            bitmap = bitmap.asImageBitmap(), contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .shadow(2.dp)
                .clickable {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "기본이미지",
            modifier = Modifier
                .size(100.dp)
                .shadow(2.dp)
                .clickable {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
        )
    }
}

//val scope = rememberCoroutineScope()
//val uid = intent.getIntExtra("UID", 0)
//val context = LocalContext.current
//val db = Room.databaseBuilder(
//    context,
//    NumberBookAppDatabase::class.java, "my.db"
//).build()
//scope.launch(Dispatchers.IO) {
//    val user = db.userDao().getUserById(userId = uid)
//}

