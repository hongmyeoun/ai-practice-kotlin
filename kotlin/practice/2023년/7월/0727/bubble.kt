//버블정렬
//1번 인덱스와 0번 인덱스의 크기를 비교
//0번이 더 크다면 1번과 위치를 변경
//2번 인덱스와 1번 인덱스 크기를 비교
//1번이 더 크다면 2번과 위치를 변경
//...
//array.size-1번 인덱스와 array.size-2인덱스의 크기를 비교
//array.size-2가 더 크다면 array.size-1과 위치를 변경
//한번 돌면 가장 큰수가 맨 마지막으로 정렬된다.
//==>for(i in 1 until input.size)

//이 루프를 input.size-1만큼 반복
// ==> 정렬이 다된 이후에도 계속 돌아감 가장 밖의 루프가 끝날 때까지

fun upbubble(input: Array<Int>) {
    for (i in 0 until input.size) {
        //gpt의 도움을 받음 swap이라는 boolean변수로 밑에 있는 if문에서 자료순서가 바뀐 부분이 있을때
        //true로 바꿔 정렬을 다했을때 불필요하게 반복을 하는 작업이 일어나면 break를 하게 만들었다.
        var isSwap = false

        //gpt의 도음을 받음 input.size-i 이부분은 마지막 값은 정렬이 된 상태이기 때문에 볼 필요가 없어서
        for (indexNum in 0 until input.size - i) {

            if (indexNum > 0 && input[indexNum] < input[indexNum - 1]) {
                //gpt의 도움을 받음 temp값은 값을 바꿀때만 생성함
                val temp = input[indexNum]
                input[indexNum] = input[indexNum - 1]
                input[indexNum - 1] = temp
                isSwap = true
            }
//            println(input.contentToString())

        }
        //gpt의 도움을 받음 이렇게 되면 마지막까지 정렬이 되었다면 두번째 for문을 돌지 않기 때문에
        //isSwap이 false그대로 가서 break를 해 정렬탐색을 중지하며 결과가 나온다.
        if (!isSwap) {
            break
        }
    }
    println(input.contentToString())

}

upbubble(arrayOf(7, 2, 8, 10, 3, 1, -3, 4))

fun downbubble(input: Array<Int>) {
    for (i in 0 until input.size) {
        var isSwap = false

        for (indexNum in 0 until input.size - i) {

            if (indexNum > 0 && input[indexNum] > input[indexNum - 1]) {
                val temp = input[indexNum]
                input[indexNum] = input[indexNum - 1]
                input[indexNum - 1] = temp
                isSwap = true
            }
//            println(input.contentToString())

        }

        if (!isSwap) {
            break
        }
    }
    println(input.contentToString())

}

downbubble(arrayOf(7, 2, 8, 10, 3, 1, -3, 4))
/*
[7, 2, 8, 10, 3, 1, -3, 4]
[7, 2, 8, 10, 3, 1, -3, 4]
[7, 8, 2, 10, 3, 1, -3, 4]
[7, 8, 10, 2, 3, 1, -3, 4]
[7, 8, 10, 3, 2, 1, -3, 4]
[7, 8, 10, 3, 2, 1, -3, 4]
[7, 8, 10, 3, 2, 1, -3, 4]
[7, 8, 10, 3, 2, 1, 4, -3]
[7, 8, 10, 3, 2, 1, 4, -3]
[8, 7, 10, 3, 2, 1, 4, -3]
[8, 10, 7, 3, 2, 1, 4, -3]
[8, 10, 7, 3, 2, 1, 4, -3]
[8, 10, 7, 3, 2, 1, 4, -3]
[8, 10, 7, 3, 2, 1, 4, -3]
[8, 10, 7, 3, 2, 4, 1, -3]
[8, 10, 7, 3, 2, 4, 1, -3]
[10, 8, 7, 3, 2, 4, 1, -3]
[10, 8, 7, 3, 2, 4, 1, -3]
[10, 8, 7, 3, 2, 4, 1, -3]
[10, 8, 7, 3, 2, 4, 1, -3]
[10, 8, 7, 3, 4, 2, 1, -3]
[10, 8, 7, 3, 4, 2, 1, -3]
[10, 8, 7, 3, 4, 2, 1, -3]
[10, 8, 7, 3, 4, 2, 1, -3]
[10, 8, 7, 3, 4, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
*/
