package algorithm.stack;

import lombok.Data;

/**
 * @author woshi
 * @date 2020/6/22
 * 使用前后栈实现浏览器的前进后退
 */
public class SampleBrowser {
    private String currentPage;
    private LinkedListBasedStack backStack;
    private LinkedListBasedStack forwardStack;

    public SampleBrowser() {
        this.backStack = new LinkedListBasedStack();
        this.forwardStack = new LinkedListBasedStack();
    }

    public void open(String url){
        if( this.currentPage != null ){
            this.backStack.push(this.currentPage);
            this.forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack(){
        return this.backStack.size > 0;
    }

    public boolean canGoForward(){
        return this.forwardStack.size > 0;
    }

    public String goBack(){
        if(this.canGoBack()){
            this.forwardStack.push(this.currentPage);
            String backUrl = this.backStack.pop();
            showUrl(backUrl, "Back");
            return backUrl;
        }
        return null;
    }

    public String goForward(){
        if(this.canGoForward()){
            this.backStack.push(this.currentPage);
            String forwardUrl = this.backStack.pop();
            showUrl(forwardUrl, "Forward");
            return forwardUrl;
        }

        return null;
    }

    private void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix+"page =="+url);
    }

    public void checkCurrentPage(){
        System.out.println("Current page is:"+this.currentPage);
    }

    public static class LinkedListBasedStack {
        private int size;
        private Node top;

        static Node createNode(String data, Node next){
            return new Node(data, next);
        }

        public void clear(){
            this.top = null;
            this.size = 0;
        }

        public void push(String data){
            Node node = createNode(data, this.top);
            this.top = node;
            this.size++;
        }

        public String pop(){
            Node popNode = this.top;
            if( popNode == null ){
                return null;
            }
            this.top = popNode.next;
            if( this.size > 0 ){
                this.size--;
            }
            return popNode.data;
        }

        public String getTopData(){
            if( this.top == null ){
                return null;
            }
            return this.top.data;
        }

        public int size(){
            return this.size;
        }
    }

    @Data
    public static class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this(data, null);
        }

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}