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
        for (indexNum in 0 until input.size) {
            val temp = input[indexNum]

            if (indexNum > 0 && input[indexNum] < input[indexNum - 1]) {
                input[indexNum] = input[indexNum - 1]
                input[indexNum - 1] = temp
            }
//            println(input.contentToString())

        }

    }
    println(input.contentToString())
}

upbubble(arrayOf(7, 2, 8, 10, 3, 1, -3, 4))

fun downbubble(input: Array<Int>) {
    for (i in 0 until input.size) {
        for (indexNum in 0 until input.size) {
            val temp = input[indexNum]

            if (indexNum > 0 && input[indexNum] > input[indexNum - 1]) {
                input[indexNum] = input[indexNum - 1]
                input[indexNum - 1] = temp
            }
//            println(input.contentToString())

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
[10, 8, 7, 3, 4, 2, 1, -3]
[10, 8, 7, 3, 4, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3] == 여기?서 끝나야 됨
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
[10, 8, 7, 4, 3, 2, 1, -3]
*/
