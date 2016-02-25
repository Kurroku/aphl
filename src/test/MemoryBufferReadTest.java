package com.jugales.aphl.test;

import java.io.File;
import java.io.IOException;

import com.jugales.aphl.MemoryComponent;

public class MemoryBufferReadTest {
	
	/**
	 * What we're doing here is reading the game code (a 4-character representation of the ROM name, language, ect) 
	 * which is at the address 0xAC in the ROM file. This test proves that the buffer containing ROM bytes can be 
	 * read successfully. 
	 * Valid output if successful: BPRE
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		File file = new File("FireRed.gba");
		MemoryComponent rom = new MemoryComponent(file);
		
		System.out.println(rom.getAsciiString(4, 0xAC));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}