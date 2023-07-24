import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.practicelogin.ui.theme.PracticeloginTheme
​
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeloginTheme {
                LogSignSystem()
            }
        }
    }
}
​
@Composable
fun LogSignSystem() {
    val context = LocalContext.current
    var enables by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IdAndPwInput(onChangeEnable = { enables = it })
        Row(modifier = Modifier.width(270.dp), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    val intent = Intent(context, LogInActivity::class.java)
                    context.startActivity(intent)
                },
                enabled = enables
            ) {
                Text(text = "Log In")
            }
            Button(
                onClick = {
                    val intent = Intent(context, SignUpActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdAndPwInput(onChangeEnable: (Boolean) -> Unit) {
    var idInput by remember { mutableStateOf("") }
    var pwInput by remember { mutableStateOf("") }
    TextField(
        value = idInput,
        onValueChange = {
            idInput = it
            onChangeEnable(idInput.length >= 6 && pwInput.length >= 6)
        },
        placeholder = { Text(text = "Enter ID...", fontStyle = FontStyle.Italic) }
    )
    TextField(
        value = pwInput,
        onValueChange = {
            pwInput = it
            onChangeEnable(idInput.length >= 6 && pwInput.length >= 6)
        },
        placeholder = { Text(text = "Enter Password...", fontStyle = FontStyle.Italic) },
        visualTransformation = PasswordVisualTransformation()
    )
}
