#!/usr/bin/env python3
def deadsimplesum(n: int) -> int:
    count=0
    sum=0
    if(n%2==0):
        sum=n//2
    else:
        sum=-1*n//2
    return sum


if __name__ == '__main__':
    n = int(input())
    print(deadsimplesum(n))
