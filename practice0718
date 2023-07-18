@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Practice() {
    Compose0717Theme {
        Column {
            IdPwCreater()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdPwCreater() {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkpassword by remember { mutableStateOf("") }
    val enable =
        password == checkpassword && name.isNotEmpty() && password.isNotEmpty() && checkpassword.isNotEmpty()
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
            onClick = {  },
            enabled = enable
        ) {
            Text(text = "회원가입")
        }
    }
}
