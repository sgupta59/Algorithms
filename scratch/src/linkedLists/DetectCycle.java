package linkedLists;

public class DetectCycle {

	public static boolean hasCycle(Node head)
	{
		 Node previousNode = null;
		  Node currentNode = head;
		  Node nextNode;
		  if (currentNode._next == null) return false;
		  while(currentNode != null){
		    nextNode = currentNode._next;
		    currentNode._next = previousNode;
		    previousNode = currentNode;
		    currentNode = nextNode;
		  }
		  return (previousNode == head);
	}
	public static boolean isCycle(Node head)
	{
		Node slowNode = head;
		Node fastNode1 = head ;
		Node fastNode2 = head;
		  while (slowNode != null && (fastNode1 = fastNode2._next) != null && (fastNode2 = fastNode1._next) != null){
		    if (slowNode == fastNode1 || slowNode == fastNode2) return true;
		    slowNode = slowNode._next;
		  }
		  return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Node head = new Node(1);
        head._next = new Node(2);
        head._next._next = new Node(3);
        head._next._next._next = new Node(4);
        head._next._next._next._next = new Node(5);
        head._next._next._next._next._next = head._next;
        System.out.println(hasCycle(head));
	}

}
