//삽입정렬
//리스트를 입력을 받는다.
//리스트의 1번째 값과 0번째 값의 크기를 비교한다 -> 리스트의 인덱스를 순회한다.
// -> 인덱스는 0부터 시작하지만 삽입정렬은 1번째와 0번쨰를 비교하는게 가장 먼저 선언되므로
// 1부터 시작, 그리고 list의 길이 - 1 까지 반복한다.
//값의 위치를 변경하는 과정에서 임시로 자료를 저장하는 것을 만들것 == temp
//i는 indexNum 인덱스가 몇번째요소인지를 나타내는 변수로 이름지음
//현재 인덱스값을 임시값(temp)로 설정한다.

//반복이 i가 줄어들어야됨 근데 안줄어 드니깐 버블이됨 i값을 변경가능한 새로운 변수로 만듦

//현 인덱스가 이전 인덱스보다 작다면 반복시작 -> 오름차순 //내림차순은 부등호가 반대
//현 인덱스위치에 이전인덱스 값을 대입
//이전 인덱스위치에 temp값 대입
//인덱스번호를 하나 줄임 반복

 //array를 출력

fun insortion(input: Array<Int>) {
    for (i in 0 until input.size) {

        var indexNum = i
        val temp = input[indexNum]

        while (indexNum > 0 && input[indexNum] < input[indexNum - 1]) {
            input[indexNum] = input[indexNum - 1]
            input[indexNum - 1] = temp
            indexNum--
        }

    }
    println(input.contentToString())
}

insortion(arrayOf(7, 2, 8, 10, 3, 1, -3, 4))

fun diInsortion(input: Array<Int>) {
    for (i in 0 until input.size) {

        var indexNum = i
        val temp = input[indexNum]

        while (indexNum > 0 && input[indexNum] > input[indexNum - 1]) {
            input[indexNum] = input[indexNum - 1]
            input[indexNum - 1] = temp
            indexNum--
        }

    }
    println(input.contentToString())
}
diInsortion(arrayOf(7, 2, 8, 10, 3, 1, -3, 4))

//diInsortion 매커니즘
/*
[7, 2, 8, 10, 3, 1, -3, 4]
[8, 7, 2, 10, 3, 1, -3, 4]
[10, 8, 7, 2, 3, 1, -3, 4]
[10, 8, 7, 3, 2, 1, -3, 4]
[10, 8, 7, 3, 2, 1, -3, 4]
[10, 8, 7, 3, 2, 1, -3, 4]
[10, 8, 7, 4, 3, 2, 1, -3]
*/
