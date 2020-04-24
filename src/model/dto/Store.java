package model.dto;

public class Store {
	private final String name;
	private final String address;
	
	public Store(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
}
