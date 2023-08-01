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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.practicelogin.ui.theme.PracticeloginTheme
​
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeloginTheme {
                IdPwCreater()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdPwCreater() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkpassword by remember { mutableStateOf("") }
    val enable1 =
        password == checkpassword && name.isNotEmpty() && password.isNotEmpty() && checkpassword.isNotEmpty()
    val enable2 = name.length >= 6 && password.length >=6
    val enables = enable1 && enable2
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text(text = "ID") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(text = "Password") }
        )
        TextField(
            value = checkpassword,
            onValueChange = { checkpassword = it },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(text = "Check Password") }
        )
        Row(modifier = Modifier.width(270.dp), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    val intent = Intent(context, LogInActivity::class.java)
                    context.startActivity(intent)
                },
                enabled = enables
            ) {
                Text(text = "Sign Up")
            }
            Button(
                onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text(text = "Go Login")
            }
        }
    }
}
