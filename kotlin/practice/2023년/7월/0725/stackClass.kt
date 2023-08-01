//MyStack Class, MyStack.push()와 MyStack.pop()의 함수를 포함한다.
//stack이라는 변경가능한 비어있는 list를 선언
//push 함수: stack에 input으로 받은 list.add를 이용해 list에 추가를 해줌
//push(input: Char)는 Char타입을 입력받는다.
//입력받은 char타입 값을 list에 추가한다.
//list를 반환한다.
//pop 함수: stack에 size-1의 인덱스를 지우고, 그 인덱스를 반환한다.
//stack에서 stack.size-1의 인덱스를 지움 그값을 lastElement에 저장
//lastElement를 반환
//isEmpty 함수: stack list의 size가 0인지 아닌지 판별하는 함수
//stack.size == 0 ture를 반환


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
var testStack = MyStack()
val testList = listOf('i','j','k','w')
println(testStack.stack)
for (element in testList){
    testStack.push(element)
}
println(testStack.stack)
while (!testStack.isEmpty()){
    testStack.pop()
    println(testStack.stack)
}
println(testStack.stack)
