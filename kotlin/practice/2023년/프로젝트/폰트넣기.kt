외부에서 폰트를 가져와 사용을 해야할 경우

일단 폰트 이름은 소문자영어와 언더바로 이루어져있어야한다.

1. res파일에 우클릭
2. new
3. Directory -> font 입력
4. 폰트파일을 방금만든 font에 드래그앤드롭
5. font 우클릭
6. new
7. Font Resource File
8. File name  font 입력
9. font.xml 들어가기
10. <font android:font="@font/폰트이름"/> font-family 사이에 넣기
11. fontFamily = FontFamily(Font(R.font.폰트이름)) 으로 사용하기
