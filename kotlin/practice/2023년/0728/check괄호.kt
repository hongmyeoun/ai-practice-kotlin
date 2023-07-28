//괄호 체크
//() 이게 한 세트이고 ()()이것도 한세트이다. (())이것도 가능
//참과 거짓으로 출력을한다.
//괄호만 체크하고 나머지 숫자같은 것이 들어오면 false이다
//입력받은 값을 차례대로 받음
//'('가 있을때 ')'가 들어오면 맨앞과 맨뒤를 뺀다.
//만약 자료가 남아있다면, false, 남아있지 않고 비었다면 true를 출력한다.

fun vps(checkString: String){

    val checking = mutableListOf<Char>()
    for (i in checkString){
        checking.add(i)
        if (i == ')'){
            if (checking[0] == '('){
                checking.removeAt(0)
                checking.removeAt(checking.size-1)
            }
        }
    }
    if (checking.isEmpty()){
        println("Yes")
    }else{
        println("No")
    }
}
vps("6")
vps("(())())")
vps("(((()())()")
vps("(()())((()))")
vps("((()()(()))(((())))()")
vps("()()()()(()()())()")
vps("(()((())()(")

/*
No
No
No
Yes
No
Yes
No
*/
