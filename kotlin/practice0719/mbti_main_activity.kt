import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.practicembti.ui.theme.PracticembtiTheme
​
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticembtiTheme() {
                Column() {
                    val c = LocalContext.current
                    Button(
                        onClick = {
                            val intent = Intent(c,AdvancedScreen1::class.java)
                            c.startActivity(intent)
                        }) {
                        Text(text = "이건 advanced Activity2개로 구성하는거 일껍니다")
                    }
                    Row {
                        MyButton(activityName = "E", mbtitext = "E")
                        MyButton(activityName = "I", mbtitext = "I")
                    }
                    Row {
                        MyButton(activityName = "S", mbtitext = "S")
                        MyButton(activityName = "N", mbtitext = "N")
                    }
                    Row {
                        MyButton(activityName = "F", mbtitext = "F")
                        MyButton(activityName = "T", mbtitext = "T")
                    }
                    Row {
                        MyButton(activityName = "P", mbtitext = "P")
                        MyButton(activityName = "J", mbtitext = "J")
                    }
                }
            }
        }
    }
}
@Composable
fun MyButton(activityName: String, mbtitext: String) {
    val context = LocalContext.current
    val activation: Intent = when (activityName) {
        "E" -> Intent(context, Einfo::class.java)
        "I" -> Intent(context, Iinfo::class.java)
        "S" -> Intent(context, Sinfo::class.java)
        "N" -> Intent(context, Ninfo::class.java)
        "T" -> Intent(context, Tinfo::class.java)
        "F" -> Intent(context, Finfo::class.java)
        "P" -> Intent(context, Pinfo::class.java)
        "J" -> Intent(context, Jinfo::class.java)
        else -> Intent(context, Elseinfo::class.java)
    }
    Button(
        onClick = {
            context.startActivity(activation)
        }
    ) {
        Text(text = mbtitext)
    }
}
