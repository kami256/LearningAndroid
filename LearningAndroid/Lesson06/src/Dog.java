
public class Dog {

	private		String	name;
	private		int		age;
	
	public	void	setName(String n){
		name = n;
	}

	public	void	setAge(int age){
		this.age = age;
	}

	public	void	showPrfile(){
		System.out.println("名前は、" + name + "です。");
		System.out.println("年齢は、" + age + "才です。");
	}

}
