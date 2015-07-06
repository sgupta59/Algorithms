package generics.DesignPatterns;


abstract class Tree<E> {
	abstract public String toString();
	abstract public Double sum();
	
	// static method to create a new leaf.
	public static <E> Tree<E> createLeaf(final E e) {
		return new Tree<E>() {
			public String toString() { 
				return e.toString();
			}
			
			public Double sum() {
				return ((Number)e).doubleValue();
			}
		};
	}
	
	public static <E> Tree<E> createBranch(final Tree<E> left, final Tree<E> right) {
		return new Tree<E>() {
			public String toString() {
				return left.toString() + "^" + right.toString();
			}
			
			public Double sum() {
				return left.sum() + right.sum();
			}
		};
	}
}
public class TreeTest1 {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree<Integer> tree = Tree.createBranch(Tree.createBranch(Tree.createLeaf(1), Tree.createLeaf(2)), Tree.createLeaf(3));
		assert tree.toString().equals("1^2^3");
		assert tree.sum() == 6;
	}

}
