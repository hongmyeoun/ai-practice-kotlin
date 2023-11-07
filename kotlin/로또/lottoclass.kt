package lotto

//class Lotto(private val numbers: List<Int>) {
//    init {
//        require(numbers.size == 6)
//    }
//
//    // TODO: 추가 기능 구현
//}


class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6)
        require(numbers.toSet().size == numbers.size)
        require(numbers.all { it in 1..45 })
    }
    //, lottoNumbers: List<Int>
    fun lottoLogic(lottoList: MutableList<List<Int>>, price: Int) {
        val lottoResult = LottoResult()

        for (lotto in lottoList) {
            val tempLotto = lotto.subList(0, 6)
            val count = tempLotto.count { it in numbers }

            if (count == 5 && lotto.last() == numbers.last()) {
                lottoResult.fiveBonusContains.add(lotto)
            } else {
                classifyLottoResults(lotto, count, lottoResult)
            }
        }

        printResult(lottoResult, getPercentBenefit(price, lottoResult))

    }

    private fun classifyLottoResults(lotto: List<Int>, count: Int, lottoResult: LottoResult) {
        when (count) {
            3 -> lottoResult.threeContains.add(lotto)
            4 -> lottoResult.fourContains.add(lotto)
            5 -> lottoResult.fiveContains.add(lotto)
            6 -> lottoResult.sixContains.add(lotto)
        }
    }

    private fun getPercentBenefit(price: Int, lottoResult: LottoResult): Float {
        val totalBenefit = lottoResult.threeContains.count() * 5000f + lottoResult.fourContains.count() * 50000f + lottoResult.fiveContains.count() * 1500000f + lottoResult.fiveBonusContains.count() * 30000000f + lottoResult.sixContains.count() * 2000000000f
        return (totalBenefit / price) * 100f
    }

    private fun printResult(lottoResult: LottoResult, percentBenefit: Float){
        println("\n당첨 통계\n---")
        println("3개 일치 (5,000원) - ${lottoResult.threeContains.count()}개")
        println("4개 일치 (50,000원) - ${lottoResult.fourContains.count()}개")
        println("5개 일치 (1,500,000원) - ${lottoResult.fiveContains.count()}개")
        println("5개 일치, 보너스 볼 일치 (30,000,000원) - ${lottoResult.fiveBonusContains.count()}개")
        println("6개 일치 (2,000,000,000원) - ${lottoResult.sixContains.count()}개")
        print("총 수익률은 ${percentBenefit}%입니다.")
    }


}

data class LottoResult(
        val threeContains: MutableList<Any> = mutableListOf(),
        val fourContains: MutableList<Any> = mutableListOf(),
        val fiveContains: MutableList<Any> = mutableListOf(),
        val fiveBonusContains: MutableList<Any> = mutableListOf(),
        val sixContains: MutableList<Any> = mutableListOf(),
)
