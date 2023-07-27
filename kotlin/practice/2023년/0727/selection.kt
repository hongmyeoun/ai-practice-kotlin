//selection
//1번째 인덱스부터 마지막 인덱스까지 최소값을 찾아냄
//최소값과 0번째 인덱스를 비교한다.
//만약 최소값이 더 작다면 0번째 인덱스와 최소값의 위치를 바꾼다.
//2번째 인덱스부터 마지막 인덱스까지 최소값을 찾아냄
//최소값과 1번쨰 인덱스를 비교
//만약 최소값이 더 작다마녀 1번쨰 인덱스와 최소값의 위치를 바꿈
//...
//list.size-2번째 인덱스 부터 마지막 인덱스 까지 최소값을 찾아낸다
//만약 최소값이 더 작다면 list.size-2번째 인덱스와 최소값의 위치를 바꾼다.
//list[i] -> for(i in 1 until list.size)
//최소값을 찾아내는 것. list 메소드를 이용하자
val list = mutableListOf<Int>(10, 4, 2, 1, 7)

selection(list)
fun selection(input: MutableList<Int>) {

    for (i in 0 until input.size) {
        var minIndex = 0
        if (input[i] < input[minIndex]) {
            minIndex = i
        }

    }
//        for (j in 0 until input.size) {
//            if (j > 0 && minIndex < input[j]) {
//                input[j] = minIndex
//            }
//        }
//
//    println(input)
}

////list에서 1번째 인덱스 부터 마지막 인덱스 에서
//var minIndex = 0
//for (i in 0 until list.size) {
//    if (list[i] < list[minIndex]) {
//        minIndex = i // 현재 요소가 최소값보다 작다면 최소값의 인덱스를 업데이트
//    }
//}
