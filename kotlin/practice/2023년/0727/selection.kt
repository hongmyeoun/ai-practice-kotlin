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
//첫번째 최소값은 1인덱스 부터 비교를 해야됨
//두번째 최소값은 2인덱스부터 비교를 해야됨
//세번째 최소값은 3인덱스부터 비교를 해야됨
//4번째 최소값은 4인덱스부터
//5번쨰는...

val list = mutableListOf<Int>(10, 4, 2, 1, 7)

fun selection(input: MutableList<Int>) {
    var minIndex = 0
    for (currentIndex in 0 until input.size) {
        minIndex = currentIndex
        //j는 0부터 반복 i는 1부터 시작 =>근데 i의 두번째 시작은 2, 3번쨰는 3이여야됨
        //j+1로 시작하면 1, 2, 3 이런식 가능
        //이렇게 하니 밑에 부분에서 오류 이후에 정렬이 되어있다면 바꾸지를 않음
        for (i in currentIndex + 1 until input.size) {
            //이게 최소값이 더 작다면 이부분은 작동을 안함
            //다음 작동에서도 최소값은 바뀌지 않음
            //최소값을 currentIndex 시작
            //이러면 currentIndex의 값을 토대로 최소값을 찾기 때문에
            //순회를 돌다 최소값보다 큰값들이 뒤에 오게 되었을때 멈추지 않고 그들중에서 최소값을 찾음
            if (input[i] < input[minIndex]) {
                minIndex = i
            }
        }
        //이부분의 조건을 바꿔야 될까
        if (input[currentIndex] > input[minIndex]) {
            val temp = input[minIndex]
            input[minIndex] = input[currentIndex]
            input[currentIndex] = temp
        }
    }
    println(input)
}
selection(list)

fun downselection(input: MutableList<Int>) {
    var maxIndex = 0
    for (currentIndex in 0 until input.size) {
        maxIndex = currentIndex
        for (i in currentIndex + 1 until input.size) {
            if (input[i] > input[maxIndex]) {
                maxIndex = i
            }
        }
        if (input[currentIndex] < input[maxIndex]) {
            val temp = input[maxIndex]
            input[maxIndex] = input[currentIndex]
            input[currentIndex] = temp
        }
    }
    println(input)
}
downselection(list)
