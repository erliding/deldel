// Given two strings s and t which consist of only lowercase letters.
//
// String t is generated by random shuffling string s and then add one more letter at a random position.
//
// Find the letter that was added in t.
//
// Example:
//
// Input:
// s = "abcd"
// t = "abcde"
//
// Output:
// e
//
// Explanation:
// 'e' is the letter that was added.


class Solution {
public:
    char findTheDifference(string s, string t) {
        char diff{0};
        for (auto c : s) {
            diff ^= c;
        }
        for (auto c : t) {
            diff ^= c;
        }
        return diff;
    }

    char findTheDifference2(string s, string t) {
        unordered_multiset<char> counts;
        for (auto c : t) counts.insert(c);
        for (auto c : s) counts.erase(counts.find(c));
        return *counts.begin();
    }
};