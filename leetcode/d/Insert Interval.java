/**
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 **/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class Solution {
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    int len = intervals.size();
    for (int i = 0; i <= len; ) {
      if (i == len) { // bug: must consider the case newInterval being added to the last
        intervals.add(newInterval);
        break;
      }
      if (intervals.get(i).end < newInterval.start) { // no overlap
        i++;
        continue;
      }
      if (intervals.get(i).start > newInterval.end) { // no overlap
        intervals.add(i, newInterval);
        break;
      } else {
        newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
        newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
        intervals.remove(i);
        len--;
      }
    }
    return intervals;
  }
}