#!/usr/bin/env python3

alphabet = 'abcdefghijklmnopqrstuvwxyz'

def count_sort(wordList, index):
    listSize = len(wordList)
    sortedWords   = [0] * listSize
    count    = [0] * len(alphabet)
    firstLetter = ord('a') 

    for word in wordList: 
        letter = ord(word[index]) - firstLetter
        count[letter] += 1
        
    for i in range(len(count)-1):  
        count[i + 1] += count[i]
    print(count)
    for word in reversed(wordList):
        letter = ord(word[index]) - firstLetter
        sortedWords[count[letter] - 1] = word
        count[letter] -= 1
    return sortedWords

def martian_sort(wordlist: list[str], order: list[int]) -> list[str]:
    # TO-DO    
    for index in reversed(order):
        wordlist = count_sort(wordlist, index)

    return wordlist

# DON'T TOUCH the code below
if __name__ == '__main__':
    order = list(map(int, input().split()))
    wordlist = input().split()
    sorted_words = ' '.join(martian_sort(wordlist, order))
    print(sorted_words)
