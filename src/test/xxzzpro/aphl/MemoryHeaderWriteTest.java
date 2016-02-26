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
public class MemoryHeaderWriteTest {

	public static void main(String[] args) {
		try {
			File file = new File("FireRed.gba");
			MemoryComponent rom = new MemoryComponent(file);
			
			String gameCode = "BPCJ";
			String makerCode = "03";
			String title = "POKEMON BAKE";
			byte version = 3;
			MemoryHeader header = new MemoryHeader(gameCode, makerCode, title, version);
			
			rom.setHeader(header);
			rom.save();
			
			// Read the saved changes and print to console
			MemoryHeaderReadTest.main(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}