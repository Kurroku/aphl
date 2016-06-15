package com.gbahck.aphl.gfx.img;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.util.cmp.CompressionType;
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
 *                     ADVANCED POKéMON  HACKING LIBRARY                     * 
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
public class BitmapPixelData {
	
	private int[] values;
	
	private final BitmapPixelDepth depth;
	
	public BitmapPixelData( MemoryBuffer memory, int address, BitmapPixelDepth depth, CompressionType compression ) {
		this.depth = depth;
		switch ( compression ) {
		case LZ77:
			this.values = Lz77Util.decompress( memory, address );
			break;
		case HUFFMAN:
			this.values = Lz77Util.decompress( memory, address );
			break;
		case NONE:
			
			break;
		}
	}
	
	public int getPixel( int index ) {
		int pixel = values[index / ( 8 / depth.value() )];
		if ( ( index & 1 ) == 0 )
			pixel &= 0x0F;
		else
			pixel = ( pixel & 0xF0 ) >> depth.value();
		return pixel;
	}
	
	public int getPixel( int tileX, int tileY, int x, int y ) {
		return 0;
	}
	
	public void getTile( int tileX, int tileY ) {
		
	}
	
	public int[] getValues() {
		return values;
	}

	public BitmapPixelDepth getDepth() {
		return depth;
	}
}
