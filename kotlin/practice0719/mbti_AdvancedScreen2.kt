import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.practicembti.ui.theme.PracticembtiTheme
​
class AdvancedScreen2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticembtiTheme() {
                MainActivity2Screen()
            }
        }
    }
    @Composable
    private fun MainActivity2Screen() {
        val message = getMessageFromIntent(intent)
        val c = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(message)
            Button(
                onClick = {
                    val i = Intent(c, AdvancedScreen1::class.java)
                    c.startActivity(i)
                }) {
                Text(text = "이전페이지")
            }
        }
        
    }
    private fun getMessageFromIntent(intent: Intent): String {
        return intent.getStringExtra(EXTRA_BUTTON_PRESSED) ?: "버튼을 눌리지 않았습니다."
    }
    companion object {
        const val EXTRA_BUTTON_PRESSED = "extra_button_pressed"
    }
}
