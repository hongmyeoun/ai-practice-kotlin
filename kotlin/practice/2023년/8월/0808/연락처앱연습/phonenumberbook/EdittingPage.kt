package com.example.phonenumberbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.phonenumberbook.data.PhoneNumberBookAppDatabase
import com.example.phonenumberbook.data.User
import com.example.phonenumberbook.data.UserDao
import com.example.phonenumberbook.ui.theme.PhoneNumberBookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EdittingPage : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PhoneNumberBookTheme {
                val uid = intent.getIntExtra("uid", 0)

                val scope = rememberCoroutineScope()

                val context = LocalContext.current
                val db = Room.databaseBuilder(
                    context,
                    PhoneNumberBookAppDatabase::class.java, "my.db"
                ).build()
                val myDao = db.userDao()
                val entity = myDao.getEntityByUid(uid)
                var newData by remember { mutableStateOf(entity?.name) }

                if (entity != null) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        TextField(
                            value = newData!!,
                            onValueChange = { newData = it },
                            label = { Text("Edit Data") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                val updatedEntity = entity.copy(name = newData)
                                scope.launch(Dispatchers.IO) { myDao.update(updatedEntity) }
                            },
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Text("Save Changes")
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Editting(
    scope: CoroutineScope,
    myDao: UserDao,
    entity: User,
    newData: String,
    onNewDataChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = newData,
            onValueChange = { onNewDataChange(it) },
            label = { Text("Edit Data") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val updatedEntity = entity.copy(name = newData)
                scope.launch(Dispatchers.IO) { myDao.update(updatedEntity) }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Save Changes")
        }
    }
}


//                val userList by db.userDao().getAll().collectAsState(initial = emptyList())

//
//
//                var oldName = intent.getStringExtra("name") ?: ""
//                var oldPhone = intent.getStringExtra("phone") ?: ""
//                var oldEmail = intent.getStringExtra("email") ?: ""
//
//                Column {
//                    TextField(value = oldName, onValueChange = {oldName = it})
//                    TextField(value = oldPhone, onValueChange = {oldPhone = it})
//                    TextField(value = oldEmail, onValueChange = {oldEmail = it})
//                    Button(onClick = {
//
//                    }) {
//                        Text(text = "수정")
//                    }
//                }