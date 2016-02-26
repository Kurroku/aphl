package com.xxzzpro.aphl.img;

import java.awt.image.BufferedImage;

/**
 * This class represents the array of pixels a given bitmap image may have. On top of representing a pixel array, 
 * this class includes operations for building ands reading Bitmap images compatible with GBA ROMs. 
 * 
 * @author Kurroku
 */
public class PixelArray {
	
	/** The palette for this image */
	private ColorTable palette;
	
	/** The pixel data for this image */
	private int[] imageData;
	
	/** The image's width and height in pixels */
	private int width, height;
	
	/**
	 * 
	 * @param imageData The pixel data for this image
	 * @param palette The palette for this image
	 * @param width The image's width in pixels
	 * @param height The image's height in pixels
	 */
	public PixelArray(int[] imageData, ColorTable palette, int width, int height) {
		this.imageData = imageData;
		this.palette = palette;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @return The bitmap image in the form of a {@link java.awt.image.BufferedImage}.
	 */
	public BufferedImage getBufferedImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		int index = 0;
		for (int yTile = 0; yTile < ((image.getHeight() / 8)); yTile++) {
			for (int xTile = 0; xTile < ((image.getWidth() / 8)); xTile++) {
				for (int yPixel = 0; yPixel < 8; yPixel++) {
					for (int xPixel = 0; xPixel < 8; xPixel++) {
						int colorIndex = imageData[index / 2];
						
						if ((index & 1) == 0)
							colorIndex &= 0xF;
						else
							colorIndex = (colorIndex & 0xF0) >> 4;
							
						image.getRaster().setPixel(xPixel + (xTile * 8), yPixel + (yTile * 8), 
								new int[] { palette.getColor(colorIndex).getRed(), palette.getColor(colorIndex).getGreen(), palette.getColor(colorIndex).getBlue(),
										(colorIndex == 0 ? 0 : 255) } );
						
						index++;
					}
				}
			}
		}
		return image;
	}
}