/*
Time Complexity: O(l) where l is the length of the word
Space Complexity: O(l) where l is the number of words in the dictionary 
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
Intuition: I used a Trie to store the words. For each word, I traverse the Trie to check if it exists. 
If it does, I return true, otherwise false. 
For checking prefixes, I traverse the Trie until I reach the end of the prefix.
*/

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        this.children = new TrieNode[26]; // lowercase
        isEnd = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // O(n) where n is the length of the word
    public void insert(String word) {
        TrieNode curr = root; // curr is used for traverse down the trie paths
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEnd = true;
    }

    // O(n) where n is the length of the word
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }
        return curr.isEnd;
    }

    // O(p) where p is the length of the prefix
    public boolean startsWith(String prefix) {
        TrieNode curr = root; // curr is used for traverse down the trie paths
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }
        return true;
    }
}
