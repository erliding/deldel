// Given a string, sort it in decreasing order based on the frequency of characters.
//
// Example 1:
//
// Input:
// "tree"
//
// Output:
// "eert"
//
// Explanation:
// 'e' appears twice while 'r' and 't' both appear once.
// So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
// Example 2:
//
// Input:
// "cccaaa"
//
// Output:
// "cccaaa"
//
// Explanation:
// Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
// Note that "cacaca" is incorrect, as the same characters must be together.
// Example 3:
//
// Input:
// "Aabb"
//
// Output:
// "bbAa"
//
// Explanation:
// "bbaA" is also a valid answer, but "Aabb" is incorrect.
// Note that 'A' and 'a' are treated as two different characters.


class Solution {
public:
    string frequencySort(string s) {
        unordered_map<char, int> freq;
        for (auto c : s) ++freq[c];
        vector<string> bucket(s.size() + 1);
        for (auto& p : freq) bucket[p.second].push_back(p.first);
        string ret;
        for (int i = bucket.size() - 1; i > 0; --i) {
            if (!bucket[i].empty()) {
                for (auto c : bucket[i]) {
                    ret.append(i, c);
                }
            }
        }
        return ret;
    }
};
