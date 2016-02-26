package com.xxzzpro.aphl.img;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.xxzzpro.aphl.img.cmp.Lz77Compression;

/**
 * A higher level representation of any bitmap image within the ROM. The purpose of this utility class is to make 
 * interacting with bitmap images easy and flexible. Possible operations completed by this class include reading 
 * and writing images to to the ROM, and also reading and writing palettes to the ROM. </br>
 * </br>
 * Bitmap images within the GBA ROM are built out of 8x8 pixel tiles. As such, all images must have a length 
 * and width that are divisible by 8. Any invalid widths or heights will throw an {@link InvalidParameterException}. </br>
 * 
 * @author Kurroku
 */
public class BitmapImage {

	/** The pixel array backing this bitmap image */
	private PixelArray pixelArray;
	
	/**
	 * 
	 * @param imageAddress The address of this image's pixel data
	 * @param paletteAddress The address of this image's palette data
	 * @param imageCompressed Whether or not the pixel data is compressed
	 * @param paletteCompressed Whether or not the palette's data is compressed
	 * @param width The width of the image
	 * @param height The height of the image
	 */
	public BitmapImage(int imageAddress, int paletteAddress, boolean imageCompressed, boolean paletteCompressed, int width, int height) {
		int[] paletteData = null;
		if (paletteCompressed) {
			paletteData = Lz77Compression.decompress(paletteAddress);
		} else {
			// TODO: Build the palette data from uncompressed values
		}
		
		int[] pixelData = null;
		if (imageCompressed) {
			pixelData = Lz77Compression.decompress(imageAddress);
		} else {
			// TODO: Build the pixel data from uncompressed values
		}
		
		this.pixelArray = new PixelArray(pixelData, new ColorTable(paletteData), width, height);
	}
	
	/**
	 * 
	 * @param imageAddress The address of this image's pixel data
	 * @param paletteAddress The address of this image's palette data
	 * @param imageCompressed Whether or not the pixel data is compressed
	 * @param paletteCompressed Whether or not the palette's data is compressed
	 * @param size The width and height of the image
	 */
	public BitmapImage(int imageAddress, int paletteAddress, boolean imageCompressed, boolean paletteCompressed, Point size) {
		this (imageAddress, paletteAddress, imageCompressed, paletteCompressed, size.x, size.y);
	}
	
	/**
	 * 
	 * @param imageData The image's pixel data in the form of an integer array
	 * @param paletteData This image's palette data in the form of an integer array
	 * @param width The width of the image
	 * @param height The height of the image
	 */
	public BitmapImage(int[] imageData, int[] paletteData, int width, int height) {
		this.pixelArray = new PixelArray(imageData, new ColorTable(paletteData), width, height);
	}
	
	/**
	 * 
	 * @param imageData The image's pixel data in the form of an integer array
	 * @param paletteData This image's palette data in the form of an integer array
	 * @param size The width and height of the image
	 */
	public BitmapImage(int[] imageData, int[] paletteData, Point size) {
		this (imageData, paletteData, size.x, size.y);
	}
	
	/**
	 * 
	 * @param imageData The pixel array backing this bitmap image 
	 */
	public BitmapImage(PixelArray pixelArray) {
		this.pixelArray = pixelArray;
	}
	
	public BufferedImage getBufferedImage() {
		return pixelArray.getBufferedImage();
	}
}