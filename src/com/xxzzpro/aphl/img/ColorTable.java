package com.xxzzpro.aphl.img;

import java.awt.Color;

/**
 * This class represents a 16-color of 256-color palette from the ROM. Each color is a 16-bit value whose 
 * individual bits determine RGB-values. Of these 16 bits, each color is made up of 5 bits, leaving one bit 
 * to be ignored.</br>
 * </br>
 * An the order of the bits and which correspond to each color value are as follows: </br>
 * Where X= Ignore, B = Blue, G = Green, R = Red. </br>
 * X B B B  B B G G  G G G R  R R R R. </br>
 * </br>
 * More information: <a href="https://www.cs.rit.edu/~tjh8300/CowBite/CowBiteSpec.htm#Color Format">Cowbite: Color Format</a></br>
 * More information: <a href="http://problemkaputt.de/gbatek.htm#lcdcolorpalettes">GbaTek: LCD Color Palettes</a>
 * 
 * @author Jugales
 */
public class ColorTable {

	/** All colors in this palette */
	private Color[] colors;
	
	/** The 16-bit color values for each color in the palette */
	private int[] data;
	
	/**
	 * 
	 * @param data The 16-bit color values for each color in the palette
	 */
	public ColorTable(int[] data) {
		this.colors = new Color[data.length];
		this.data = data;
		
		for (int i = 0; i < colors.length; i++) {
			int color = data[i] + (data[i + 1] << 8);
			
			int r = (color & 0x1F) << 3;
			int g = (color & 0x3E0) >> 2;
			int b = (color & 0x7C00) >> 7;
			this.colors[i / 2] = new Color(r, g, b);

			i++;
		}
	}
	
	/**
	 * 
	 * @param colors All colors in this palette
	 */
	public ColorTable(Color[] colors) {
		if (colors.length != 16 && colors.length != 256)
			throw new IllegalArgumentException("Illegal palette! Argument array \"colors\" must be a length of 16 or 256");
		// TODO: Convert colors to proper 16-bit GBA-ready values
	}
	
	/** 
	 * 
	 * @param index The index of the color in the array
	 * @return The color at the specific index
	 */
	public Color getColor(int index) {
		return colors[index];
	}
	
	/**
	 * 
	 * @return All colors of the array
	 */
	public Color[] getColors() {
		return colors;
	}
	
	/**
	 * 
	 * @return The byte values for all reds in this palette
	 */
	public byte[] getReds() {
		byte[] reds = new byte[colors.length];
		for (int i = 0; i < colors.length; i++)
			reds[i] = (byte) colors[i].getRed();
		return reds;
	}
	
	/**
	 * 
	 * @return The byte values for all greens in this palette
	 */
	public byte[] getGreens() {
		byte[] greens = new byte[colors.length];
		for (int i = 0; i < colors.length; i++)
			greens[i] = (byte) colors[i].getGreen();
		return greens;
	}
	
	/**
	 * 
	 * @return The byte values for all blues in this palette
	 */
	public byte[] getBlues() {
		byte[] blues = new byte[colors.length];
		for (int i = 0; i < colors.length; i++)
			blues[i] = (byte) colors[i].getBlue();
		return blues;
	}
	
	/**
	 * 
	 * @return The array of the 16-bit color values for each color in the palette
	 */
	public int[] getData() {
		return data;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < data.length; i++)
			builder.append("[" + data[i] + "]" + (i != data.length - 1 ? "," : ""));
		return builder.toString();
	}
}