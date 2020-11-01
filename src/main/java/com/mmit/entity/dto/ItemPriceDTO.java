package com.mmit.entity.dto;

public class ItemPriceDTO {
	private int price;
	private String name;
	
	public ItemPriceDTO() {
		super();
	}
	
	public ItemPriceDTO(int price, String name) {
		super();
		this.price = price;
		this.name = name;
	}
	//ItemPriceDTO obj=new ItemPriceDTO(100,"item");
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
}
