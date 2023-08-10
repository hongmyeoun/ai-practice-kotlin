package com.example.mlkitpractice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mlkitpractice.ui.theme.MlkitPracticeTheme
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

private lateinit var firebaseAnalytics: FirebaseAnalytics


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        setContent {
            MlkitPracticeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var viewString by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewString = result.data?.getStringExtra("textRecognized") ?: ""
            }
        }
    )

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    val koEn = remember { Translator(TranslateLanguage.KOREAN, TranslateLanguage.ENGLISH) }
    val enKo = remember { Translator(TranslateLanguage.ENGLISH, TranslateLanguage.KOREAN) }
    val koJp = remember { Translator(TranslateLanguage.KOREAN, TranslateLanguage.JAPANESE) }
    val jpKo = remember { Translator(TranslateLanguage.JAPANESE, TranslateLanguage.KOREAN) }
    val enJp = remember { Translator(TranslateLanguage.ENGLISH, TranslateLanguage.JAPANESE) }
    val jpEn = remember { Translator(TranslateLanguage.JAPANESE, TranslateLanguage.ENGLISH) }

    var enKoEnabled by remember { mutableStateOf(false) }
    var enJpEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val conditions = DownloadConditions.Builder().requireWifi().build()
        enKo.downloadModelIfNeeded(conditions).addOnSuccessListener {
            enKoEnabled = true
        }
        enJp.downloadModelIfNeeded(conditions).addOnSuccessListener {
            enJpEnabled = true
        }
    }

    var textTranslated by remember { mutableStateOf("") }
    var isTrans by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "홍진번역", fontWeight = FontWeight.Bold, fontSize = 25.sp)

        Spacer(modifier = Modifier.size(5.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box() {
                TranslateInputWindow(viewString)
            }
            Box(
                modifier = Modifier
                    .size(width = 280.dp, height = 200.dp)
                    .border(1.dp, color = Color.Black)

            ) {
                TranslateOutputWindow(isTrans, textTranslated)
            }
            Row(horizontalArrangement = Arrangement.Center) {
                ButtonCancel(isTrans, viewString)
                Spacer(modifier = Modifier.size(10.dp))
                ButtonCopy(textTranslated, clipboardManager)
                Spacer(modifier = Modifier.size(10.dp))
                ButtonGetImageText(context, launcher)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
        Text(text = "통역버튼", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Divider()
        Row {
            TranslationButton(
                onClick = { translator ->
                    isTrans = true
                    translator.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                        firebaseAnalytics.logEvent("translation_performed") {
                            param("source_text", viewString)
                            param("translated_text", textTranslated)
                        }
                    }
                },
                enabled = enKoEnabled,
                buttonText = "ko -> en",
                translator = koEn
            )
            Spacer(modifier = Modifier.size(5.dp))
            TranslationButton(
                onClick = { translator ->
                    isTrans = true
                    translator.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                        firebaseAnalytics.logEvent("translation_performed") {
                            param("source_text", viewString)
                            param("translated_text", textTranslated)
                        }
                    }
                },
                enabled = enJpEnabled,
                buttonText = "ko -> jp",
                translator = koJp
            )
            Spacer(modifier = Modifier.size(5.dp))
            TranslationButton(
                onClick = { translator ->
                    isTrans = true
                    translator.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                        firebaseAnalytics.logEvent("translation_performed") {
                            param("source_text", viewString)
                            param("translated_text", textTranslated)
                        }
                    }
                },
                enabled = enJpEnabled,
                buttonText = "en -> jp",
                translator = enJp
            )
        }
        Row {
            TranslationButton(
                onClick = { translator ->
                    isTrans = true
                    translator.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                        firebaseAnalytics.logEvent("translation_performed") {
                            param("source_text", viewString)
                            param("translated_text", textTranslated)
                        }
                    }
                },
                enabled = enKoEnabled,
                buttonText = "en -> ko",
                translator = enKo
            )
            Spacer(modifier = Modifier.size(5.dp))
            TranslationButton(
                onClick = { translator ->
                    isTrans = true
                    translator.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                        firebaseAnalytics.logEvent("translation_performed") {
                            param("source_text", viewString)
                            param("translated_text", textTranslated)
                        }
                    }
                },
                enabled = enJpEnabled,
                buttonText = "jp -> Ko",
                translator = jpKo
            )
            Spacer(modifier = Modifier.size(5.dp))
            TranslationButton(
                onClick = { translator ->
                    isTrans = true
                    translator.translate(viewString).addOnSuccessListener { translatedText ->
                        textTranslated = translatedText
                        firebaseAnalytics.logEvent("translation_performed") {
                            param("source_text", viewString)
                            param("translated_text", textTranslated)
                        }
                    }
                },
                enabled = enJpEnabled,
                buttonText = "jp -> en",
                translator = jpEn
            )
        }
    }
}

@Composable
fun TranslationButton(
    onClick: (translator: Translator) -> Unit,
    enabled: Boolean,
    buttonText: String,
    translator: Translator
) {
    Button(
        onClick = { onClick(translator) },
        enabled = enabled
    ) {
        Text(text = buttonText)
    }
}

@Composable
private fun ButtonGetImageText(
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    Button(onClick = {
        val intent = Intent(context, ImageTextTR::class.java)
        launcher.launch(intent)
    }) {
        Text(text = "이미지 번역")
    }
}

@Composable
private fun ButtonCopy(
    textTranslated: String,
    clipboardManager: ClipboardManager
) {
    Button(onClick = {
        if (textTranslated.isNotEmpty()) {
            val annotatedString = AnnotatedString(textTranslated)
            clipboardManager.setText(annotatedString)
        }
    }) {
        Text(text = "복사")
    }
}

@Composable
private fun ButtonCancel(isTrans: Boolean, viewString: String) {
    var isTrans1 = isTrans
    var viewString1 = viewString
    Button(onClick = {
        isTrans1 = false
        viewString1 = ""
    }) {
        Text(text = "취소")
    }
}

@Composable
private fun TranslateOutputWindow(isTrans: Boolean, textTranslated: String) {
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
            Text(text = "글을 쓰고 원하는 통역 버튼을 눌러주세요~!!😊")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TranslateInputWindow(viewString: String) {
    var viewString1 = viewString
    TextField(
        value = viewString1,
        onValueChange = { viewString1 = it },
        modifier = Modifier
            .size(width = 280.dp, height = 200.dp)
            .border(1.dp, color = Color.Black)
    )
}

private fun Translator(source: String, target: String): Translator {
    val options = TranslatorOptions.Builder().setSourceLanguage(source)
        .setTargetLanguage(target).build()
    return Translation.getClient(options)
}