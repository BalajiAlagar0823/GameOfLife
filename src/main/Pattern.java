package main;

import java.io.File;

import javafx.scene.image.Image;

public class Pattern {
	
	private int size;
	private String description;
	private String name;
	private Image icon;
	
	public Pattern(int size, String description, String name) {
		this.size = size;
		this.description = description;
		this.name = name;
		this.icon = icon;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
