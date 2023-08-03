//퀵정렬
//과정
//기준점 (pivot)을 정해 기준점의 값보다 작은 값은 left array, 큰 값은 right array에 저장한다.
//각 left, right array에 대해서는 재귀함수를 통해 같은 동작을 반복하여 정렬한다.
//최종적으로 left, pivot, right 순으로 차례로 병합하여 정렬된 array를 리턴한다.
//⇒ 기준점을 잡고, 기준점보다 값이 작으면 왼쪽, 값이 크면 오른쪽으로 넣는다.
//ex) {8, 4, 7, 3, 1, 6}이 있다면, index/2로 6개의 요소가 있으므로 3번째 요소인 [7]을 기준으로 잡는다
//8, 4, [7], 3, 1, 6
//left=[4,3,1,6] right=[8]
//4, [3], 1,6
//left=[1] right=[4,6]
//[4],6
//left=[] right=[6]

//left=[1] + pivot=[3] + right=[4,6]
//[1] [3] [4,6]
//left[1,3,4,6] + pivot=[7] + right=[8]
//[1,3,4,6] [7] [8]

//list를 pivot을 정해서 왼쪽으로는 pivot보다 작은수들의 리스트, 오른쪽은 pivot보다 큰수의 리스트로 쪼개기
//pivot을 index/2로 잡는다


val list = mutableListOf<Int>(8, 4, 7, 3, 1, 6)
quickDivide(list)
fun quickDivide(input: List<Int>): List<Int> {
    val pivotIndex = input.size / 2
    val left = mutableListOf<Int>()
    val pivot = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    if (input.size < 2) {
        return input
    }

    //list의 pivotIndex의 있는 값보다 크기가 작은 값들이 나오면
    //left 리스트에 add
    //아니면 right에 add
    for (index in 0 until input.size) {
        if (input[index] < input[pivotIndex]) {
            left.add(input[index])
        } else {
            right.add(input[index])
        }
    }
    pivot.add(input[pivotIndex])

    val dividedLeft = quickDivide(left)
    val dividedRight = quickDivide(right)

    return merge(dividedLeft, pivot, dividedRight)
}

fun merge(left: List<Int>, pivot: List<Int>, right: List<Int>): List<Int> {
    //left + pivot + right 이렇게 반복
    val mergeList = mutableListOf<Int>()

    for (index in 0 until left.size) {
        mergeList.add(left[index])
    }
    for (index in 0 until pivot.size) {
        mergeList.add(pivot[index])
    }
    for (index in 0 until right.size) {
        mergeList.add(right[index])
    }

    return mergeList
}

