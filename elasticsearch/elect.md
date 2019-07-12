# 选 Master

##### 模拟从候选 Master Node 列表中选举算法

```java

/**
 * 选择 clusterStateVersion 值最大的 Node 作为 Master。
 * <p>
 * 如果有多个，再比较 id 值，选 id 最大的 Node 作为 Master。
 */
public class EsElect {

    static class Node {
        long id;
        long clusterStateVersion;
        boolean isMasterNode;

        public Node(long id, long clusterStateVersion, boolean isMasterNode) {
            this.id = id;
            this.clusterStateVersion = clusterStateVersion;
            this.isMasterNode = isMasterNode;
        }

        public Long getId() {
            return id;
        }

        public boolean isMasterNode() {
            return isMasterNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", clusterStateVersion=" + clusterStateVersion +
                    '}';
        }
    }

    public static int compare(Node c1, Node c2) {
        int ret = Long.compare(c2.clusterStateVersion, c1.clusterStateVersion);
        if (ret == 0) {
            ret = compareNodes(c1, c2);
        }
        return ret;
    }

    private static int compareNodes(Node o1, Node o2) {
        if (o1.isMasterNode() && !o2.isMasterNode()) {
            return -1;
        }
        if (!o1.isMasterNode() && o2.isMasterNode()) {
            return 1;
        }
        return o1.getId().compareTo(o2.getId());
    }

    public static Node electMaster(Collection<Node> candidates) {
        List<Node> sortedCandidates = new ArrayList<>(candidates);
        sortedCandidates.sort(EsElect::compare);
        return sortedCandidates.get(0);
    }

    public static void main(String[] args) {
        // 候选 Master 节点
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(3, 3, false));
        nodeList.add(new Node(5, 5, false));
        nodeList.add(new Node(2, 2, false));
        nodeList.add(new Node(5, 100, false));
        nodeList.add(new Node(2, 2, false));

        // Master: Node{id=5, clusterStateVersion=100}
        System.out.println("Master: " + electMaster(nodeList));

    }
}
```