package sushigame.view;

import comp401sushi.Plate.Color;
import comp401sushi.Sushi;

public class SushiInfo {
	
	private Color color;
	private String type;
	private String name;
	private int position;
	private double price;
	
	
	public SushiInfo(Color color, String type, String name, int position) {
		this.color=color;
		this.type=type;
		this.name=name;
		this.position=position;
	}
	public SushiInfo(Color color, String type, String name, int position, double price) {
		this.color=color;
		this.type=type;
		this.name=name;
		this.position=position;
		this.price=price;
	}
	
	public Color getColor() {
		return color;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public int getPosition() {
		return position;
	}
	public double getPrice() {
		return price;
	}

	
	
}
