package renshu1;

public class Item {
	private String name;
	private int price;
	private int weight;
	
	public Item() {
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getWeight() {
		return weight;
	}
}
