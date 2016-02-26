package test.xxzzpro.aphl;

import java.io.File;
import java.io.IOException;

import com.xxzzpro.aphl.MemoryComponent;
import com.xxzzpro.aphl.MemoryHeader;

/**
 * TODO: Documentation
 * 
 * @author Kurroku
 *
 */
public class MemoryHeaderReadTest {

	public static void main(String[] args) {
		try {
			File file = new File("FireRed.gba");
			MemoryComponent rom = new MemoryComponent(file);
			MemoryHeader header = rom.getHeader();
			
			System.out.println("Game code: " + header.getGameCode());
			System.out.println("Maker code: " + header.getMakerCode());
			System.out.println("Title: " + header.getTitle());
			System.out.println("Version: 1." + header.getVersion());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}