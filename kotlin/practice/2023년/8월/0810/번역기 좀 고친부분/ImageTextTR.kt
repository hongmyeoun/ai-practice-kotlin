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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 8.dp, end = 2.dp, top = 40.dp)
                    ) {
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

                                recognizer.process(image)
                                    .addOnSuccessListener { visionText ->

                                        val combinedText = StringBuilder()

                                        for (block in visionText.textBlocks) {
                                            for (line in block.lines) {
                                                for (element in line.elements) {
                                                    val elementText = element.text
                                                    combinedText.append(elementText).append(" ")
                                                }
                                            }
                                        }

                                        val finalText = combinedText.toString().trim()
                                        textRecognized = finalText
                                    }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } ?: run {
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
                        Spacer(modifier = Modifier.size(10.dp))
                        Button(onClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            intent.putExtra("textRecognized", textRecognized)
                            setResult(RESULT_OK, intent)
                            finish()
                        }) {
                            Text(text = "복사")
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "텍스트 인식", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                        LazyColumn(
                            modifier = Modifier
                                .padding(5.dp)
                                .shadow(1.dp)
                        ) {
                            item {
                                Text(text = textRecognized)
                            }
                        }
                    }
                }
            }
        }
    }
}
