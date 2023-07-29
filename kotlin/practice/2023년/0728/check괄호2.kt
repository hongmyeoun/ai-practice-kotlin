//괄호 체크
//() 이게 한 세트이고 ()()이것도 한세트이다. (())이것도 가능
//참과 거짓으로 출력을한다.
//괄호만 체크하고 나머지 숫자같은 것이 들어오면 false이다

//input String을 순회하면서 '(' 가 있다면 checkList라는 변경가능한 List에 추가를 한다.
//순회를 하다 ')'가 있다면 추가는 하지 않고 checkList의 마지막 인덱스를 제거한다.
//제거한 문자는 '(' 일테고 만약 아니면 false를 출력한다.

//만약 자료가 남아있다면, false, 남아있지 않고 비었다면 true를 출력한다.

fun vps(checkString: String) {

    val checking = mutableListOf<Char>()
    for (i in checkString) {
        if (i == '(') {
            checking.add(i)
        } else if (i == ')') {
            //닫는 괄호만 들어오게 되면 checking에 아무 정보가 없기 때문에 No가 나와야 된다.
            if (checking.isEmpty()) {
                println("No")
                //그러나 밑에 동작이 실행된다. 어떻게 없엘까
                break
            }
            //순회 도중 닫는 괄호번째가 오면 마지막 인덱스를 없엔다
            val lastString = checking.removeAt(checking.size - 1)
            //제거한 문자는 '(' 일테고 만약 아니면 false를 출력한다.
            if (lastString != '(') {
                println("No")
            }
        }
    }
    //break를 하게 되면 루프문을 끊고 이쪽으로 오게되어 Yes를 실행한다.
    //닫는 괄호만 있다면 이부분이 No가 되야되지만 그렇지 않다.
    if (checking.isEmpty()) {
        println("Yes")
    } else {
        println("No")
    }
}

//'(', '[', '{', '<' 를 추가 해봄
fun vps(checkString: String) {

    val checking = mutableListOf<Char>()
    for (i in checkString) {
        if (i == '(' || i == '[' || i == '{' || i=='<') {
            checking.add(i)
        } else if (i == ')' || i == ']' || i == '}'||i=='>') {
            //닫는 괄호만 들어오게 되면 checking에 아무 정보가 없기 때문에 No가 나와야 된다.
            if (checking.isEmpty()) {
                println("No")
                //그러나 밑에 동작이 실행된다. 어떻게 없엘까
                break
            }
            //순회 도중 닫는 괄호번째가 오면 마지막 인덱스를 없엔다
            val lastString = checking.removeAt(checking.size - 1)
            //만약 닫는 괄호(lastString)와 삭제한 인덱스의 여는괄호(i)가 짝이 맞지 않으면 No
            when (i) {
                ')' -> if (lastString != '(') {
                    println("No")
                }

                ']' -> if (lastString != '[') {
                    println("No")
                }

                '}' -> if (lastString != '{') {
                    println("No")
                }
                '>' -> if (lastString != '<') {
                    println("No")
                }
            }
        }
    }
    //break를 하게 되면 루프문을 끊고 이쪽으로 오게되어 Yes를 실행한다.
    //닫는 괄호만 있다면 이부분이 No가 되야되지만 그렇지 않다.
    if (checking.isEmpty()) {
        println("Yes")
    } else {
        println("No")
    }
}
vps("[({<)>}]") //결과: No, No, No, Yes
