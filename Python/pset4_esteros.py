#!/usr/bin/env python3
import heapq
def printMatrix(matrix):
    for row in matrix:
        text = []
        for char in row:
            text.append(char)
        print(text)

def widest_barge(n: int, waterways: list[tuple[int, int, int]]) -> list[int]:
    # Step 1: Create a graph representation
    graph = {i: [] for i in range(n)}
    for u, v, c in waterways:
        graph[u].append((v, c))
        graph[v].append((u, c))

    # Step 2: Dijkstra's algorithm
    max_barge_widths = [float('-inf')] * n
    max_barge_widths[0] = float('inf')

    priority_queue = [(-float('inf'), 0)]  # Max heap to keep track of maximum width

    while priority_queue:
        max_width, current_node = heapq.heappop(priority_queue)
        max_width = -max_width

        for neighbor, capacity in graph[current_node]:
            min_width = min(max_width, capacity)
            if min_width > max_barge_widths[neighbor]:
                max_barge_widths[neighbor] = min_width
                heapq.heappush(priority_queue, (-min_width, neighbor))
    print(graph)
    # Convert to the required output format
    return max_barge_widths[1:]


### DON'T touch anything below this line
#   this already takes care of the input and output
if __name__ == '__main__':
    n, m = map(int, input().split())
    waterways = []
    for _ in range(m):
        u, v, c = map(int, input().split())
        waterways.append((u, v, c))
    result = widest_barge(n, waterways)
    print('\n'.join(map(str, result)))
    
