#!/usr/bin/env python3

class Wordlist:
    def __init__(self, filename):
        self.WORDLIST = set()
        with open(filename) as words:
            lines = words.readlines()
            for line in lines:
                self.WORDLIST.add(line.strip())

    def contains(self, word: str) -> bool:
        '''
        Check whether `word` can be found in the word list.
        '''
        return word in self.WORDLIST

    def contains_prefix(self, prefix: str) -> bool:
        '''
        Check whether there is a word in the word list that starts with `prefix`.
        '''
        return any(w.startswith(prefix) for w in self.WORDLIST)

