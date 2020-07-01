package algorithm.hashTable;

import java.util.HashMap;

/**
 * @author woshi
 * @date 2020/7/2
 * 基于散列表的lru算法
 */
public class LRUBaseHashTable<K, V> {
    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头节点
     */
    private DNode<K, V> headNode;

    /**
     * 尾结点
     */
    private DNode<K, V> tailNode;

    private Integer length;

    private Integer capacity;

    /**
     * 散列表存储key
     */
    private HashMap<K, DNode<K, V>> table;

    /**
     * 双向链表
     */
    static class DNode<K, V> {
        private K key;

        private V value;

        private DNode<K, V> prev;
        private DNode<K, V> next;

        public DNode() {
        }

        public DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUBaseHashTable(Integer capacity) {
        this.length = 0;
        this.capacity = capacity;

        headNode = new DNode<>();
        tailNode = new DNode<>();

        headNode.next = tailNode;
        tailNode.prev = headNode;

        table = new HashMap<>();
    }

    public LRUBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 新增
     */
    public void add(K key, V value) {
        DNode<K, V> node = table.get(key);
        if( node == null ){
            DNode<K, V> newNode = new DNode<>(key, value);
            table.put(key, newNode);
            addNode(newNode);

            if( ++length > capacity ){
                DNode<K, V> tail = popTail();
                table.remove(tail.key);
                length--;
            }
        }else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 移动到头结点
     * @param node
     */
    private void moveToHead(DNode<K, V> node) {
        //在原来的位置移除
        removeNode(node);
        //在头结点加
        addNode(node);
    }

    /**
     * 弹出尾部数据节点
     */
    private DNode<K, V> popTail() {
        DNode<K, V> node = tailNode.prev;
        removeNode(node);
        return node;

    }

    /**
     * 移除节点
     * @param node
     */
    private void removeNode(DNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将新节点+到头部
     * @param newNode
     */
    private void addNode(DNode<K, V> newNode) {
        newNode.next = headNode.next;
        newNode.prev = headNode;

        headNode.next.prev = newNode;
        headNode.next = newNode;
    }

    /**
     * 获取节点数据
     */
    public V get(K key){
        DNode<K, V> node = table.get(key);
        if( node == null ){
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 删除节点
     */
    public void remove(K key){
        DNode<K, V> node = table.get(key);
        if( node == null ){
            return;
        }

        removeNode(node);
        length--;
        table.remove(key);
    }

}