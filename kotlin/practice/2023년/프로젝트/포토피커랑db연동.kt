//포토피커로 가져온 이미지를 db에 넣고 다시 가져와서 어플이 종료되어도 사용
//예제에서는 저장만하고 업데이트가 아니여서 바꾸는건 볼수 없다, 그리고 1번uid만 가져온다.

package com.example.testprojet

import android.content.Context
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.testprojet.data.AppDatabase
import com.example.testprojet.data.ImageClass
import com.example.testprojet.ui.theme.TestProjetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjetTheme {
                val context = LocalContext.current
                val db = remember { AppDatabase.getDatabase(context) }
                val scope = rememberCoroutineScope()

                val imageList by db.imageDao().getAll().collectAsState(initial = emptyList())
                Column {
                    var selectUri by remember { mutableStateOf<Uri?>(null) }
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.PickVisualMedia(),
                        onResult = { uri ->
                            selectUri = uri
                            //어플을 종료하면 퍼미션이 없어져 부여하는 부분
                            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                            context.contentResolver.takePersistableUriPermission(selectUri!!, flag)
                        }
                    )
                    //test를 위해 uid 1번만 확인 uri는 string값이다
                    val imageFirst = imageList.find { it.uid == 1 }
                    //가져온 image에 uri.string값을 uri로 바꿔줌
                    val selectedUri = imageFirst?.image?.let { Uri.parse(it) }

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
                                MediaStore.Images.Media.getBitmap(context.contentResolver, selectUri)
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
                                val newImage = ImageClass(image = selectUri.toString())
                                scope.launch(Dispatchers.IO) { db.imageDao().insertAll(newImage) }
                            }) {
                                Text(text = "저장")
                            }
                        }
                        //db에 있는 이미지를 보여주는 곳
                        if (selectedUri != null) {
                            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                ImageDecoder.decodeBitmap(
                                    ImageDecoder.createSource(
                                        context.contentResolver,
                                        selectedUri!!
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
                    }


                    var selectUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
                    val launcher2 = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.PickMultipleVisualMedia(),
                        onResult = { uris ->
                            selectUris = uris
                            //selectUris는 list이기 때문에 권한을 하나하나 다줘야 된다.
                            for (uri in selectUris) {
                                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                context.contentResolver.takePersistableUriPermission(uri!!, flag)
                            }
                        }
                    )
                    //list<string?>?을 List<Uri!>로 바꾸는 작업 -> 그냥 if문으로 널체크를 하던가 let으로 돌려서 사용해도 될듯
                    val selectedUrisList = imageFirst?.imageList
                    val uriList = selectedUrisList?.map { uriString -> Uri.parse(uriString) }
                    val uriStringList = selectUris.map { uri -> uri.toString() }

                    Column {
                        LazyRow() {
                            item {
                                if (selectUris.isNotEmpty()) {
                                    for (uri in selectUris) {
                                        val bitmap =
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                                ImageDecoder.decodeBitmap(
                                                    ImageDecoder.createSource(
                                                        context.contentResolver,
                                                        uri!!
                                                    )
                                                )
                                            } else {
                                                MediaStore.Images.Media.getBitmap(
                                                    context.contentResolver,
                                                    uri
                                                )
                                            }
                                        Image(
                                            bitmap = bitmap.asImageBitmap(), contentDescription = "",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .shadow(2.dp)
                                                .clickable {
                                                    launcher2.launch(
                                                        PickVisualMediaRequest(
                                                            ActivityResultContracts.PickVisualMedia.ImageAndVideo
                                                        )
                                                    )
                                                }
                                        )
                                    }
                                } else {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_launcher_background),
                                        contentDescription = "기본이미지",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .shadow(2.dp)
                                    )
                                }

                            }
                        }
                        Row {
                            Button(onClick = {
                                launcher2.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                            }) {
                                Text(text = "여러이미지 가져오기")
                            }
                            Button(onClick = {
                                val newImage = ImageClass(imageList = uriStringList)
                                scope.launch(Dispatchers.IO) { db.imageDao().insertAll(newImage) }
                            }) {
                                Text(text = "저장")
                            }
                        }
                        LazyRow() {
                            item {
                                if (uriList != null) {
                                    if (uriList.isNotEmpty()) {
                                        for (uri in uriList) {
                                            val bitmap =
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                                    ImageDecoder.decodeBitmap(
                                                        ImageDecoder.createSource(
                                                            context.contentResolver,
                                                            uri!!
                                                        )
                                                    )
                                                } else {
                                                    MediaStore.Images.Media.getBitmap(
                                                        context.contentResolver,
                                                        uri
                                                    )
                                                }
                                            Image(
                                                bitmap = bitmap.asImageBitmap(),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .size(100.dp)
                                                    .shadow(2.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
