class MinHeap {
    // 최소 힙을 저장할 리스트를 생성합니다.
    private val heapArray = mutableListOf<Int>()

    // 새로운 요소를 힙에 삽입하는 함수입니다.
    fun insert(value: Int) {
        // 리스트 끝에 새로운 요소를 추가합니다.
        heapArray.add(value)
        // 삽입한 요소를 올바른 위치로 이동시키기 위해 heapifyUp 함수를 호출합니다.
        heapifyUp(heapArray.size - 1)
    }

    // 최소 힙에서 최솟값을 추출하는 함수입니다.
    fun extractMin(): Int {
        // 힙이 비어있는 경우 예외를 던집니다.
        if (heapArray.isEmpty()) {
            throw NoSuchElementException("Heap is empty.")
        }
        // 최솟값을 저장합니다.
        val minValue = heapArray[0]
        // 힙의 마지막 요소를 루트로 이동시킵니다.
        heapArray[0] = heapArray[heapArray.size - 1]
        // 힙의 마지막 요소를 제거합니다.
        heapArray.removeAt(heapArray.size - 1)
        // 루트로 이동한 요소를 올바른 위치로 이동시키기 위해 heapifyDown 함수를 호출합니다.
        heapifyDown(0)
        // 최솟값을 반환합니다.
        return minValue
    }

    // 힙이 비어있는지 확인하는 함수입니다.
    fun isEmpty(): Boolean {
        return heapArray.isEmpty()
    }

    // 주어진 인덱스의 요소를 부모와 비교하며 힙의 성질을 유지하는 함수입니다.
    private fun heapifyUp(index: Int) {
        var currentIndex = index
        while (currentIndex > 0) {
            // 부모 노드의 인덱스를 계산합니다.
            val parentIndex = (currentIndex - 1) / 2
            // 부모 노드의 값과 현재 노드의 값을 비교하여 힙의 성질을 만족하도록 조정합니다.
            if (heapArray[parentIndex] > heapArray[currentIndex]) {
                // 부모 노드와 현재 노드의 값을 교환합니다.
                swap(parentIndex, currentIndex)
                // 현재 노드의 인덱스를 부모 노드의 인덱스로 변경하여 다음 반복에서 부모와 비교합니다.
                currentIndex = parentIndex
            } else {
                // 힙의 성질을 더 이상 위로 조정할 필요가 없으면 반복을 종료합니다.
                break
            }
        }
    }

    // 주어진 인덱스의 요소를 자식과 비교하며 힙의 성질을 유지하는 함수입니다.
    private fun heapifyDown(index: Int) {
        var currentIndex = index
        val lastIndex = heapArray.size - 1
        while (true) {
            // 왼쪽 자식 노드의 인덱스를 계산합니다.
            var childIndex = currentIndex * 2 + 1
            // 자식 노드가 존재하지 않으면 반복을 종료합니다.
            if (childIndex > lastIndex) {
                break
            }
            // 두 자식 중 더 작은 값을 가진 자식 노드를 선택합니다.
            if (childIndex + 1 <= lastIndex && heapArray[childIndex + 1] < heapArray[childIndex]) {
                childIndex++
            }
            // 자식 노드와 현재 노드의 값을 비교하여 힙의 성질을 만족하도록 조정합니다.
            if (heapArray[currentIndex] > heapArray[childIndex]) {
                // 자식 노드와 현재 노드의 값을 교환합니다.
                swap(currentIndex, childIndex)
                // 현재 노드의 인덱스를 자식 노드의 인덱스로 변경하여 다음 반복에서 자식과 비교합니다.
                currentIndex = childIndex
            } else {
                // 힙의 성질을 더 이상 아래로 조정할 필요가 없으면 반복을 종료합니다.
                break
            }
        }
    }

    // 두 요소의 값을 교환하는 함수입니다.
    private fun swap(index1: Int, index2: Int) {
        val temp = heapArray[index1]
        heapArray[index1] = heapArray[index2]
        heapArray[index2] = temp
    }
}


// MinHeap 클래스의 사용 예시입니다.
val minHeap = MinHeap()
// 요소를 삽입합니다.
minHeap.insert(5)
minHeap.insert(10)
minHeap.insert(2)
minHeap.insert(8)
minHeap.insert(3)
// 최솟값을 순차적으로 추출하여 출력합니다.
while (!minHeap.isEmpty()) {
    val minValue = minHeap.extractMin()
    println(minValue)
}
