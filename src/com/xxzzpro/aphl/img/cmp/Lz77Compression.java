package com.xxzzpro.aphl.img.cmp;

import com.xxzzpro.aphl.MemoryComponent;
import com.xxzzpro.aphl.MemoryComponent.DataType;

/**
 * A utility class with static methods from Lz77 compression and decompression. Lz77 compression is used in 
 * GBA games for images and palette data. The code used in this class is essentially a port from CUE's dsdecmp 
 * library made to be compatible with this library's byte manipulation scheme.
 * 
 * @author Kurroku, originally by CUE
 */
public class Lz77Compression {
	
	/**
	 * Decompresses image data from a given address
	 * 
	 * @param address The address of the image data
	 * @return The decompressed data
	 */
	public static int[] decompress(int address) {
		MemoryComponent rom = MemoryComponent.instance();
		int opcode = rom.get(address, DataType.BYTE);
		
		// 0x10 is the opcode that verifies that this data is compressed through lz77
     	if (opcode != 0x10)
     		throw new IllegalArgumentException("Invalid decompression opcode: " + opcode);
     	return decompressLZ77(rom);
    }
     
	/**
	 * @return The amount of bytes to read
	 */
	private static int getLength(MemoryComponent rom) {
		int length = 0;
		for (int i = 0; i < 3; i++)
			length = length | (rom.get(DataType.BYTE) << (i * 8));
		if (length == 0) 
			length = (int) rom.get(DataType.INTEGER);
		return length;
	}
     
	/**
	 * Decompresses Lz77 compressed data
	 * 
	 * @return The decompressed data
	 */
     private static int[] decompressLZ77(MemoryComponent rom) {
    	 int[] outData = new int[getLength(rom)];
         int currentSize = 0;
         int flags;
         boolean flag;
         int disp, n, b, cdest;

         while (currentSize < outData.length) {
         	flags = rom.get(DataType.BYTE);
	        for (int i = 0; i < 8; i++) {
	             flag = (flags & (0x80 >> i)) > 0;
	             if (flag) {
	                 disp = 0;
	                
	                 b = rom.get(DataType.BYTE);

	                 n = b >> 4;
	                 disp = (b & 0x0F) << 8;
	                 
	                 disp |= rom.get(DataType.BYTE);
	                 
	                 n += 3;
	                 cdest = currentSize;
	                 
	                 if (disp > currentSize)
	                     throw new IllegalStateException("Cannot go back more than already written");
	                 for (int j = 0; j < n; j++)
	                     outData[currentSize++] = outData[cdest - disp - 1 + j];
	                 
	                 if (currentSize > outData.length) 
	                	 break;
	             } else {
	                 b = rom.get(DataType.BYTE);
	                 try { 
	                 	outData[currentSize++] = b; 
	                } catch (ArrayIndexOutOfBoundsException ex) { 
	                 	if (b == 0) 
	                 		break;
	                }
	
	                 if (currentSize > outData.length) break;
	             }
	         }
	     }
	     return outData;
     }
}