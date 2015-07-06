package generics.DesignPatterns;

abstract class Tree1<E> {
	public interface Visitor<E,R> {
		public R leaf(E let);
		public R branch(R left, R right);
	}
	public abstract String toString();
	public abstract Double sum();
	public abstract <R> R visit(Visitor<E,R> v);
	
	public static <E> Tree1<E> leaf(final E e) {
		return new Tree1<E>() {
			public String toString() {
				return e.toString();
			}
			
			public Double sum() {
				return ((Number)e).doubleValue();
			}
			
			public <R> R visit(Visitor<E,R> v) {
				return v.leaf(e);
			}
		};
	}
	
	public static <E> Tree1<E> branch(final Tree1<E> l, final Tree1<E> r) {
		return new Tree1<E>() {
			public String toString() {
				return l.toString() + "^" + r.toString();
			}
			
			public Double sum() {
				return l.sum() + r.sum();
			}
			
			public <R> R visit(Visitor<E,R> v) {
				return v.branch(l.visit(v), r.visit(v)); 
			}
		};
	}
}
public class TreeTest2 {

	public static <E> String toString(Tree1<E> t) {
		return t.visit(new Tree1.Visitor<E,String>() {
			public String leaf(E e) {
				return e.toString();
			}
			
			public String branch(String l, String r) {
				return l + "^" + r;
			}
		});
	}
	
	public static <N extends Number> double sum(Tree1<N> t) {
		return t.visit(new Tree1.Visitor<N, Double>() {

			@Override
			public Double leaf(N let) {
				return let.doubleValue();
			}

			@Override
			public Double branch(Double left, Double right) {
				// TODO Auto-generated method stub
				return left+ right;
			}
			
		});
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree1<Integer> tree = Tree1.branch(Tree1.branch(Tree1.leaf(1), Tree1.leaf(2)), Tree1.leaf(3));
		toString(tree).equals("1^2^3");
		sum(tree);
	}

}
