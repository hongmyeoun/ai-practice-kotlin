import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicecompose0723.ui.theme.PracticeCompose0723Theme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeCompose0723Theme {
                val scrollState = rememberScrollState()
                Box(
                    modifier = Modifier
                        .size(height = 680.dp, width = 300.dp)
                        .background(Color.Black)
                ) {
                    NewTextImage()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .size(height = 600.dp, width = 300.dp)
                                .verticalScroll(scrollState)
                        ) {
                            TopSetting()
                            Image(
                                painterResource(id = R.drawable.darkmode),
                                contentDescription = ""
                            )
                            MenuText(input = "🗨️   스레드")
                            MenuText(input = "📝   캔버스")
                            MenuText(input = "📨   초안 및 전송됨")
                            MenuText(input = "🔖   나중에")
                            Divider()
                            ChannelToggle(
                                "채널                                               -",
                                "채널                                               +",
                                "채널"
                            )
                            Divider()
                            ChannelToggle(
                                "다이렉트 메세지                                 -",
                                "다이렉트 메세지                                 +",
                                "다이렉트 메세지"
                            )
                        }
                        Divider()
                        BottomMenu()
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSetting() {
    var outtext by remember { mutableStateOf("") }
    Box() {
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.size(height = 50.dp, width = 300.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = ""
                )
                Text(
                    text = "KDT_인텔 인공지능 인재양성",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 40.sp,
                    color = Color.White
                )
            }
            Divider(Modifier.padding(5.dp))
            OutlinedTextField(
                placeholder = {
                    Text(
                        text = "다음으로 이동...",
                        fontSize = 10.sp,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                value = outtext,
                onValueChange = { outtext = it },
                modifier = Modifier
                    .padding(10.dp)
                    .size(height = 44.dp, width = 250.dp)
                    .align(CenterHorizontally)
            )
        }
    }
}
@Composable
fun NewTextImage() {
    val context = LocalContext.current
    Box(modifier = Modifier.absoluteOffset(225.dp, 520.dp)) {
        Text(
            text = "📃",
            fontSize = 50.sp,
            modifier = Modifier
                .padding(3.dp)
                .clickable {
                    val intent = Intent(context, AfterClickPage::class.java)
                    intent.putExtra("Click", "새글 작성")
                    context.startActivity(intent)
                }
        )
    }
}
@Composable
fun MenuText(input: String) {
    val context = LocalContext.current
    Text(
        text = input,
        color = Color.Gray,
        modifier = Modifier
            .padding(3.dp)
            .clickable {
                val intent = Intent(context, AfterClickPage::class.java)
                intent.putExtra("Click", input)
                context.startActivity(intent)
            })
    Spacer(modifier = Modifier.size(3.dp))
}
@Composable
fun ChannelToggle(unfoldedtext: String, foldedtext: String, menuName: String) {
    var isChannelExpanded by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (isChannelExpanded) unfoldedtext else foldedtext,
                color = Color.Gray,
                modifier = Modifier.padding(3.dp)
            )
            IconButton(onClick = { isChannelExpanded = !isChannelExpanded }) {
                Icon(
                    imageVector = if (isChannelExpanded) Icons.Default.ArrowForward else Icons.Default.ArrowDropDown,
                    contentDescription = "Toggle Channel",
                    tint = Color.Gray
                )
            }
        }
        @Composable
        fun Channel() {
            if (isChannelExpanded) {
                MenuText(input = "🍔   _공지사항")
                MenuText(input = "🍔   _질문있어요")
                MenuText(input = "🍔   _ai과정")
                MenuText(input = "🍔   _app과정")
                MenuText(input = "🍔   오프더레코드")
                MenuText(input = "🍔   일정알림")
                MenuText(input = "🍔   점심후기-공유해요")
                MenuText(input = "🍔   팀1-interstellar")
                MenuText(input = "➕   채널추가")
            }
        }
        @Composable
        fun DirectMessage() {
            if (isChannelExpanded) {
                MenuText(input = "☠   이홍진")
                MenuText(input = "☠   이홍진")
                MenuText(input = "☠   이홍진")
                MenuText(input = "☠   이홍진")
                MenuText(input = "☠   이홍진")
                MenuText(input = "☠   이홍진")
                MenuText(input = "☠   이홍진")
                MenuText(input = "☠   이홍진")
            }
        }
        when (menuName) {
            "채널" -> Channel()
            "다이렉트 메세지" -> DirectMessage()
        }
    }
}
@Composable
private fun BottomMenu() {
    Box(
        modifier = Modifier.size(height = 150.dp, width = 300.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            BottomText(image = "🏡", text = "홈")
            BottomText(image = "\uD83D\uDCAC", text = "DM")
            BottomText(image = "©️", text = "멘션")
            BottomText(image = "\uD83D\uDD0D", text = "검색")
            BottomText(image = "\uD83D\uDE0A", text = "나")
        }
    }
}
@Composable
fun BottomText(image: String, text: String) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = image,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    val intent = Intent(context, AfterClickPage::class.java)
                    intent.putExtra("Click", image)
                    context.startActivity(intent)
                },
            textAlign = TextAlign.Center,
            fontSize = 40.sp
        )
        Text(
            text = text, textAlign = TextAlign.Center, fontSize = 12.sp, color = Color.Gray
        )
    }
}
