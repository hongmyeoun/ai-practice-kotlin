fun check369(n: Int) {
    //n을 입력받음
    //n에 3,6,9가 있는지 판단
    //n을 3, 6, 9로 나누고 나머지가 0인지 확인 -> 0이면 짝 출력 아니면 n 출력
//    if (n % 3 == 0 || n % 6 == 0 || n % 9 == 0) {
//        println("짝")
//    } else {
//        println(n)
//    }
    //n이 10이상일때 10으로 나누고 나머지를 3, 6, 9로 나누고 나머지가 0인지 확인 -> 0이면 짝 출력 아니면 n 출력
    if (n in 0..9) {
        if (n % 3 == 0 || n % 6 == 0 || n % 9 == 0) {
            println("짝")
        } else {
            println(n)
        }
    } else if (n >= 10) {
        if ((n % 10) == 3 || (n % 10) == 6 || (n % 10) == 9) {
            println("짝")
        } else {
            println(n)
        }
    }
}
//10으로 나눈 몫이 3,6,9인지 확인 ->귀찮게 10으로 나누지말고 10으로 나눈 몫을 위에서 한자리 수일때 했던 방식으로 판별
check369second(30)

//n값을 입력하고 반환값은 리스트인 함수
//소인수분해 == 소수(더이상 본인이외에 나누어지지 않는수)의 곱
//입력받은 n값을 2로 나눈다. 나누어서 나머지가 0이면 나눈값을 list에 저장한다.
//나머지가 0이었을 때 몫을 3으로 나눈다. 나누어서 나머지가 0이면 나눈값을 list에 저장한다.
//나머지가 0이었을 때 몫을 4으로 나눈다. 나누어서 나머지가 0이면 나눈값을 list에 저장한다.
//n까지 반복한다.
//리스트를 반환한다.
//input값을 입력하고 반환값은 리스트인 함수
fun primeFactorization(input: Int): List<Int> {
    //반환 시킬 list를 만든다
    val primeFactorList = mutableListOf<Int>()
    //계산을 위해 변환할 수없는 매개변수 input을 num이라는 변수로 만든다
    var num = input
    //2부터 num까지 반복한다.
    for (i in 2..num) {
        //num을 i로 나누어서 나머지가 0이면
        while (num % i == 0) {
            //나눈값을 list에 저장한다
            primeFactorList.add(i)
            //num을 i로 나누어 몫을 num에 저장한다.
            num /= i
        }
    }
    //리스트릴 반환한다.
    return primeFactorList
}
primeFactorization(6)
primeFactorization(60)

//단어반복
//문자열 input의 각 문자를 입력받은 n만큼 반복해서 순서대로 반환하는 함수를 완성하세요.
//문자열 input과 정수형 n을 입력받아 문자열을 반환하는 함수
//output을 빈 String으로 초기화한다.
//문자열의 요소를 첫요소부터 n번 더하고 다음요소를 n번더해 마지막요소까지 반복한다.
//더한 요소값을 output에 저장한다.
//output을 반환한다.
//String 타입으로 input을 받고, Int 타입으로 n을 받아 String을 반환하는 함수
fun repeatAlphabet(input: String, n: Int): String {
    //빈 String타입의 변수 output을 선언한다.
    var output = ""
    //1부터 input의 길이만큼 반복한다
    for (i in 1..input.length) {
        //1부터 n까지 반복한다
        for (j in 1..n) {
            //output에 output과 input의 i-1의 값을 더해서 저장한다.
            output += input[i - 1]
        }
    }
    //output을 반환한다.
    return output
}
repeatAlphabet("hi", 2)
repeatAlphabet("easy", 3)

//두 번째로 큰수
//입력받은 리스트에서 두 번째로 큰 수를 반환하는 함수를 완성하시오
//Int형 list를 입력받아 Int를 반환하는함수
fun second(input: List<Int>): Int {
    //second라는 int형 변수를 선언한다.
    val second: Int
    //input을 변경 가능한 list로 만들 sortedList를 선언한다.
    var sortedList = input
    //입력 받은 list를 내림차순으로 정렬한다.
    sortedList = sortedList.sortedDescending()
    //정렬한 list의 두번째 요소를 second에 저장한다.
    second = sortedList[1]
    //second를 반환한다.
    return second
}
second(listOf(5, 3, 7, 1))
second(listOf(-1, -5, 3, -10))

fun second2(input: List<Int>): Int{
    //input의 0번째 요소값을 갖는 변수 largest와 second를 선언
    var largest = input[0]
    var second = input[0]
    //list 내부에 있는 요소를 비교 for문을 이용 for(i in input) == list의 첫요소부터 순회
    for (i in input){
        //i가 largest보다 클때 second에 largest 저장
        if (i > largest){
            second = largest
            //lagest에는 i값을 저장
            largest = i
        }
        //i가 second보다는 크고 largest보다 작게되면 second에 i를 저장
        else if (i > second && i < largest){
            second = i
        }
    }
    //second를 반환
    return second
}
second2(listOf(5, 3, 7, 1))
second2(listOf(-1, -5, 3, -10))

//두번째로 큰 수를 찾는 함수
//정수형 list를 input으로 받고 정수형을 반환하는 함수
//변수 largeset를 input의 0번째 요소로 지정
//변수 second를 input의 0번째 요소로 지정
//input list 를 0번째 요소부터 순회
//i번째 요소가 largest보다 크면
//second에 가장큰수가 저장
//largest에는 i번째 요소가 저장
//i번째 요소가 largest 작으면
//i번째 요소와 second의 크기를 비교
//i번째 요소가 second보다 크면
//second에 i번째 요소를 저장
//i번째 요소가 second다 작으면
//두번째로 큰수를 반환
//두번째로 큰 수를 찾는 함수
//정수형 list를 input으로 받고 정수형을 반환하는 함수
fun second4(input: List<Int>): Int{
    //변수 largeset를 input의 0번째 요소로 지정
    var largest = input[0]
    //변수 second를 input의 0번째 요소로 지정
    var second = input[0]
    //input list 를 0번째 요소부터 순회
    for(i in input){
        //i번째 요소가 largest보다 크면
        if (i > largest){
            //second에 가장큰수가 저장
            second = largest
            //largest에는 i번째 요소가 저장
            largest = i
        }
        //i번째 요소가 largest 작으면
        //i번째 요소와 second의 크기를 비교
        else if(i > second){
            //i번째 요소가 second보다 크면
            //second에 i번째 요소를 저장
            second = i
        }
        //i번째 요소가 second다 작으면
    }
    //두번째로 큰수를 반환
    return second
}
second4(listOf(2,1,6,4))

//최대 공약수
//정수형input1과 정수형input2를 입력해 최대공약수의 list를 반환
//input1를 소인수분해해서  a list로 만든다
//input2를 소인수분해 해서 b list로 만든다
//input1와 input2의 소인수분해 list의 값중 같은 값들을 새로운 리스트(c)에 넣는다
//새로운 리스트를 반환한다.
//두개의 list에서 같은 값을 찾아 새로운  list를 만드는 계산
//a의 list의 0번째 인수부터 순회하며 b의 list의 0번째 인수부터 비교한다
//비교를 했을때 같은 값이 나오면 새로운 list에 그 값을 추가한다.
//a리스트의 0번째 인수와 b리스트의 0번째 인수부터 마지막 인수까지 비교를 한다
//a리스트의 0번째 인수와 b리스트의 0번째 인수가 같다면, 그 인수값을 c list에 추가한다
//a리스트의 1번째 인수와 b리스트의 1번째 인수부터 마지막 인수까지 비교한다
//a리스트의 1번째 인수와 b리스트의 n번째 인수와 같다면 그 인수값을 c list에 추가를한다
//a리스트의 2번째 인수와 b리스트의 n+1번째 인수부터 마지막 인수까지 비교한다 인수가 같은 값이 나오면 그 인수를 c list에 추가한다
//a리스트의 마지막까지 위의 과정을 반복한다.
//c 리스트의 값을 곱한다.
//c 리스트를 곱한 값을 반환한다.
//input값을 입력하고 반환값은 리스트인 함수
fun calcGCD(input1: Int, input2: Int): Int{
    //반환 시킬 list를 만든다
    val primeFactorList1 = mutableListOf<Int>()
    //계산을 위해 변환할 수없는 매개변수 input을 num이라는 변수로 만든다
    var num1 = input1
    //2부터 num까지 반복한다.
    for (a in 2..num1) {
        //num을 i로 나누어서 나머지가 0이면
        while (num1 % a == 0) {
            //나눈값을 list에 저장한다
            primeFactorList1.add(a)
            //num을 i로 나누어 몫을 num에 저장한다.
            num1 /= a
        }
    }
    val primeFactorList2 = mutableListOf<Int>()
    var num2 = input2
    for (b in 2..num2) {
        while (num2 % b == 0) {
            primeFactorList2.add(b)
            num2 /= b
        }
    }
    val commonDivisor = mutableListOf<Int>()
    var gcd = 1
    var i = 0
    var j = 0
    //a와 b의 리스트 크기보다 i와 j가 작을때 반복
    while (i < primeFactorList1.size && j < primeFactorList2.size) {
        //a의 i번째 요소와 b의 i번째 요소가 같으면
        if (primeFactorList1[i] == primeFactorList2[j]) {
            //c에 a의 i번째 요소를 추가
            commonDivisor.add(primeFactorList1[i])
            //c list의 요소의 곱(product에 prodcut와 a의 i번째 요소를 곱함)
            gcd *= primeFactorList1[i]
            //i 1증가
            i++
            //j 1증가
            j++
            //a의 i번째 요소와 b의 i번째 요소가 같지 않을때
            //a의 i번째 요소의 크기가 b의 j번째 요소의 크기보다 작다면
        } else if (primeFactorList1[i] < primeFactorList2[j]) {
            //i 1증가
            i++
            //a의 i번째 요소의 크기가 b의 j번째 요소의 크기보다 크다면
        } else {
            //j 1증가
            j++
        }
    }
    return gcd
}
calcGCD(8, 6)

//문자열 뒤집기
//문자열을 입력받아 문자열을 반환하는 함수
//문자열을 list로 바꾼다
//answer라는 빈 string 변수를 선언한다.
//list를 순회한다
//순회를 하면서 answer에 순회하는 리스트의 요소와 answer를 더한다.
//answer를 반환한다.
//문자열 뒤집기
//문자열을 입력받아 문자열을 반환하는 함수
fun reverseWord(input: String): String{
    //answer라는 빈 string 변수를 선언한다.
    var answer = ""
    //문자열을 list로 바꾼다
    val inputList = input.toList()
    //list를 순회한다
    for (i in inputList){
        //순회를 하면서 answer에 순회하는 리스트의 요소와 answer를 더한다.
        answer = i + answer
    }
    //answer를 반환한다.
    return answer
}
reverseWord("HelloW")
//문자열 뒤집기
//문자열을 입력받아 문자열을 반환하는 함수
fun reverseWord2(input: String): String{
    //answer라는 빈 string 변수를 선언한다.
    var answer = ""
    //input의 길이는 요소의 번호보다 1이크기때문에 길이-1부터 0까지 역순으로 순회
    for (i in input.length - 1 downTo 0){
        //answer와 input의 i번째 요소를 더함
        answer += input[i]
    }
    //answer를 반환한다.
    return answer
}
reverseWord2("HelloW")


//미완 미완 미완
//피보나치수열 1,1,2,3,5,8,13....
//피보나치 수열의 n번째 수를 찾아내는 함수
//정수타입의 input을 받고 정수타입을 반환하는 함수
//[1,1]인 변경가능한 pibonachiList를 생성
//이 pibonachiList에 pibonachiList.size번째의 요소와 pibonachiList.size-1의 요소의 합을 추가
//추가한 list에 n+1번째 요소를 반환
fun pibonachi(input: Int): Int{
    val pibonachiList = mutableListOf<Int>(1,1)
    var addNum = 0
    addNum = pibonachiList[pibonachiList.size] + pibonachiList[pibonachiList.size-1]
    pibonachiList.add(addNum)
    return pibonachiList[input-1]
}
pibonachi(3)
