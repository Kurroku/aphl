package com.gbahck.aphl.util.cmp;

import com.gbahck.aphl.MemoryBuffer;

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
 * originally by CUE @ dsdcmp, ported for our purposes with general algorithm improvements as well. 
 * TODO: real documentation
 * @author Phillip Groves
 *
 */
public class Lz77Util {

	
	public static void compress( MemoryBuffer memory, int address, int[] data ) {
		
	}
	
	public static int[] decompress( MemoryBuffer memory, int address ) {
		if ( memory.get( address ) != 0x10 )
			throw new IllegalStateException( "Invalid Lz10 compression opcode." );
		
		int[] data = new int[getDecompLength( memory )];
		int destination, flags, offset, length, value, position = 0;
		boolean flagged;
		
		while ( position < data.length ) {
			flags = memory.get();
			for ( int i = 0; i < 8; i++ ) {
				flagged = ( flags & ( 0x80 >> i ) ) > 0;
				if ( flagged ) {
					value = memory.get();
					length = ( value >> 4 ) + 3;
					offset = ( ( value & 0x0F ) << 8 ) | memory.get();
					destination = position;
					
					if ( offset > position )
						throw new IllegalStateException( "Cannot go back more than already written." );
					for ( int j = 0; j < length; j++ )
						data[position++] = data[destination - offset - 1 + j];
				} else {
					value = memory.get();
					if ( position < data.length )
						data[position++] = value;
					else if ( value == 0 )
						break;
				}
				if ( position > data.length )
					break;
			}
		}
		return data;
	}
	
	private static int getDecompLength( MemoryBuffer memory ) {
		int length = 0;
		for ( int i = 0; i < 3; i++ )
			length |= ( memory.get() << ( i * 8) );
		if( length == 0 )
			length = memory.getInteger();
		return length;
	}
}