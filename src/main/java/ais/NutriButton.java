package ais;

import java.awt.Color;
import java.awt.Label;

import javax.swing.JButton;

public class NutriButton extends JButton {

	private static final long serialVersionUID = 3099488001677537207L;
	
	
	
	private int value;
	
	public NutriButton(String name, Color bcKgColor, int value,Label label) {
		super(name);
		this.value = value;
		
		this.setSize(60, 100);
		this.setBackground(bcKgColor);
		
		this.addMouseListener(ListenerUtils.buildMouseListener(value, label));
	}
	
	public int getValue() {
		return value;
	}
}
