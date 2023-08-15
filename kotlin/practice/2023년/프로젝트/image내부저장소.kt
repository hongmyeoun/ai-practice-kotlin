//사진을 가져와서 어플 내부저장소에 저장하고 불러오는 어플, 완벽하진 않지만 이런식으로 작동한다.
package com.example.testttt1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.testttt1.ui.theme.Testttt1Theme
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Testttt1Theme {

                val context = LocalContext.current

                var selectUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }
                val launcher2 = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickMultipleVisualMedia(),
                    onResult = { uris ->
                        selectUris = uris
                    }
                )

                Column {

                    if (selectUris.isNotEmpty()) {
                        saveImagesToInternalStorage(context, selectUris)
                        for (uri in selectUris) {

                            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                ImageDecoder.decodeBitmap(
                                    ImageDecoder.createSource(
                                        context.contentResolver,
                                        uri!!
                                    )
                                )
                            } else {
                                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                            }
                            Image(
                                bitmap = bitmap.asImageBitmap(), contentDescription = "",
                                modifier = Modifier
                                    .size(100.dp)
                                    .shadow(2.dp)
                                    .clickable {
                                        launcher2.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                                    }
                            )
                        }
                    }
                    val loadedImages = loadImagesFromInternalStorage(context)

                    Row {
                        for (bitmap in loadedImages) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "Loaded Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}


private fun saveImagesToInternalStorage(context: Context, uris: List<Uri?>) {
    val directory = File(context.filesDir, "images")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    for ((index, uri) in uris.withIndex()) {
        val inputStream = context.contentResolver.openInputStream(uri!!)
        val file = File(directory, "image_$index.jpg")
        val outputStream = FileOutputStream(file)

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
    }
}
private fun loadImagesFromInternalStorage(context: Context): List<Bitmap> {
    val imageBitmaps = mutableListOf<Bitmap>()
    val directory = File(context.filesDir, "images")

    if (directory.exists()) {
        val imageFiles = directory.listFiles()

        imageFiles?.forEach { file ->
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            if (bitmap != null) {
                imageBitmaps.add(bitmap)
            }
        }
    }
    return imageBitmaps
}
