#!/usr/bin/env python3
from collections import defaultdict, deque

def arrange_wings(wings, obs):
    graph = defaultdict(list)       #Initialize a graph
    in_degree = {wing: 0 for wing in wings}     #Keep track of how many edges connected to each node

    #Build the Graph
    for relation in obs:
        graph[relation[0]].append(relation[1])
        in_degree[relation[1]] += 1

    #Start the topolical sort
    queue = deque([wing for wing in in_degree if in_degree[wing] == 0])
    result = []

    #Topoligical Sorting
    while queue:
        current = queue.popleft()
        result.append(current)
        
        for neighbor in graph[current]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)

    #Find back edge
    if len(result) == len(wings):
        return result
    else:
        return None


### DON'T touch anything below this line
#   this already takes care of the input and output
if __name__ == '__main__':
    n = int(input())
    wings = list(input().split())
    assert n == len(wings), 'length of wings list do not match'

    m = int(input())
    obs = []
    for _ in range(m):
        di, dj = input().split()
        obs.append((di, dj))
    assert m == len(obs), 'length of observations list do not match'
    ordered_wings = arrange_wings(wings, obs)
    if ordered_wings is None:
        print('IMPOSSIBLE')
    else:
        print('\n'.join(ordered_wings))
