#!/usr/bin/env python3

# Warning: Please refrain from using global variables! Your solution will be checked
# by importing your function, not from the raw input/output.

from wordlist import Wordlist

# mapping of digit to possible letters
# e.g., keypad[2] returns all letters mapped to 2
keypad = {
    '2': 'abc',
    '3': 'def',
    '4': 'ghi',
    '5': 'jkl',
    '6': 'mno',
    '7': 'pqrs',
    '8': 'tuv',
    '9': 'wxyz'
}

words = Wordlist('EnglishWords.txt')
### DON'T TOUCH the code above

def predict(digits: str) -> set[str]:
    # TO-DO
    def backtrack(currentCombination, currentDigit):
        if currentDigit == len(digits):
            word = ''.join(currentCombination)
            if words.contains(word):
                suggestions.add(word)
            return
        current_digit = digits[currentDigit]
        for letter in keypad[current_digit]:
            currentCombination.append(letter)
            if words.contains_prefix(''.join(currentCombination)):
                backtrack(currentCombination, currentDigit + 1)
            currentCombination.pop()
    
    suggestions = set()
    backtrack([], 0)
    return suggestions

### DON'T TOUCH the code below

if __name__ == '__main__':
    digits = input()
    words = predict(digits)
    if len(words) == 0:
        print('NONE')
    else:
        for word in words:
            print(word)
