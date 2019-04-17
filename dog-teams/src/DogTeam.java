public class DogTeam {

	private LLDogNode head;

	public DogTeam(Dog dog) {
		head = new LLDogNode(dog, null);
	}

	public void printTeam() {
		LLDogNode cur = head;
		int dogNumber = 1;

		System.out.println("----------------");
		while (cur != null) {
			System.out.println(dogNumber + ". " + cur.getContents().getName() + ", " + cur.getContents().getWeight());
			cur = cur.getLink();
			dogNumber += 1;
		}
	}

	public static void main(String[] args) {

		DogTeam team = new DogTeam(new Dog("dog1", 60));
		team.printTeam();
		System.out.println("weightDiff: " + team.weightDiff());

		team.insertTail(new Dog("dog0", 5));
		team.insertHead(new Dog("dog2", 90));
		team.printTeam();
		System.out.println("weightDiff: " + team.weightDiff());

		team.insertHead(new Dog("dog3", 7));
		team.insertAfter(new Dog("dog4", 100), "dog2");
		team.printTeam();

		team.insertTail(new Dog("dog10", 205));
		team.insertAfter(new Dog("dog9", 75), "dog10");

		team.printTeam();
		System.out.println("weightDiff: " + team.weightDiff());

	}

	public void insertHead(Dog dog) {
		LLDogNode node = new LLDogNode(dog, head);
		this.head = node;
	}

	public void insertTail(Dog dog) {
		LLDogNode current = head;
		while (current.getLink() != null) {
			current = current.getLink();
		}
		current.setLink(new LLDogNode(dog, null));
	}

	public double weightDiff() {
		double max = head.getContents().getWeight(), min = max, weight = 0.0;
	
		LLDogNode current = head;
		while (current.getLink() != null) {
			current = current.getLink();
			weight = current.getContents().getWeight();
			if (weight > max) {
				max = weight;
			}
			if (weight < min) {
				min = weight;
			}
		}

		return max - min;
	}
	/*
	 * 
	 * insertAfter, which puts a new Dog in the list after the Dog with name
	 * dogName. (The exercise assumes that there is only one Dog object with a given
	 * name, so you do not have to worry about multiple dogs having the same name.)
	 */

	public void insertAfter(Dog dog, String dogName) {
		LLDogNode current = head;
		String name = current.getContents().getName();
		
		while (current.getLink() != null && !name.equalsIgnoreCase(dogName)) {
			current = current.getLink();
			name = current.getContents().getName();
		}
		current.setLink(new LLDogNode(dog, current.getLink()));
	}
}


















