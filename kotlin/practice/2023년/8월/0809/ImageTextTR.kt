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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
                    onResult = { uri ->
                        selectUri = uri
                    }
                )
                var textRecognized by remember { mutableStateOf("") }
                var image: InputImage
                val context = LocalContext.current

                Row {
                    Column {
                        selectUri?.let {
                            val context = LocalContext.current
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
                                        //이미지로 인식한 텍스트는 텍스트 박스임 그래서 이상하게 줄이바뀌거나 그런현상이 있음
                                        //그것을 한줄로 바꿔주는 부분 시작
                                        val combinedText = StringBuilder()

                                        for (block in visionText.textBlocks) {
                                            for (line in block.lines) {
                                                for (element in line.elements) {
                                                    val elementText = element.text
                                                    combinedText.append(elementText).append(" ")
                                                }
                                            }
                                        }
                                        val finalText = combinedText.toString().trim() // 앞뒤 공백 제거
                                        //여기까지 한줄로 바꾸는 작업
                                        //이건 인식한 텍스트를 한줄텍스트로 바꾼것을
                                        //변수에 저장 136줄 버튼에서 MainActivity로 넘겨줄 예정
                                        textRecognized = finalText

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
                            //버튼이 눌렸을때 intent를 MainActivity로 넘겨줌
                            val intent = Intent(context, MainActivity::class.java)
                            intent.putExtra("textRecognized", textRecognized)
                            //기존에 사용하던 context.startActivitiy(intent)가 아니라 결과를 넘겨주면서 activity를 닫아버리는 것임
                            setResult(RESULT_OK,intent)
                            //finish를 통해 엑티비티 종료 -> 복사버튼을 누르면 mainactivity에 인식한 텍스트를 옮겨줌
                            finish()
                        }) {
                            Text(text = "복사")
                        }
                    }
                    LazyColumn() {
                        item {
                            Text(text = textRecognized)
                        }
                    }
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
