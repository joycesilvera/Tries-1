import java.util.*;

/*
Time Complexity: O(max(M,N)* l) where M is the number of words in the dictionary and N is the number of words in the sentence and l is the average length of the words.
Space Complexity: O(M * l) where M is the number of words in the dictionary and l is the average length of the words.
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
Intuition: I used a Trie to store the dictionary words. 
For each word in the sentence, I traverse the Trie to find the shortest root word that 
can replace it. If a root word is found, I replace the word in the sentence 
with that root word.
*/

public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        StringBuilder result = new StringBuilder();
        for (String word : dictionary) {
            trie.insert(word);
        }

        for (String word : sentence.split(" ")) {
            String wordToUpdate = word;
            TrieNode curr = trie.root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (curr.children[ch - 'a'] == null) {
                    break;
                }
                curr = curr.children[ch - 'a'];
                if (curr.isEnd) {
                    wordToUpdate = word.substring(0, i + 1);
                    break;
                }
            }
            result.append(wordToUpdate).append(" ");
        }

        return result.toString().trim();
    }
}

// implementation of TrieNode
class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        this.children = new TrieNode[26]; // lowercase
        isEnd = false;
    }
}

class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
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

    // Search for a word in the Trie
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

    // Check if a prefix exists in the Trie
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
