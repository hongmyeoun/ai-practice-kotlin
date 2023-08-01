//자 팩토리얼
//펙토리얼 이란
//!이다
//느낌표는
//n!은 n*(n-1)*(n-2)*...*1 이다
//자
fun factorial(n: Int): Int {
    if (n == 1) {
        return 1
    }
    return n * factorial(n - 1)
}
factorial(10)
