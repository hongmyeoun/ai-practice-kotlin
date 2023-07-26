//Queue연습
//MyQueue
//poll()과 peek를 만들기, add도 만들기
//poll Queue에 저장되어 있는 값중 가장 앞의 값을 빼내어 반환
//Queue에 저장되어 있는 값중 가장 앞의 값의 정의
//가장 앞의 값은 list의 index가 0인 부분
//list[0]값을 remove하고 그 값을 firstElement라는 값에 저장
//firstElement를 반환
//peek 가장 앞의 있는 값을 확인시켜줌
//그냥 Queue에 가장앞의 인덱스값을 반환
//list[0]
//list에 가장 뒤에 String 요소를 추가하는 것 add?
//클래스로 구현한 queue
class MyQueue() {
    val queue = mutableListOf<String>()
    fun poll(): String {
        val firstElement = queue.remove(queue[0])
        return firstElement.toString()
    }

    //    fun peek(): String{
//        return queue[0]
//    }
    fun add(input: String) {
        queue.add(input)
    }
}

fun mixAlphabet(input: Int): String {
    val queue = MyQueue()
    queue.add("a")
    queue.add("b")
    queue.add("c")
    queue.add("d")
    queue.add("e")
    queue.add("f")
    queue.add("g")
    queue.add("h")
    queue.add("i")
    queue.add("j")
    queue.add("k")
    queue.add("l")
    queue.add("m")
    queue.add("n")
    queue.add("o")
    queue.add("p")
    queue.add("q")
    queue.add("r")
    queue.add("s")
    queue.add("t")
    queue.add("u")
    queue.add("v")
    queue.add("w")
    queue.add("x")
    queue.add("y")
    queue.add("z")

    for (i in 1..input) {
        queue.poll()
        queue.add(queue.poll())
    }
//  return queue.peek()
    return queue.queue[0]
}
mixAlphabet(1)
mixAlphabet(3)
