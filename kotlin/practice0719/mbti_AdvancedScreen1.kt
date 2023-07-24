import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practicembti.ui.theme.PracticembtiTheme
​
class AdvancedScreen1 : ComponentActivity() {
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val buttonPressed = result.data?.getStringExtra(AdvancedScreen2.EXTRA_BUTTON_PRESSED)
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticembtiTheme() {
                MainActivityScreen()
            }
        }
    }
    @Composable
    private fun MainActivityScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyButton("E","외향적")
            MyButton("I","내향적")
            MyButton("S","감각")
            MyButton("N","직관")
            MyButton("T","사고")
            MyButton("F","감정")
            MyButton("J","판단, 체계")
            MyButton("P","인신, 융통")
        }
    }
​
    @Composable
    private fun MyButton(mbti: String, mbtiInfo: String) {
        Button(onClick = {
            startForResult.launch(Intent(
                this@AdvancedScreen1, AdvancedScreen2::class.java
            ).apply {
                putExtra(AdvancedScreen2.EXTRA_BUTTON_PRESSED, mbtiInfo)
            })
        }) {
            Text(mbti)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
