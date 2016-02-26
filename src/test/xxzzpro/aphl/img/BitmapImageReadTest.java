package test.xxzzpro.aphl.img;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xxzzpro.aphl.MemoryComponent;
import com.xxzzpro.aphl.img.ColorTable;
import com.xxzzpro.aphl.img.PixelArray;
import com.xxzzpro.aphl.img.cmp.Lz77Compression;

public class BitmapImageReadTest {

	public static void main(String[] args) {
		try {
		File file = new File("FireRed.gba");
		new MemoryComponent(file);
		
		int[] paletteData = Lz77Compression.decompress(0xD321C4);
		ColorTable colorTable = new ColorTable(paletteData);
		
		int[] imageData = Lz77Compression.decompress(0xD31F24);
		PixelArray pixelArray = new PixelArray(imageData, colorTable, 64, 64);
		
		ImageIO.write(pixelArray.getBufferedImage(), "png", new File("Sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Wrote sprite to file");
	}
}