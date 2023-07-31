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

//이런식으로 리스트들을 병합
//마지막 list를 출력


val list = mutableListOf<Int>(10, 4, 2, 1, 7, 3, 13, 5)
//list를 2로 나눈값까지 새 리스트에 저장
fun listdivider(input: List<Int>): List<Int>{
    if (input.size < 2) {
        return input
    }
    val mid = input.size / 2
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    for (i in 0 until input.size) {
        if (i < mid) {
            //1. 10,4,2,1
            leftList.add(input[i])
        } else {
            //1. 7,3,13,5
            rightList.add(input[i])
        }
    }
    //1. 10,4,2,1
    val leftDivided = leftList
    //1. 7,3,13,5
    val rightDivided = rightList
    //한번 나눈 list를 병합
    println("$leftDivided $rightDivided")
    return merge(leftDivided, rightDivided)
}
println(listdivider(list))
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

//println(merge(listOf(10,4),listOf(2,1)))
fun merge(left: List<Int>, right: List<Int>): List<Int> {
    //왼쪽의 0번째와 오른쪽의 0번째 크기비교
    //작은쪽이 새로운 정렬된 리스트에 먼저 저장됨
    //2개 2개일때
    //왼쪽 0번과 오른쪽 0번 비교
    //왼쪽이 더 작으면 왼쪽을 add
    //그리고 왼쪽1번과 오른쪽 0번비교
    //오른쪽이 더 작으면 오른쪽0번 add
    //그리고 왼쪽 1번과 오른쪽 1번 비교
    //왼쪽1번이 더 작으면 왼쪽1번 add, 오른쪽1번 add

    //index는 0부터 왼쪽,오른쪽 둘다
    //index는 0부터 2까지
    //왼쪽[index]<오른쪽[index] == true
    //new.add(왼쪽[index]), index++
    //왼쪽[index]<오른쪽[index] == false
    //new.add(오른쪽[index]), index++
    val mergeSortList = mutableListOf<Int>()
    var leftIndex = 0
    var rightIndex = 0

    //left[10,4,2,1] right[7,3,13,5]

    while (leftIndex < left.size && rightIndex < right.size){
        if(left[leftIndex] < right[rightIndex]){
            mergeSortList.add(left[leftIndex])
            leftIndex++
        }else{
            mergeSortList.add(right[rightIndex])
            rightIndex++
        }
    }

    while (leftIndex < left.size) {
        mergeSortList.add(left[leftIndex])
        leftIndex++
    }

    while (rightIndex < right.size) {
        mergeSortList.add(right[rightIndex])
        rightIndex++
    }

    return mergeSortList
}
