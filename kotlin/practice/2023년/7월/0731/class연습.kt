class Cafe(name: String, address: String, monthTax: Int, private val coffee: Coffee) {
    private val cafename = name
    private val cafeaddress = address
    private val monthtax = monthTax

    fun cafeName() {
        println("카페 이름 : $cafename")
    }

    fun cafeAddress() {
        println("카페 주소 : $cafeaddress")
    }

    fun monthTaxMoney() {
        println("월세 : $monthtax")
    }

    fun cafeMenu() {
        println("메뉴로는 Coffe와 Tea 종류가 있습니다.")
    }

    fun gain() {
        println("${coffee.name} ${(monthtax / coffee.price) + 1}잔 팔아야 월세를 냅니다 ㅠㅠ")
    }

    fun cafeIntro() {
        cafeName()
        cafeAddress()
        monthTaxMoney()
        cafeMenu()
        gain()
    }
}

val coffee1 = Americano("아메리카노", 1600, "아메리카노")
val coffee2 = CafeLatte("카페라떼", 3600, "카페라떼")
coffee1.coffeeMenu()
coffee2.coffeeMenu()


val tea1 = GreenTea("그린티", 3400, "초록색")
val tea4 = IceTea("아이스티", 2400, "복숭아")
tea1.teaMenu()
tea4.teaMenu()

val cafe1 = Cafe("메머드", "학원앞", 2000000, coffee1)
cafe1.cafeIntro()

val cafe2 = Cafe("메머드", "학원앞", 2000000, coffee2)
cafe2.cafeIntro()


open class Coffee(cfname: String, cfprice: Int, cfingredient: String) {
    var name = cfname
    var price = cfprice
    var ingredient = cfingredient
    open fun coffeeMenu() {
        println("${ingredient}를 사용해 만든 ${name}(${price}원)")
    }
}

class Americano(name: String, price: Int, ingredient: String) : Coffee(name, price, ingredient)

class CafeLatte(name: String, price: Int, ingredient: String) : Coffee(name, price, ingredient)

open class Tea2(tname: String, tprice: Int, tingredient: String) {
    var name = tname
    var price = tprice
    var ingredient = tingredient
    open fun teaMenu() {
        println("${ingredient}를 사용해 만든 ${name}(${price}원)")
    }
}

class GreenTea(name: String, price: Int, ingredient: String) : Tea2(name, price, ingredient) {
    override fun teaMenu() {
        println("${ingredient}을 사용해 만든 ${name}(${price}원)")
    }
}

class IceTea(name: String, price: Int, ingredient: String) : Tea2(name, price, ingredient)


//open class Coffee() {
//    var coffeeName = ""
//    var coffeeprice = 0
//    var coffeeingredient = ""
//    open fun coffeeMenu() {
//        println("${coffeeingredient}(을)를 사용해 만든 ${coffeeName}(${coffeeprice}원)")
//    }
//}
//
//class Americano(price: Int, ingredient: String) : Coffee() {
//    init {
//        super.coffeeName = "아메리카노"
//        super.coffeeprice = price
//        super.coffeeingredient = ingredient
//    }
//
//    override fun coffeeMenu() {
//        println("${coffeeingredient}를 사용해 만든 ${coffeeName}(${coffeeprice}원)")
//    }
//}
//
//val coffee1 = Americano(1600, "원두")
//coffee1.coffeeMenu()
//
//class CafeLatte(price: Int, ingredient: String) : Coffee() {
//    init {
//        super.coffeeName = "카페라떼"
//        super.coffeeprice = price
//        super.coffeeingredient = ingredient
//    }
//
//    override fun coffeeMenu() {
//        println("${coffeeingredient}를 사용해 만든 ${coffeeName}(${coffeeprice}원)")
//    }
//}
//
//val coffee2 = CafeLatte(3600, "우유")
//coffee2.coffeeMenu()
//
//open class Tea() {
//    var teaName = ""
//    var teaprice = 0
//    var teaingredient = ""
//    open fun teaMenu() {
//        println("${teaingredient}(을)를 사용해 만든 ${teaName}(${teaprice}원)")
//    }
//}
//
//class GreenTea(price: Int, ingredient: String) : Tea() {
//    init {
//        super.teaName = "그린티"
//        super.teaprice = price
//        super.teaingredient = ingredient
//    }
//
//    override fun teaMenu() {
//        println("${teaingredient}을 사용해 만든 ${teaName}(${teaprice}원)")
//    }
//}
//
//val tea1 = GreenTea(3400, "초록색")
//tea1.teaMenu()
//
//class IceTea(price: Int, ingredient: String) : Tea() {
//    init {
//        super.teaName = "아이스티"
//        super.teaprice = price
//        super.teaingredient = ingredient
//    }
//
//    override fun teaMenu() {
//        println("${teaingredient}를 사용해 만든 ${teaName}(${teaprice}원)")
//    }
//}
//
//val tea2 = IceTea(2400, "복숭아")
//tea2.teaMenu()
