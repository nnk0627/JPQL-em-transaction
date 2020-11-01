package com.mmit.entity.dto;

import com.mmit.entity.Item;

public class ItemCategory {

	private Item item;
	private String categoryName;
	
	public ItemCategory(Item item, String categoryName) {
		super();
		this.item = item;
		this.categoryName = categoryName;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
