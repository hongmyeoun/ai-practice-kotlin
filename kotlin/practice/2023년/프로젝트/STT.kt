//TextField에 글을 쓰다 음성인식을 하고 이어서 쓸수 있도록 STT를 만듦

//첫 문제는 
//TextField에 음성인식 글을 붙여쓰는 것, input + recog 이렇게 하면 textfield에 글을 추가할때 계속 recog가 쓰여짐
//해결을 위해 onValueChange부분에 recog = ""를 추가 => input이 수정되면 recog가 초기화 되어 input + recog = input이됨

//두번째 문제 
//textfield에 text부분은 input + recog임 그리고 바뀌는값은 input임
//이 말은 음성인식으로 들어온 recog가 textfield에 추가되면 키보드 커서는 input쪽(최근에 썻던내용, 음성인식부분X)에 있다는 것
//추가로 글을 작성하기 위해서는 이 뒤로 커서가 가서 가장 끝에 글을 쓰는게 보편적
//그러므로 음성을 받았을때 음성인식버튼이 비활성화됨, 그리고 recog가 초기화 될때, 즉 input을 수정 했을때(textfield에 가장 끝으로가서 스페이스바 또는 엔터)
//음성인식 버튼이 활성화 되게 만듦.

//세번째 문제
//음성인식은 했는데 잘못해서 사용하기 싫을때 버튼이 비활성화 되어있기 때문에 새로운 버튼을 추가해서 recog를 초기화함
//이렇게 되면 recog도 초기화하면서 잘못써진 text도 지우고 음성인식버튼까지 활성화 가능(재녹음)

//네번째 문제
//글을 쓰다 음성인식이 들어오면 자동으로 한칸 뛰어졌으면 좋겠어서 spaceBar라는 변수를 만들고 어쩌구 해봤지만
//그냥 recog를 처음받는 부분에 " "를 넣어주면 되었다. 어쩌피 String

package com.example.voicetotextpractice

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.voicetotextpractice.ui.theme.VoiceToTextPracticeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VoiceToTextPracticeTheme {
                var inputText by remember { mutableStateOf("") }
                var recognizedText by remember { mutableStateOf("") }
                //처음엔 버튼 활성화
                var isRecognitionEnabled by remember { mutableStateOf(true) }

                val speechRecognizerLauncher = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val data: Intent? = result.data
                        val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        if (!results.isNullOrEmpty()) {
                            //쓰던부분과 음성인식부분의 띄어쓰기
                            recognizedText = " " + results[0]
                            //음성인식을 받았으면 버튼을 비활성화
                            isRecognitionEnabled = false
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    TextField(
                        value = inputText  + recognizedText,
                        onValueChange = {
                            inputText = it
                            recognizedText = ""
                            //TextField를 수정 했으면 음성인식이 초기화되며 버튼 활성화
                            isRecognitionEnabled = true
                        })
                    Button(
                        onClick = {
                            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                            intent.putExtra(
                                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                            )
                            speechRecognizerLauncher.launch(intent)
                        },
                        //버튼컨트롤
                        enabled = isRecognitionEnabled
                    ) {
                        Text(text = "음성인식")
                    }
                    Button(onClick = {
                        recognizedText = ""
                        isRecognitionEnabled = true
                    }) {
                        Text(text = "음성취소")
                    }
                }
            }
        }
    }
}
