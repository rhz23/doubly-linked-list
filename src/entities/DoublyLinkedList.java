package entities;

public class DoublyLinkedList<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;

    private int listSize;

    public DoublyLinkedList(){
        this.firstNode = null;
        this.lastNode = null;
        this.listSize = 0;
    }

    public int size() {
        return listSize;
    }

    public T get(int index){
        Node<T> auxNode = getNode(index);
        return auxNode.getData();
    }

    public void add(T data){
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(null);
        newNode.setPreviousNode(lastNode);
        if (firstNode == null){
            firstNode = newNode;
        }
        if(lastNode != null){
            lastNode.setNextNode(newNode);
        }
        lastNode = newNode;
        listSize ++;
    }

    public void add(int index, T data){
        Node<T> auxNode = getNode(index);
        Node<T> newNode = new Node(data);
        newNode.setNextNode(auxNode);
        if (newNode.getNextNode() != null){
            newNode.setPreviousNode(auxNode.getPreviousNode());
            newNode.getNextNode().setPreviousNode(newNode);
        }else{
            newNode.setPreviousNode(lastNode);
            lastNode = newNode;
        }
        if(index == 0){
            firstNode = newNode;
        }else{
            newNode.getPreviousNode().setNextNode(newNode);
        }

        listSize ++;
    }

    public void remove(int index){
        if(index == 0){
            firstNode = firstNode.getNextNode();
            if (firstNode != null){
                firstNode.setPreviousNode(null);
            }
        }else{
            Node<T> auxNode = getNode(index);
            auxNode.getPreviousNode().setNextNode(auxNode.getNextNode());
            if (auxNode != lastNode){
                auxNode.getNextNode().setPreviousNode(auxNode.getPreviousNode());
            }else{
                lastNode = auxNode;
            }
        }
        this.listSize --;
    }

    private Node getNode(int index){
        Node<T> auxNode = firstNode;
        for (int i = 0; (i < index) && (auxNode != null); i++){
            auxNode = auxNode.getNextNode();
        }
        return auxNode;
    }

    @Override
    public String toString() {
        String returnString = "";

        Node<T> auxNode = firstNode;
        for (int i=0; i < size(); i++){
            returnString += "[Node{data=" + auxNode.getData() + "}]--->";
            auxNode = auxNode.getNextNode();
        }

        returnString += "null";
        return returnString;
    }
}
