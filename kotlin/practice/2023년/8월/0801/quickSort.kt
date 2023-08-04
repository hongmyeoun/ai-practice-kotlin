fun main() {
    println(quickDivide(mutableListOf<Int>(8, 4, 7, 5, 10, 6)))
}

fun quickDivide(input: List<Int>): List<Int> {
    if (input.size < 2) {
        return input
    }

    //if문 밑으로 위치가 바뀜
    val pivotIndex = input.size / 2
    val pivotValue = input[pivotIndex]
    
    val left = mutableListOf<Int>()
    val pivot = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    for (index in 0 until input.size) {
        //index == pivotIndex 추가
        if (index == pivotIndex) {
            pivot.add(input[index])
        } else if (input[index] < pivotValue) {
            left.add(input[index])
        } else {
            right.add(input[index])
        }
    }

    val dividedLeft = quickDivide(left)
    val dividedRight = quickDivide(right)

    return merge(dividedLeft, pivot, dividedRight)
}

fun merge(left: List<Int>, pivot: List<Int>, right: List<Int>): List<Int> {
    val mergeList = mutableListOf<Int>()

    mergeList.addAll(left)
    mergeList.addAll(pivot)
    mergeList.addAll(right)

    return mergeList
}
