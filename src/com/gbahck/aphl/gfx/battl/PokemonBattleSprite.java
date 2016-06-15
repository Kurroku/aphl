package com.gbahck.aphl.gfx.battl;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.gfx.img.BitmapPaletteData;
import com.gbahck.aphl.gfx.img.BitmapPixelData;
import com.gbahck.aphl.gfx.img.BitmapPixelDepth;
import com.gbahck.aphl.util.cmp.CompressionType;

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

public class PokemonBattleSprite extends BattleSprite {

	public PokemonBattleSprite( MemoryBuffer memory, int id, boolean front, boolean shiny ) {
		super(getPixels( memory, id, front ), getPalette( memory, id, shiny ), 64);
	}

	private static BitmapPixelData getPixels( MemoryBuffer memory, int id, boolean front ) {
		int address = memory.getPointer( ( front ? 0x2350AC : 0x23654C ) + ( id * 8 ) );
		return new BitmapPixelData( memory, address, BitmapPixelDepth.BPP_4, CompressionType.LZ77 );
	}
	
	private static BitmapPaletteData getPalette( MemoryBuffer memory, int id, boolean shiny ) {
		int address = memory.getPointer( ( shiny ? 0x2380CC : 0x23730C ) + ( id * 8 ) );
		return new BitmapPaletteData( memory, address, CompressionType.LZ77 );
	}
}
