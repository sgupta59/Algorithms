package generics.StanfordTutorial;

class Worker extends Person {
	private String occupation;
	Worker(String name, String occupation) {
		super(name);
		this.occupation = occupation;
	}
	
	public String toString() {
		return super.toString() + " " + occupation;
	}
}