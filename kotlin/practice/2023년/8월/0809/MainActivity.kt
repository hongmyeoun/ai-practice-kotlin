package com.example.mlkitpractice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mlkitpractice.ui.theme.MlkitPracticeTheme
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MlkitPracticeTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
    var viewString by remember { mutableStateOf("") }
    //다른 엑티비티에서 인식한 텍스트를 받아오는 부분
    val launcher = rememberLauncherForActivityResult(
        //엑티비티가 시작되면서 정보를 받아옴(?)
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //viewString은 textfield에 입력하는 값 => 이미지에서 인식한 텍스트를 바로 텍스트 필드에 넣어줌
                viewString = result.data?.getStringExtra("textRecognized") ?: ""
            }
        }
    )

    //클립보드
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    //함수로 빼고 리스트처리
    val koEn = remember {
        val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH).build()
        //일반적으로 람다식에 가장 마지막에 나온값이 람다식의 리턴값이다.
        Translation.getClient(options)
    }
    val enKo = remember {
        val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.KOREAN).build()
        Translation.getClient(options)
    }
    val koJp = remember {
        val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.KOREAN)
            .setTargetLanguage(TranslateLanguage.JAPANESE).build()
        Translation.getClient(options)
    }
    val jpKo = remember {
        val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.JAPANESE)
            .setTargetLanguage(TranslateLanguage.KOREAN).build()
        Translation.getClient(options)
    }
    val enJp = remember {
        val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.JAPANESE).build()
        Translation.getClient(options)
    }
    val jpEn = remember {
        val options = TranslatorOptions.Builder().setSourceLanguage(TranslateLanguage.JAPANESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH).build()
        Translation.getClient(options)
    }
    var enabled by remember { mutableStateOf(false) }
    //다운로드는 한번만 하면된다 그래서 사용하는 것, 사용언어가 한글, 영어, 일본어 3개 다운로드
    LaunchedEffect(Unit) {
        val conditions = DownloadConditions.Builder().requireWifi().build()
        enKo.downloadModelIfNeeded(conditions).addOnSuccessListener {
           
            enabled = true
        }.addOnFailureListener { exception ->
            
        }
        enJp.downloadModelIfNeeded(conditions).addOnSuccessListener {
            //이부분 enKo랑 같은데 다른 enabled로 설정해줘야됨
            enabled = true
        }.addOnFailureListener { exception ->
            
        }
    }

    var textTranslated by remember { mutableStateOf("") }
    var isTrans by remember { mutableStateOf(false) }

    //여기서부터 레이아웃
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "홍진번역", fontWeight = FontWeight.Bold, fontSize = 25.sp)

        Spacer(modifier = Modifier.size(5.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box() {
                TextField(
                    value = viewString,
                    onValueChange = { viewString = it },
                    modifier = Modifier
                        .size(width = 280.dp, height = 200.dp)
                        .border(1.dp, color = Color.Black)
                )
            }
            Box(
                modifier = Modifier
                    .size(width = 280.dp, height = 200.dp)
                    .border(1.dp, color = Color.Black)

            ) {
                //통역버튼을 누르게 되면 실행이되는 부분
                if (isTrans) {
                    Box(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                        LazyColumn() {
                            item {
                                Text(text = textTranslated)
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
                        //만약 취소버튼을 누르게 되면 초기화면이 뜨는 것
                        Text(text = "글을 쓰고 원하는 통역 버튼을 눌러주세요~!!😊")
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    //클릭 했을시 TextField에 있는 모든값을 지움
                    isTrans = false
                    viewString = ""
                }) {
                    Text(text = "취소")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {
                    //번역된 텍스트를 clipboard에 복사는 코드
                    if (textTranslated.isNotEmpty()) {
                        val annotatedString = AnnotatedString(textTranslated)
                        clipboardManager.setText(annotatedString) // Copy text to clipboard
                    }
                }) {
                    Text(text = "복사")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {
                    //기존에 사용하던 intent
                    val intent = Intent(context, ImageTextTR::class.java)
                    //context.starthActivity와 동일하게 ImageTextTR엑티비티를 띄어줌
                    launcher.launch(intent)
                }) {
                    Text(text = "이미지 번역")
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
        Text(text = "통역버튼", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Divider()
        Row {
            Button(
                onClick = {
                    isTrans = true
                    koEn.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                    }.addOnFailureListener { }
                }, enabled = enabled
            ) {
                Text(text = "ko -> en")
            }
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = {
                    isTrans = true
                    koJp.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                    }.addOnFailureListener { }
                }, enabled = enabled
            ) {
                Text(text = "ko -> jp")
            }
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = {
                    isTrans = true
                    enJp.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                    }.addOnFailureListener { }
                }, enabled = enabled
            ) {
                Text(text = "en -> jp")
            }
        }
        Row {
            Button(
                onClick = {
                    isTrans = true
                    enKo.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                    }.addOnFailureListener { }
                }, enabled = enabled
            ) {
                Text(text = "en -> ko")
            }
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = {
                    isTrans = true
                    jpKo.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                    }.addOnFailureListener { }
                }, enabled = enabled
            ) {
                Text(text = "jp -> Ko")
            }
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = {
                    isTrans = true
                    jpEn.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                    }.addOnFailureListener { }
                }, enabled = enabled
            ) {
                Text(text = "jp -> en")
            }
        }
    }
}
