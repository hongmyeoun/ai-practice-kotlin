package com.example.getimagepractice0808

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.getimagepractice0808.ui.theme.GetImagePractice0808Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetImagePractice0808Theme {
                Mainscreen()
            }
        }
    }
}

@Composable
fun Mainscreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var selectUri by remember { mutableStateOf<Uri?>(null) }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                //url == 유니크한 경로
                onResult = { uri ->
                    selectUri = uri
                }
            )

//            if (selectUri != null){
//                val context = LocalContext.current
//                //버전이 낮은건 else를 실행한다
//                //ImageDecoder쪽을 복사하고 else todo랑 같이 검색하면됨
//                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, selectUri!!))
//                } else {
//                    MediaStore.Images.Media.getBitmap(context.contentResolver, selectUri)
//                }
//                Image(
//                    bitmap = bitmap.asImageBitmap(), contentDescription = "",
//                    modifier = Modifier
//                        .size(100.dp)
//                        .shadow(2.dp)
//                        .clickable {
//                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                        }
//                )
//            }else{
//                Image(
//                    painter = painterResource(id = R.drawable.clap),
//                    contentDescription = "기본이미지",
//                    modifier = Modifier
//                        .size(100.dp)
//                        .shadow(2.dp)
//                        .clickable {
//                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                        }
//                )
//            }

            //let 널이 아니면 let에 있는 코드를 실행
            selectUri?.let {
                val context = LocalContext.current
                //버전이 낮은건 else를 실행한다
                //ImageDecoder쪽을 복사하고 else todo랑 같이 검색하면됨
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, it))
                } else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
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
            } ?: run {
                // selectUri가 null인 경우 여기서 다른 이미지를 띄워줍니다.
                Image(
                    painter = painterResource(id = R.drawable.clap),
                    contentDescription = "기본이미지",
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(2.dp)
                        .clickable {
                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                )
            }
//
//            Spacer(modifier = Modifier.size(10.dp))
//            Button(
//                onClick = {
//                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//            ) {
//                Text(text = "변경")
//            }
        }
    }
}
