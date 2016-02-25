package com.jugales.aphl.test;

import java.io.File;
import java.io.IOException;

import com.jugales.aphl.MemoryComponent;
import com.jugales.aphl.MemoryComponent.DataType;

public class MemoryBufferWriteTest {

	public static void main(String[] args) {
		try {
		File file = new File("FireRed.gba");
		MemoryComponent rom = new MemoryComponent(file);
		rom.put(0x7F, 0x00, DataType.BYTE);
		System.out.println(Integer.toHexString(rom.get(0x00, DataType.BYTE)));
		rom.save();
		
		File file1 = new File("FireRed.gba");
		MemoryComponent rom1 = new MemoryComponent(file1);
		System.out.println(Integer.toHexString(rom1.get(0x00, DataType.BYTE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}