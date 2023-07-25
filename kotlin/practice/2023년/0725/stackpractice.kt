//String 타입의 input을 받아 String 타입을 반환하는 함수
//String타입의 변수 answer 선언
//stack이라는 String형 Stack 선언
//input을 i번 순회
//순회를 하는 동안 stack.push를 하면서 문자열 한글자씩 Stack에 저장
//반복이 끝이 나면 stack이 비어있지 않을 때까지 반복
//반복을 하는동안 stack을 pop한 값과 answer를 더한 값을 answer에 대입 
//answer를 반환

//String 타입의 input을 받아 String 타입을 반환하는 함수
fun reverseWord4(input: String): String{
    //String타입의 변수 answer 선언
    var answer = ""
    //stack이라는 Char형 Stack 선언
    val stack = Stack<Char>()
    //input을 i번 순회
    for (i in input){
        //순회를 하는 동안 stack.push를 하면서 문자열 한글자씩 Stack에 저장
        stack.push(i)
    }
    //반복이 끝이 나면 stack이 비어있지 않을 때까지 반복
    while (!stack.isEmpty()){
        //반복을 하는동안 answer에 answer과 stack을 pop 값을 더한 값을 대입
        answer += stack.pop()
    }
    //answer를 반환
    return answer
}

reverseWord4("hi")
reverseWord4("easy")
