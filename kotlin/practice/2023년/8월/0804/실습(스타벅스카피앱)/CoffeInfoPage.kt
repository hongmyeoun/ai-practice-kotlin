package com.example.starbuckscopyapp0804

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starbuckscopyapp0804.ui.theme.StarbucksCopyApp0804Theme

class CoffeInfoPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarbucksCopyApp0804Theme {
                val coffeeName = intent.getStringExtra("coffeeName") ?: ""
                val engName = intent.getStringExtra("EngName") ?: ""
                val price = intent.getStringExtra("Price") ?: ""
                //Int는 default값을 넣어야됨
                val imageResID = intent.getIntExtra("ImageID", 0)
                Tiriri(coffeeName, engName, price, imageResID)

            }
        }
    }
}

@Composable
fun NextPageTop() {
    val context = LocalContext.current
    Box() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text("<", color = Color.White, fontSize = 25.sp)
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text("📤", fontSize = 25.sp)
            }
        }

    }
}


@Composable
fun Tiriri(coffeename: String, engname: String, price: String, imageResId: Int) {
    val context = LocalContext.current
    val painter: Painter = painterResource(id = imageResId)
    val scrollState = rememberScrollState()
    val tmi = MoreInfo(engname)
    val tmi2 = OrderInfo(engname)

    StarbucksCopyApp0804Theme {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Box {
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )
                NextPageTop()
            }
            Column {
                Box(modifier = Modifier.padding(50.dp)) {
                    Column {

                        Text(
                            text = coffeename,
                            fontSize = 25.sp,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )

                        Text(text = engname, fontSize = 12.sp, color = Color.LightGray)

                        Spacer(modifier = Modifier.size(15.dp))

                        Text(text = tmi, fontSize = 13.sp, style = TextStyle(lineHeight = 15.sp))

                        Spacer(modifier = Modifier.size(15.dp))

                        Text(
                            text = price,
                            fontSize = 20.sp,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                shape = RoundedCornerShape(
                                    topStart = 30.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 30.dp,
                                    bottomEnd = 0.dp
                                ),
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Text("Hot")
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                                shape = RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 30.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 30.dp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Iced")
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .background(Color.LightGray)
                                .fillMaxWidth()
                        ) {
                            Text(text = tmi2, fontSize = 13.sp)
                        }

                        Button(
                            onClick = {
                                val intent = Intent(context, OrderDonePage::class.java)
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009900)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "주문하기")
                        }

                    }
                }
            }
        }
    }
}


@Composable
private fun MoreInfo(engname: String): String {
    var tmi = ""

    when (engname) {
        "Iced Caffe Americano" -> tmi =
            "진한 에스프레소에 시원한 정수물과 얼음을 더하여 스타벅스의 깔끔하고 강렬한 에스프레소를 가장 부드럽고 시원하게 즐길 수 있는 커피"

        "Iced Caffe Latte" -> tmi = "풍부하고 진한 농도의 에스프레소가 시원한 우유와 얼음을 만나 고소함과 시원함을 즐길 수 있는 대표적인 커피 라떼"
        "Sea Salt Caramel Cold Brew" -> tmi = "구름 처럼 부드러운 씨솔트 폼과 번트 카라멜의\n" +
                "중독성 강한 단짠단짠 조합의 콜드 브루\n" +
                "한번 맛보면 자꾸 생각나는 매력적인 음료"

        "Iced Grapefruit Honey Black Tea" -> tmi =
            "새콤한 자몽과 달콤한 꿀이 깊고 그윽한 풍미의 스타벅스 티바나 블랙 티의 조화.\n" +
                    "한정된 기간 동안 판매하는 트렌타 사이즈로 즐겨 보세요."

        "Cold Brew" -> tmi = "스타벅스 바리스타의 정성으로 탄생한 콜드 브루!\n" +
                "콜드 브루 전용 원두를 차가운 물로 추출하여 한정된 양만 제공됩니다.\n" +
                "실크같이 부드럽고 그윽한 초콜릿 풍미의 콜드 브루를 만나보세요!\n" +
                "한정된 기간 동안 판매하는 트렌타 사이즈로 즐겨 보세요."

        "Caffe Mocha" -> tmi =
            "진한 초콜릿 모카 시럽과 풍부한 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료로 진한 에스프레소와 초콜릿 맛이 어우러진 커피"

        "Lavender Cafe Breve" -> tmi =
            "진한 리저브 에스프레소 샷과 은은한 라벤더향이 고급스럽게 어우러진 부드럽고 세련된 풍미의 라벤더 카페 브레베"

        "The Green Mugwart Cream Latte" -> tmi = "은은한 쑥과 곡물에 블론드 샷이 어우러져 고소하고 부드러운 라떼\n" +
                "달콤한 쑥 폼이 올라가 부드럽게 즐기는 아인슈페너 음료\n" +
                "*더북한산,더양평DTR,더북한강R,경동1960,대구종로고택 매장에서만 판매하는 음료입니다."
    }
    return tmi
}

@Composable
private fun OrderInfo(engname: String): String {
    var tmi2 = ""

    when (engname) {
        "Iced Caffe Americano" -> tmi2 = "여기 뭐들어가는지 진짜모룸\n" + "\n" + "\n" + "\n"

        "Iced Caffe Latte" -> tmi2 = "여기 뭐들어가는지 진짜모룸2\n" + "\n" + "\n" + "\n"

        "Sea Salt Caramel Cold Brew" -> tmi2 = "여기 뭐들어가는지 진짜모룸3\n" + "\n" + "\n" + "\n"

        "Iced Grapefruit Honey Black Tea" -> tmi2 = "여기 뭐들어가는지 진짜모룸4\n" + "\n" + "\n" + "\n"

        "Cold Brew" -> tmi2 = "알레르기 유발요인 : 우유\n" + "\n" + "\n" + "\n"

        "Caffe Mocha" -> tmi2 = "알레르기 유발요인 : 우유\n" + "\n" + "\n" + "\n"

        "Lavender Cafe Breve" -> tmi2 = "알레르기 유발요인 : 우유\n" + "\n" + "\n" + "\n"

        "The Green Mugwart Cream Latte" -> tmi2 = "알레르기 유발요인 : 대두 / 우유\n" + "\n" + "\n" + "\n"
    }
    return tmi2
}


