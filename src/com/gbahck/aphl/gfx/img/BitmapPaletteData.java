package com.gbahck.aphl.gfx.img;

import java.awt.Color;

import com.gbahck.aphl.MemoryBuffer;
/****************************************************************************\ 
 *                                                                           * 
 *          .8.            8 888888888o     8 8888        8   8 8888         * 
 *         .888.           8 8888    `88.   8 8888        8   8 8888         * 
 *        :88888.          8 8888     `88   8 8888        8   8 8888         * 
 *       . `88888.         8 8888     ,88   8 8888        8   8 8888         * 
 *      .8. `88888.        8 8888.   ,88'   8 8888        8   8 8888         * 
 *     .8`8. `88888.       8 888888888P'    8 8888888888888   8 8888         * 
 *    .8' `8. `88888.      8 8888           8 8888        8   8 8888         * 
 *   .8'   `8. `88888.     8 8888           8 8888        8   8 8888         * 
 *  .888888888. `88888.    8 8888           8 8888        8   8 8888         * 
 * .8'       `8. `88888.   8 8888           8 8888        8   8 888888888888 * 
 *                                                                           * 
 *                     ADVANCED POKÈMON  HACKING LIBRARY                     * 
 *                                                                           * 
 *     A Java library for developers interested in helping ROM hackers.      * 
 *                                                                           * 
 *                Copyright (C) 2016  P. Groves, A. Nicholi                  * 
 *                                                                           * 
 * This program is free software; you can redistribute it and/or modify it   * 
 * under the terms of the GNU General Public License as published by the     * 
 * Free Software Foundation; either version 2 of the License, or (at your    * 
 * option) any later version.                                                * 
 *                                                                           * 
 * This program is distributed in the hope that it will be useful, but       * 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANT-      * 
 * ABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the LICENSE file for    * 
 * more details.                                                             * 
 *                                                                           * 
 * You should have received a copy of the GNU General Public License along   * 
 * with this program; if not, write to the Free Software Foundation, Inc.,   * 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.             * 
 \****************************************************************************/
import com.gbahck.aphl.util.cmp.CompressionType;
import com.gbahck.aphl.util.cmp.HuffmanUtil;
import com.gbahck.aphl.util.cmp.Lz77Util;

/*****************************************************************************\ 
 *                                                                           * 
 *          .8.            8 888888888o     8 8888        8   8 8888         * 
 *         .888.           8 8888    `88.   8 8888        8   8 8888         * 
 *        :88888.          8 8888     `88   8 8888        8   8 8888         * 
 *       . `88888.         8 8888     ,88   8 8888        8   8 8888         * 
 *      .8. `88888.        8 8888.   ,88'   8 8888        8   8 8888         * 
 *     .8`8. `88888.       8 888888888P'    8 8888888888888   8 8888         * 
 *    .8' `8. `88888.      8 8888           8 8888        8   8 8888         * 
 *   .8'   `8. `88888.     8 8888           8 8888        8   8 8888         * 
 *  .888888888. `88888.    8 8888           8 8888        8   8 8888         * 
 * .8'       `8. `88888.   8 8888           8 8888        8   8 888888888888 * 
 *                                                                           * 
 *                     ADVANCED POKÈMON  HACKING LIBRARY                     * 
 *                                                                           * 
 *     A Java library for developers interested in helping ROM hackers.      * 
 *                                                                           * 
 *                Copyright (C) 2016  P. Groves, A. Nicholi                  * 
 *                                                                           * 
 * This program is free software; you can redistribute it and/or modify it   * 
 * under the terms of the GNU General Public License as published by the     * 
 * Free Software Foundation; either version 2 of the License, or (at your    * 
 * option) any later version.                                                * 
 *                                                                           * 
 * This program is distributed in the hope that it will be useful, but       * 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANT-      * 
 * ABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the LICENSE file for    * 
 * more details.                                                             * 
 *                                                                           * 
 * You should have received a copy of the GNU General Public License along   * 
 * with this program; if not, write to the Free Software Foundation, Inc.,   * 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.             * 
\*****************************************************************************/

/**
 * 
 * @author Phillip Groves
 *
 */
public class BitmapPaletteData {
	
	private MemoryBuffer memory;
	
	private final int address;
	
	private int[] values;
	
	private Color[] colors;
	
	public BitmapPaletteData( MemoryBuffer memory, int address, int size, CompressionType compression ) {
		this.memory = memory;
		this.address = address;
		
		switch ( compression ) {
		case NONE:
			values = memory.getBytes(address, size * 2);
			break;
		case LZ77:
			values = Lz77Util.decompress( memory, address );
			break;
		case HUFFMAN:
			values = HuffmanUtil.decompress(memory, address);
		}
		
		this.colors = new Color[values.length / 2];
		
		for ( int i = 0; i < values.length; i++ ) {
			int value = values[i] | ( values[++i] << 8 );
			
			int red = ( value & 0x1F ) << 3;
			int green = ( value & 0x3E0 ) >> 2;
			int blue = ( value & 0x7C00 ) >> 7;
			
			colors[ i / 2] = new Color( red, green, blue );
		}
	}
	
	public BitmapPaletteData( MemoryBuffer memory, int address, CompressionType compression ) {
		this ( memory, address, 0, compression );
	}
	
	public void set( int value, int index ) {
		memory.putShort( value, address + ( index * 2 ) );
	}
	
	public void setAll( int[] newValues ) {
		if ( values.length == newValues.length ) {
			for ( int i = 0; i < values.length; i++ )
				set(newValues[i] , i );
		}
	}
	
	public int get( int index ) {
		return values[index];
	}
	
	public int[] getAll() {
		return values;
	}
	
	public Color getColor( int index ) {
		return colors[index];
	}
	
	public Color[] getColors() {
		return colors;
	}
}
