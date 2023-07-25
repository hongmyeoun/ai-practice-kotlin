import java.util.Stack

//input의 i부터 순회하다가
//i번째의 요소가 ' '이면
//스택이 비어있지 않는 상태에서 answer에 stack.pop()을 더한다.

//input으로 String을 받고, String 타입을 반환한다.
fun reverseWord(input: String): String {
    //string타입의 빈 변수인 answer를 선언
    var answer = ""
    //char타입의 비어있는 stack stack 선언
    val stack = Stack<Char>()
    //input을 순회 하면서
    for (i in input) {
        //input의 i번째 요소를 stack에 push한다.
        stack.push(i)
        //만약 i번째 요소가 ' '이라면
        if (i == ' ') {
            //stack이 비어있지 않을때까지
            while (!stack.isEmpty()) {
                //answer에 answer와 stack.pop()을 더한다.
                answer += stack.pop()
            }
        }
    }
    //' '이 없는 마지막 단어에 도달했을때 answer에 공백을 더한다
    answer += " "
    //stack이 빌때까지
    while (!stack.isEmpty()) {
        //answer에 answer와stack.pop()을 더한다
        answer += stack.pop()
    }
    //answer를 반환한다.
    return answer
}

reverseWord("hi hellow bye")
reverseWord("easy")
