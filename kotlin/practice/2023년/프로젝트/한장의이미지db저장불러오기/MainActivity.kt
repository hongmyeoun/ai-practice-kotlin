package com.example.testttt1

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testttt1.data.AppDatabase
import com.example.testttt1.data.ImageEntity
import com.example.testttt1.ui.theme.Testttt1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Testttt1Theme {
                val context = LocalContext.current
                val db = remember { AppDatabase.getDatabase(context) }
                val scope = rememberCoroutineScope()
                val imageList by db.imageDao().getAll().collectAsState(initial = emptyList())

                var selectUri by remember { mutableStateOf<Uri?>(null) }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickVisualMedia(),
                    onResult = { uri ->
                        selectUri = uri
                        //어플을 종료하면 퍼미션이 없어져 부여하는 부분
                        val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        if (selectUri != null) {
                            context.contentResolver.takePersistableUriPermission(
                                selectUri!!,
                                flag
                            )
                        }
                    }
                )

                Column {
                    Column {
                        //선택한 이미지를 보여주는 곳
                        if (selectUri != null) {
                            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                ImageDecoder.decodeBitmap(
                                    ImageDecoder.createSource(
                                        context.contentResolver,
                                        selectUri!!
                                    )
                                )
                            } else {
                                MediaStore.Images.Media.getBitmap(
                                    context.contentResolver,
                                    selectUri
                                )
                            }
                            Image(
                                bitmap = bitmap.asImageBitmap(), contentDescription = "",
                                modifier = Modifier
                                    .size(100.dp)
                                    .shadow(2.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "기본이미지",
                                modifier = Modifier
                                    .size(100.dp)
                                    .shadow(2.dp)
                            )
                        }
                        Row {
                            Button(onClick = {
                                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }) {
                                Text(text = "이미지 가져오기")
                            }
                            Button(onClick = {
                                //가져온 이미지가 없다면 저장했을때 image에 null값이 들어가야되므로
                                if (selectUri != null) {
                                    val newImage = ImageEntity(image = selectUri.toString())
                                    scope.launch(Dispatchers.IO) {
                                        db.imageDao().insertAll(newImage)
                                    }
                                }else{
                                    val newImage = ImageEntity()
                                    scope.launch(Dispatchers.IO) {
                                        db.imageDao().insertAll(newImage)
                                    }
                                }
                            }) {
                                Text(text = "저장")
                            }
                        }

                        for (idAndImageString in imageList) {
                            //가져온 image에 uri.string값을 uri로 바꿔줌
                            val selectedUri = idAndImageString.image?.let { Uri.parse(it) }
                            //db에 있는 이미지를 보여주는 곳
                            Row {
                                if (selectedUri != null) {
                                    val bitmap =
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                            ImageDecoder.decodeBitmap(
                                                ImageDecoder.createSource(
                                                    context.contentResolver,
                                                    selectedUri
                                                )
                                            )
                                        } else {
                                            MediaStore.Images.Media.getBitmap(
                                                context.contentResolver,
                                                selectedUri
                                            )
                                        }
                                    Image(
                                        bitmap = bitmap.asImageBitmap(), contentDescription = "",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .shadow(2.dp)
                                    )
                                }
                                Text(text = "${idAndImageString.id}")
                            }
                        }
                    }
                }
            }
        }
    }
}
