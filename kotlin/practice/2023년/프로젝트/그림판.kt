// 선을 표현하는 데이터 클래스
data class Line(
    var path: Path = Path(),  // 선의 경로
    var start: Offset,  // 선의 시작점
    var end: Offset  // 선의 끝점
)

@Composable
fun MyCanvas() {
    var lines by remember { mutableStateOf(mutableListOf<Line>()) }  // 그려진 선들의 목록
    var currentLine by remember { mutableStateOf<Line?>(null) }  // 현재 그리고 있는 선
    var eraseMode by remember { mutableStateOf(false) }  // 지우개 모드인지 여부
    var eraserPosition by remember { mutableStateOf<Offset?>(null) }  // 지우개의 위치
    val strokeWidth = 10f  // 선의 굵기
    val color = Color.Black  // 선의 색상
    val eraserRadius = 50f  // 지우개의 반지름

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { startOffset ->
                        if (!eraseMode) {
                            // 그리기 모드일 때는 선을 새로 시작
                            currentLine = Line(start = startOffset, end = startOffset).apply {
                                path.moveTo(startOffset.x, startOffset.y)
                            }
                        } else {
                            // 지우개 모드일 때는 터치한 지점을 통과하는 모든 선을 제거
                            lines = lines.filterNot { it.isTouching(startOffset, eraserRadius) }.toMutableList()
                        }
                        // 지우개의 위치를 업데이트
                        eraserPosition = startOffset
                    },
                    onDrag = { change, dragAmount ->
                        change.consumeAllChanges()
                        if (eraseMode) {
                            // 지우개 모드일 때는 터치한 지점을 통과하는 모든 선을 제거
                            lines = lines.filterNot { it.isTouching(change.position, eraserRadius) }.toMutableList()
                        } else {
                            // 그리기 모드일 때는 선을 계속 그림
                            currentLine?.let {
                                it.end = change.position
                                it.path.lineTo(change.position.x, change.position.y)
                                lines = lines.toMutableList().apply {
                                    add(it)
                                }
                                currentLine = Line(start = change.position, end = change.position).apply {
                                    path.moveTo(change.position.x, change.position.y)
                                }
                            }
                        }
                        // 지우개의 위치를 업데이트
                        eraserPosition = change.position
                    },
                    onDragEnd = {
                        // 드래그가 끝나면 지우개의 위치를 null로 설정
                        eraserPosition = null
                    }
                )
            }
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                for (line in lines) {
                    // 모든 선을 그림
                    drawPath(
                        path = line.path,
                        color = color,
                        style = Stroke(width = strokeWidth)
                    )
                }
                if (eraseMode) {
                    // 지우개 모드일 때는 지우개의 범위를 표시
                    eraserPosition?.let {
                        drawCircle(
                            color = Color.Gray,
                            radius = eraserRadius,
                            center = it,
                            style = Stroke(width = 2f)
                        )
                    }
                }
            }
        )
        Button(onClick = {
            // 지우기 버튼을 누르면 모든 선을 제거
            lines = mutableListOf()
        }) {
            Text(text = "지우기")
        }
        Button(onClick = { eraseMode = !eraseMode }, modifier = Modifier.offset(y = 50.dp)) {
            // 지우개 모드와 그리기 모드를 전환하는 버튼
            Text(text = if (eraseMode) "그리기 모드로 변경" else "지우개 모드로 변경")
        }
    }
}

// 선이 특정 지점을 터치하는지 판단하는 함수
fun Line.isTouching(point: Offset, radius: Float): Boolean {
    val distanceToStart = sqrt((point.x - start.x).pow(2) + (point.y - start.y).pow(2))
    val distanceToEnd = sqrt((point.x - end.x).pow(2) + (point.y - end.y).pow(2))
    val lineLength = sqrt((start.x - end.x).pow(2) + (start.y - end.y).pow(2))

    // 터치 지점이 선의 양 끝점 사이에 있거나 근처에 있으면 터치한 것으로 판단
    return distanceToStart + distanceToEnd <= lineLength * 1.1 + 2 * radius
}
