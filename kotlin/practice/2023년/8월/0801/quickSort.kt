// 코드를 살펴보니 두 가지 문제가 있습니다. 
// 하나는 pivot을 중복해서 추가하는 부분에서 발생하고, 
// 다른 하나는 재귀 호출을 할 때 pivot을 넣어주지 않는 부분에서 발생합니다. 
// 아래에서 두 문제를 해결한 수정된 코드를 보여드리겠습니다.

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

// 주요 변경사항:

// pivot 값을 따로 저장하고, pivot 중복 추가 문제를 해결하였습니다.
// pivot 값은 merge 함수에서도 추가되도록 수정하였습니다.
// pivot을 제대로 분리하여 재귀 호출할 때 같이 넘겨주도록 수정하였습니다.
// 위 수정 사항으로 코드가 잘 작동하고 overflow 문제도 해결되어야 합니다.
