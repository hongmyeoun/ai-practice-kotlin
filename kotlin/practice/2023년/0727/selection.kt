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
//최소값과 순환중인 리스트목록을 비교
//최소값이 더 작다면
//최소값을 temp에 저장
//최소값에 input[i]를 저장
//input[i]에 temp값을 저장
//최소값은 한번 쓰이면 없어져야된다. 이부분이 안됨 현재

val list = mutableListOf<Int>(10, 4, 2, 1, 7)

fun selection(input: MutableList<Int>) {
    val sortedList = input
    var minIndex = 0
    println(sortedList)
    for (j in 0 until sortedList.size) {
        
        for (i in 0 until sortedList.size) {
            
            if (sortedList[i] < sortedList[minIndex]) {
                minIndex = i
            }
            println(minIndex)
        }
        if (sortedList[j] > sortedList[minIndex]) {
            val temp = sortedList[minIndex]
            sortedList[minIndex] = sortedList[j]
            sortedList[j] = temp
        }

        println(sortedList)
    }

}
selection(list)
