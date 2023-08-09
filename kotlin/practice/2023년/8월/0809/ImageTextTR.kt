package com.example.mlkitpractice


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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.mlkitpractice.ui.theme.MlkitPracticeTheme
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import java.io.IOException

class ImageTextTR : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MlkitPracticeTheme {
                var selectUri by remember { mutableStateOf<Uri?>(null) }
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickVisualMedia(),
                    //url == 유니크한 경로
                    onResult = { uri ->
                        selectUri = uri
                    }
                )
                var textRecognized by remember { mutableStateOf("") }
                var image: InputImage
                val clipboardManager = LocalClipboardManager.current

                Row {
                    Column {
                        selectUri?.let {
                            val context = LocalContext.current
                            //버전이 낮은건 else를 실행한다
                            //ImageDecoder쪽을 복사하고 else todo랑 같이 검색하면됨
                            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                ImageDecoder.decodeBitmap(
                                    ImageDecoder.createSource(
                                        context.contentResolver,
                                        it
                                    )
                                )
                            } else {
                                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                            }
                            Image(
                                bitmap = bitmap.asImageBitmap(), contentDescription = "",
                                modifier = Modifier
                                    .size(100.dp)
                                    .shadow(2.dp)
                                    .clickable {
                                        launcher.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                            )
                            try {
                                image = InputImage.fromFilePath(context, selectUri!!)
                                val recognizer =
                                    TextRecognition.getClient(
                                        KoreanTextRecognizerOptions.Builder().build()
                                    )

                                val result = recognizer.process(image)
                                    .addOnSuccessListener { visionText ->
                                        // Task completed successfully
                                        // ...
                                        textRecognized = visionText.text
                                    }
                                    .addOnFailureListener { e ->
                                        // Task failed with an exception
                                        // ...
                                    }

                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } ?: run {
                            // selectUri가 null인 경우 여기서 다른 이미지를 띄워줍니다.
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "기본이미지",
                                modifier = Modifier
                                    .size(100.dp)
                                    .shadow(2.dp)
                                    .clickable {
                                        launcher.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                            )
                        }
                        Button(onClick = {
                            if (textRecognized.isNotEmpty()) {
                                val annotatedString = AnnotatedString(textRecognized)
                                clipboardManager.setText(annotatedString) // Copy text to clipboard
                            }
                        }) {
                            Text(text = "복사")
                        }
                    }
                    Text(text = textRecognized)
                }
            }
        }
    }
}

//val resultText = result.text
//val combinedText = StringBuilder()
//
//for (block in result.textBlocks) {
//    for (line in block.lines) {
//        for (element in line.elements) {
//            val elementText = element.text
//            combinedText.append(elementText).append(" ")
//        }
//        combinedText.append("\n") // 한 라인이 끝날 때마다 줄바꿈 추가
//    }
//}
//
//val finalText = combinedText.toString().trim() // 앞뒤 공백 제거