//merge 정렬
//받은 list를 더이상 나눌 수 없을 때까지 나눔
//나눔
//리스트를 반으로 나누고 또 반으로 나누고 또 반으로 나눠 한개의 리스트 들이 되게 만듦
//리스트를 어케나눔?
//list.size/2만큼 반복하면서 요소를 뺌 removeAt사용
//그값을 leftlist에 저장
//나머지는 rightlist가됨
//요소가 하나가 될 때 까지 반복
//다 나눴다.
//리스트를 합치기 전에
//0번과 1번의 크기를 비교
//크기순으로 크기가 2인 리스트에 추가
//똑같이 나머지들도 다 반복
//크기가 2인 리스트들 2개의 요소크기비교
//list1의 0번과 list2의 0번비교 작은거 list3에 0번에 저장
//list1의 0번과 list2의 1번비교 작은거 list3에 1번에 저장
//남은거 비교 작은거 list3에 2번에 저장
//남은거 list3에 3번에 저장
//나머지 반복
//이런식으로 리스트들을 병합
//마지막 list를 출력


//fun divideList(input: List<Int>): List<Any> {
//    val test: MutableList<Int> = input as MutableList<Int>
//    if (input.size <= 1) {
//        return listOf(input)
//    }
//    for(i in 0..(input.size-1)/2){
//        val left = test.add(test.removeAt(i))
//    }
//    val right = test
//
//}
val list = mutableListOf<Int>(10, 4, 2, 1, 7, 3, 13, 5)

var mid = list.size / 2
var L = list.subList(0, mid)
var R = list.subList(mid, list.size)

println("$L $R")

var LM = L.size / 2
var LL = L.subList(0, LM)
var LR = L.subList(LM, L.size)

var RM = R.size / 2
var RL = R.subList(0, RM)
var RR = R.subList(RM, R.size)

println("$LL $LR $RL $RR")

var LLM = LL.size / 2
var LLL = LL.subList(0, LLM)
var LLR = LL.subList(LLM, LL.size)

var LRM = LR.size / 2
var LRL = LR.subList(0, LRM)
var LRR = LR.subList(LRM, LL.size)

var RRM = RR.size / 2
var RRL = RR.subList(0, RRM)
var RRR = RR.subList(RRM, RR.size)

var RLM = RL.size / 2
var RLL = RL.subList(0, RLM)
var RLR = RL.subList(RLM, RL.size)

println("$LLL $LLR $LRL $LRR $RLL $RLR $RLL $RLR")

//LLL[0]=10, LLR[0]=4
//10 > 4
val newLL = mutableListOf<Int>()
if (LLL[0] > LLR[0]){
    //[4, 10]
    newLL.add(LLR[0])
    newLL.add(LLL[0])
}else{
    newLL.add(LLL[0])
    newLL.add(LLR[0])
}
println(newLL)

val newLR = mutableListOf<Int>()
if (LRL[0] > LRR[0]){
    newLR.add(LRR[0])
    newLR.add(LRL[0])
}else{
    newLR.add(LRL[0])
    newLR.add(LRR[0])
}
println(newLR)

val newL = mutableListOf<Int>()

println(newL)
//
//if (LL[0] > LR[0]){
//    val temp = LL[0]
//    LL[0] = LR[0]
//    LR[0] = temp
//}else{
//    val temp = LR[0]
//    LR[0] = LL[0]
//    LL[0] = temp
//}
//if (LL[0] > LR[1]){
//    val temp = LL[0]
//    LL[0] = LR[1]
//    LR[1] = temp
//}else{
//    val temp = LR[1]
//    LR[1] = LL[0]
//    LL[0] = temp
//}
//if (LL[1] > LR[0]){
//    val temp = LL[1]
//    LL[1] = LR[0]
//    LR[0] = temp
//}else{
//    val temp = LR[0]
//    LR[0] = LL[1]
//    LL[1] = temp
//}
//if (LL[1] > LR[1]){
//    val temp = LL[1]
//    LL[1] = LR[1]
//    LR[1] = temp
//}else{
//    val temp = LR[1]
//    LR[1] = LL[1]
//    LL[1] = temp
//}
//L = merge(LL,LR) as MutableList<Int>
//println(L)

fun merge(list1: List<Int>, list2: List<Int>): List<Int>{
    return list1 + list2
}
