package racingcar

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

fun main() {
    println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,)로 구분)")
    val carNames = Console.readLine()
    if (carNames.split(",").any { it.length > 5 }) {
        throw IllegalArgumentException("자동차 이름은 5글자 이하이어야 합니다.")
    }
    val cars = carNames.split(",").map { Car(it) }

    println("시도할 횟수는 몇 회인가요?")
    val distanceToWin = Console.readLine().toInt()
    if (distanceToWin <= 0) {
        throw IllegalArgumentException("시도 횟수는 0보다 큰 정수여야 합니다.")
    }

    race(cars, distanceToWin)
    printWinners(findWinners(cars, distanceToWin))
}

data class Car(val name: String, var position: Int = 0) {
    fun move() {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            position++
        }
    }
}

fun race(cars: List<Car>, distanceToWin: Int) {
    println("\n실행 결과")
    while (cars.none { it.position >= distanceToWin }) {
        cars.forEach { car ->
            car.move()
            println("${car.name} : ${"-".repeat(car.position)}")
        }
        println()
    }
}

fun findWinners(cars: List<Car>, distanceToWin: Int): List<String> {
    val winningPosition = cars.maxOf { it.position }
    return cars.filter { it.position == winningPosition && it.position >= distanceToWin }.map { it.name }
}

fun printWinners(winners: List<String>) {
    if (winners.isNotEmpty()) {
        val winnerText = winners.joinToString(", ")
        println("최종 우승자 : $winnerText")
    }
}
