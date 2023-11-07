package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

fun main() {
//    TODO("프로그램 구현")

    var validInput = false
    var price = 0

    while (!validInput) {
        try {
            println("구입금액을 입력해 주세요.")

            val inputPrice = Console.readLine()

            price = inputPrice.toInt()

            if (price % 1000 != 0) {
                throw IllegalArgumentException("[ERROR] 구매 금액은 1,000원 단위 입니다!!")
            }
            validInput = true
        } catch (e: NumberFormatException) {
            println("[ERROR] 숫자를 입력하세요!!")
        } catch (e: IllegalArgumentException) {
            println("[ERROR] 구매 금액은 1,000원 단위 입니다!!")
        }
    }

    val totalLottoCount = price / 1000
    println("\n${totalLottoCount}개를 구매했습니다.")

    val lottoList = mutableListOf<List<Int>>()
    for (i in 0 until totalLottoCount) {
        val lottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 7).sorted()
        lottoList.add(lottoNumber)

        val printLottoNumber = lottoNumber.subList(0, 6)
        println(printLottoNumber)
    }

    var valideInput2 = false
    var lottoNumbers: List<Int> = emptyList()

    while (!valideInput2) {

        try {
            println("\n당첨번호를 입력해 주세요.")
            val lottoNumberInput = Console.readLine().split(',')
            lottoNumbers = lottoNumberInput.map { it.toInt() }
            if (lottoNumbers.size != lottoNumbers.toSet().size) {
                throw IllegalArgumentException("중복 숫자 오류")
            }
            if (lottoNumbers.size != 6) {
                throw IllegalArgumentException("입력 개수 오류")
            }
            for (lotto in lottoNumbers) {
                if (lotto !in 1..45) {
                    throw IllegalArgumentException("입력 범위 오류")
                }
            }
            valideInput2 = true

        } catch (e: NumberFormatException) {
            println("[ERROR] 숫자를 써주세요!!")
        } catch (e: IllegalArgumentException) {
            when (e.message) {
                "중복 숫자 오류" -> println("[ERROR] 중복된 숫자를 쓰지마세요")
                "입력 개수 오류" -> println("[ERROR] 입력 번호는 6개만 써주세요")
                "입력 범위 오류" -> println("[ERROR] 1 ~ 45 사이에 숫자만 써주세요")
            }

        }
    }

    lottoNumbers = lottoNumbers.sorted().toMutableList()
    println(lottoNumbers)

    val lotto = Lotto(lottoNumbers)

    var validInput3 = false
    var bonusNumber = 0

    while (!validInput3) {
        try {
            println("\n보너스 번호를 입력해 주세요.")

            val bonusNumberInput = Console.readLine()

            bonusNumber = bonusNumberInput.toInt()

            if (bonusNumber !in 1..45) {
                throw IllegalArgumentException("입력 범위 오류")
            }
            lottoNumbers.add(bonusNumber)
            if (lottoNumbers.size != lottoNumbers.toSet().size) {
                throw IllegalArgumentException("중복 숫자 오류")
            }
            validInput3 = true
        } catch (e: NumberFormatException) {
            println("[ERROR] 숫자는 하나만 써주세요!!")
        } catch (e: IllegalArgumentException){
            when (e.message){
                "중복 숫자 오류" -> println("[ERROR] 보너스가 중복입니다")
                "입력 범위 오류" -> println("[ERROR] 1 ~ 45 사이에 숫자만 써주세요")
            }
        }
    }

    println(bonusNumber)

    lotto.lottoLogic(lottoList, price)

}
