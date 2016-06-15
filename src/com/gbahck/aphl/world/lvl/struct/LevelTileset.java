package com.gbahck.aphl.world.lvl.struct;

import java.awt.image.BufferedImage;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.gfx.img.BitmapImage;
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

/**
 * 
 * @author Phillip Groves
 *
 */
public class LevelTileset {
	
	//private final MemoryBuffer memory;
	
	private final LevelTilesetHeader header;
	
	private final BitmapImage[] atlases = new BitmapImage[13];
	
	public LevelTileset( MemoryBuffer memory, int address ) {
	//	this.memory = memory;
		this.header = new LevelTilesetHeader( memory, address );
		
		int start = header.isSecondary() ? 7 : 0;
		int end = header.isSecondary() ? 13 : 7;
		
		BitmapPixelData pixels = new BitmapPixelData( memory, header.getPixelDataAddress(), BitmapPixelDepth.BPP_4, CompressionType.LZ77 );
		for ( int i = start; i < end; i++ ) {
			BitmapPaletteData palette = new BitmapPaletteData( memory, header.getPaletteDataAddress() + ( ( i - start ) * 32 ), 32, CompressionType.NONE );
			this.atlases[i] = new BitmapImage( pixels, palette, 128, 512 );
		}
	}
	
	public BufferedImage getTile( int id, int palette, boolean xFlip, boolean yFlip ) {
		return atlases[palette].getTile( id, xFlip, yFlip );
	}
	
	public LevelTilesetHeader getHeader() {
		return header;
	}
}