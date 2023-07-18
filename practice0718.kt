@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdPwCreater() {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkpassword by remember { mutableStateOf("") }
    var showBox by remember { mutableStateOf(false) }

    val enable =
        password == checkpassword && name.isNotEmpty() && password.isNotEmpty() && checkpassword.isNotEmpty()

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
                onClick = { showBox = true },
                enabled = enable
            ) {
                Text(text = "회원가입")
            }
        }
    }

    if (showBox) {
        Box(
            modifier = Modifier
                .height(250.dp)
                .width(400.dp)
                .background(Color.Black)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "회원가입이 완료되었습니다!",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Practice() {
    Compose0717Theme {
        IdPwCreater()
    }
}
