import java.util.LinkedList
import java.util.Queue

//min heap
//가장 마지막에 들어온 값은 가장 마지막 인덱스에 들어간다
//값을 제거할 경우엔 가장 앞으 인덱스가 나간다.
//마지막 인덱스가 들어오면 그 인덱스 값으 /2한 인덱스와 크기를 비교한다
//만약 작다면 둘의 위치를 바꾼다.
//가장 작은 값의 인덱스가 1일때 까지 반복한다.

val testlint = mutableListOf<Int>(7, 4, 1, 12, 9, 3)
var tmp = 0

println(testlint)
//index번호가 짝수이면 i%2 == 1
//i와 (i-1)/2의 번째의 index값을 비교
//i쪽이 작다면
//i와 (i-1)/2의 위치를 바꿈
//홀수이면 else
//i와 i/2의 번째의 index값을 비교
//i쪽이 작다면
//i와 i/2의 위치를 바꿈

for (i in testlint.size - 1 downTo 1) {
    if (i % 2 == 1) {
        if (testlint[i] < testlint[i / 2]) {
            tmp = testlint[i]
            testlint[i] = testlint[(i - 1) / 2]
            testlint[(i - 1) / 2] = tmp
            tmp = 0
        } else {
            if (testlint[i / 2] < testlint[0]) {
                tmp = testlint[i / 2]
                testlint[i / 2] = testlint[0]
                testlint[0] = tmp
                tmp = 0
            }
        }
    } else {
        if (testlint[i] < testlint[i / 2]) {
            tmp = testlint[i]
            testlint[i] = testlint[i / 2]
            testlint[i / 2] = tmp
            tmp
        }
    }
}
println(testlint)
//val queue: Queue<Int> = LinkedList()
//for (i in testlint){
//    queue.add(i)
//}
//println(queue)
//if(queue.elementAt(queue.size-1) < queue.elementAt((queue.size-1)/2)){
//    //3과 1을 비교 이건 거짓 자리 안바꿈
//    if (queue.elementAt((queue.size-1)/2) < queue.elementAt((queue.size-1)/4)){
//        //1과 7을 비교 이건 자리 바꿈
//        //7의 인덱스 값을 저장 tmp에
//
//        //1의 인덱스 값을 7에 인덱스 값에 대입
//        //tmp를 1에 대입
//    }
//}
