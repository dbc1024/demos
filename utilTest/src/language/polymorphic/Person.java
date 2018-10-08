package language.polymorphic;

public class Person {

	public void feat(Animal animal){
		animal.eat();
	}
	
	
	public static void main(String[] args) {
		
		Person p = new Person();
		Dog dog = new Dog();
		p.feat(dog);
		
		Cat cat = new Cat();
		p.feat(cat);
		
		
	}
}
