class MyStack(){
    var stack = mutableListOf<Char>()
    fun push(input: Char): MutableList<Char> {
        stack.add(input)
        return stack
    }
    fun pop(): Char{
        val lastElement = stack.removeAt(stack.size - 1)
        return lastElement
    }
    fun isEmpty(): Boolean{
        return stack.size == 0
    }
}
//stackClass의 내용을 이어받음
//input의 i부터 순회하다가
//i번째의 요소가 ' '이면
//스택이 비어있지 않는 상태에서 answer에 stack.pop()을 더한다.
//마지막 공백이 후의 단어는 이전에 단어를 뒤집었던 방식을 사용한다. 그전에 answer에 공백을 더한다.
//answer를 반환

//input으로 String을 받고, String 타입을 반환한다.
fun reverseWord(input: String): String {
    //string타입의 빈 변수인 answer를 선언
    var answer = ""
    //char타입의 비어있는 stack stack 선언
    val stack = MyStack()
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

reverseWord("hi hellow bye") // 결과: " ih wolleh eyb" == 공백까지 뒤집어서 첫 글자가 공백임

//단어를 입력받아 뒤집기(list의 입력)
//stackClass의 내용을 이어받음
//input으로 String타입의 단어들을 list로 받음
//비어있는 String answer변수 선언
//stack이라는 MyStack 호출
//input의 String타입의 요소부터 순회하면서
//String타입을 char단위로 순회
//이때 stack에 char를 push
//stack이 비어있지 않다면
//answer에 answer + stack.pop()을 대입
//여기까지가 한 단어를 뒤집은 것이고 끝났으니 answer에 공백을 더함
//answer 반환
fun reverseWord2(input: List<String>): String {
    var answer = ""
    val stack = MyStack()
    //리스트의 요소를 순회
    for (string in input) {
        //순회하는 요소(String)을 순회
        for (char in string) {
            stack.push(char)
        }
        while (!stack.isEmpty()) {
            answer += stack.pop()
        }
        answer += " "
    }
    return answer
}

reverseWord2(listOf("hi","hellow","bye"))// 결과: "ih wolleh eyb " == 단어가 끝나면 공백을 주기때문에 마지막 단어 뒤에 공백
