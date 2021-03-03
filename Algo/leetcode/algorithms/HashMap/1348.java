class TweetCounts {
    private Map<String, TreeMap<Integer, Integer>> tweetMap;

    public TweetCounts() {
        tweetMap = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        TreeMap<Integer, Integer> treeMap = tweetMap.computeIfAbsent(tweetName, v -> new TreeMap<>());
        treeMap.put(time, treeMap.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res = new ArrayList<>();
        int interval;
        if ("minute".equals(freq)) {
            interval = 60;
        } else if ("hour".equals(freq)) {
            interval = 60 * 60;
        } else {
            interval = 60 * 60 * 24;
        }
        TreeMap<Integer, Integer> userTweets = tweetMap.get(tweetName);
        for (int time = startTime; time <= endTime; time += interval) {
            int end = Math.min(time + interval, endTime + 1);
            Map.Entry<Integer, Integer> entry = userTweets.ceilingEntry(time);
            int count = 0;
            while (entry != null && entry.getKey() < end) {
                count += entry.getValue();
                entry = userTweets.higherEntry(entry.getKey());
            }
            res.add(count);
        }
        return res;
    }
}