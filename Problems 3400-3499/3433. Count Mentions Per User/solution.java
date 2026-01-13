class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];

        int[] onlineTime = new int[numberOfUsers];

        events.sort((a, b) -> {
            int timeA = Integer.parseInt(a.get(1));
            int timeB = Integer.parseInt(b.get(1));
            if (timeA == timeB) {
                // OFFLINE events before MESSAGE events if same timestamp
                return b.get(0).compareTo(a.get(0));
            }

            return timeA - timeB;
        });

        int all = 0;
        for (List<String> event : events) {
            System.out.println(event.get(0) + " " + event.get(1));
            String type = event.get(0);
            int timestamp = Integer.parseInt(event.get(1));

            if (type.equals("MESSAGE")) {
                String mentionIds = event.get(2);
                if (mentionIds.equals("ALL")) {
                    all++;
                } else if (mentionIds.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (onlineTime[i] <= timestamp) {
                            mentions[i]++;
                        }
                    }
                } else {
                    String[] ids = mentionIds.split(" ");
                    for (String id : ids) {
                        mentions[Integer.parseInt(id.substring(2))]++;
                    }
                }
            } else {
                // Offline
                int id = Integer.parseInt(event.get(2));
                onlineTime[id] = timestamp + 60;
            }
        }

        for (int i = 0; i < numberOfUsers; i++) {
            mentions[i] += all;
        }

        return mentions;
    }
}