/*
Time Complexity: O(N * l) where N is the number of words and l is the average length of the words.
Space Complexity: O(N * l) where N is the number of words and l is the average length of the words.
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
Intuition: I used a Trie to store the words. For each word, I check if all its prefixes 
exist in the Trie.
*/

public class LongestWordDictionary {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        String longestWord = "";

        for (String word : words) {
            trie.insert(word);
        }

        for (String word : words) {
            if (trie.allPrefixesExist(word)) {
                if (word.length() > longestWord.length() ||
                        (word.length() == longestWord.length() && word.compareTo(longestWord) < 0)) {
                    longestWord = word;
                }
            }
        }

        return longestWord;
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEnd = true;
    }

    // New helper: Check if all prefixes exist as words
    public boolean allPrefixesExist(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            curr = curr.children[ch - 'a'];
            if (curr == null || !curr.isEnd) {
                return false;
            }
        }
        return true;
    }
}
