class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0, last = 1, i = 0, n = events.length;
        while (i < n || !pq.isEmpty()) {
            while (i < n && events[i][0] == last) {
                pq.offer(events[i++][1]);
            }
            while (!pq.isEmpty() && pq.peek() < last) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                res++;
            }
            last++;
        }
        return res;
    }
}