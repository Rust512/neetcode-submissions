class Twitter {

    private static record Tweet(int id, int timestamp) {
    }

    private static record Pair<U, V>(U first, V second) {
    }

    private int timestamp = 0;
    private final Map<Integer, List<Tweet>> tweetsTable = new HashMap<>();
    private final Map<Integer, Set<Integer>> followeesTable = new HashMap<>();

    private static final int NEWS_FEED_COUNT = 10;

    public Twitter() {
    }

    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweets = new ArrayList<>(List.of(new Tweet(tweetId, timestamp++)));
        tweetsTable.merge(userId, tweets, (oldVal, newVal) -> {
            oldVal.addAll(newVal);
            return oldVal;
        });
    }

    private List<Integer> getRecentTweets(List<List<Tweet>> tweets) {
        int numberOfUsers = tweets.size();
        int[] indices = new int[numberOfUsers];

        Comparator<Pair<Integer, Tweet>> comparator = Comparator.comparing((Pair<Integer, Tweet> pair) -> pair.second().timestamp())
                .reversed();
        PriorityQueue<Pair<Integer, Tweet>> container = new PriorityQueue<>(comparator);
        for (int i = 0; i < numberOfUsers; i++) {
            container.offer(new Pair<>(i, tweets.get(i).get(indices[i]++)));
        }

        List<Integer> recentTweets = new ArrayList<>();
        while (!container.isEmpty() && recentTweets.size() < NEWS_FEED_COUNT) {
            Pair<Integer, Tweet> recent = container.poll();
            int index = recent.first();
            recentTweets.add(recent.second().id());
            int next = ++indices[index];
            if (next < tweets.get(index).size()) {
                container.offer(new Pair<>(index, tweets.get(index).get(next)));
            }
        }

        return recentTweets;
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> userIds = followeesTable.containsKey(userId) ? new HashSet<>(followeesTable.get(userId))
                : new HashSet<>();
        userIds.add(userId);
        List<List<Tweet>> allTweets = userIds.stream()
                .map(id -> tweetsTable.get(id))
                .filter(tweets -> !tweets.isEmpty())
                .toList();

        return getRecentTweets(allTweets);
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> followeeSet = new HashSet<>(Set.of(followeeId));
        followeesTable.merge(followerId, followeeSet, (oldVal, newVal) -> {
            oldVal.addAll(newVal);
            return oldVal;
        });
    }

    public void unfollow(int followerId, int followeeId) {
        followeesTable.get(followerId).remove(followeeId);
    }
}
