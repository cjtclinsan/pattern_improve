package algorithm.skipList;

/**
 * @author woshi
 * @date 2020/7/1
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 */
public class SkipList2 {
    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;
    private Node head = new Node(MAX_LEVEL);

    public Node find(int value){
        Node p = head;
        //从最大层开始找，找到前一个节点，通过--i，移动下一层去找
        for( int i = levelCount - 1; i >= 0; --i ){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                //前一个节点
                p = p.forwards[i];
            }
        }

        if( p.forwards[0] != null && p.forwards[0].data == value ){
            return p.forwards[0];
        }else {
            return null;
        }
    }

    public void insert2(int value){
        int level = head.forwards[0] == null ? 1 : randomLevel();
        // 每次只增加一层，如果条件满足
        if( level > levelCount ) {
            level = +levelCount;
        }
        Node newNode = new Node(level);
        newNode.data = value;
        Node p = head;

        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }

            //levelCount > level
            if( level > i ){
                if( p.forwards[i] == null ){
                    p.forwards[i] = newNode;
                }else {
                    Node next = p.forwards[i];
                    p.forwards[i] = newNode;
                    newNode.forwards[i] = next;
                }
            }
        }
    }

    public void insert(int value){
        int level = randomLevel();
        Node newNode = new Node(level);
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for( int i = 0; i < level; ++i ){
            update[i] = head;
        }

        Node p = head;
        for( int i = level -1; i >= 0; --i ){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        for( int i = 0; i < level; i++ ){
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        if( levelCount < level ){
            levelCount = level;
        }
    }

    public void delete(int value){
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }
        if( p.forwards[0] != null && p.forwards[0].data == value ){
            for( int i = levelCount - 1; i >= 0; --i ){
                if( update[i].forwards[i] != null && update[i].forwards[i].data == value ){
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null){
            levelCount--;
        }
    }

    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;
        while ( Math.random() < SKIPLIST_P && level < MAX_LEVEL ){
            level += 1;
        }
        return level;
    }

    /**
     * 跳表的节点，每个节点记录了当前节点数据和所在层数数据
     */
    public class Node {
        private int data = -1;
        /**
         * 表示当前节点位置的下一个节点所有层的数据，从上层切换到下层，就是数组下标-1，
         * forwards[3]表示当前节点在第三层的下一个节点。
         */
        private Node forwards[];
        private int maxLevel = 0;

        public Node(int level) {
            forwards = new Node[level];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }
}