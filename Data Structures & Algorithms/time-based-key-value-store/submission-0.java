class TimeMap {
    private static record TimeValue(String value, int timestamp) {}

    private final Map<String, List<TimeValue>> container = new HashMap<>();

    public TimeMap() {
    }
    
    public void set(String key, String value, int timestamp) {
        container.merge(key, new ArrayList<>(List.of(new TimeValue(value, timestamp))), (oldValue, newValue) -> {
            oldValue.addAll(newValue);
            return oldValue;
        });
    }
    
    public String get(String key, int timestamp) {
        List<TimeValue> values = container.get(key);
        if (values.isEmpty()) {
            return "";
        }

        var targetTimeValue = new TimeValue("", timestamp);
        var timeValueComparator = Comparator.comparing(TimeValue::timestamp);
        int index = -1;
        int start = 0;
        int end = values.size() - 1;

        if (timeValueComparator.compare(targetTimeValue, values.get(start)) < 0) {
            return "";
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            var timeValue = values.get(mid);

            int comparison = timeValueComparator.compare(timeValue, targetTimeValue);
            if (comparison == 0) {
                return timeValue.value();
            }

            if (comparison < 0) {
                index = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return values.get(index).value();
    }
}
