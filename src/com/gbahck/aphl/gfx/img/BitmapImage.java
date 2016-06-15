package com.gbahck.aphl.gfx.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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
 * 
 * @author Phillip Groves
 * 
 */
public class BitmapImage extends BufferedImage {
	
	private static final int TILE_SIZE = 8;
	
	private Map<Integer, BufferedImage> tiles = new HashMap<Integer, BufferedImage>();
	
	public BitmapImage( BitmapPixelData pixels, BitmapPaletteData palette, int width, int height ) {
		super ( width, height == 0 ? getHeight( pixels, width ) : height, BufferedImage.TYPE_INT_ARGB );
		if ( width % TILE_SIZE != 0 || height % TILE_SIZE != 0 )
			throw new IllegalStateException( "Bitmap image width and height must be divisible by 8!" );
		
		int index = 0;
		for ( int yTile = 0; yTile < ( super.getHeight() / TILE_SIZE ); yTile++ ) {
			for ( int xTile = 0; xTile < ( super.getWidth() / TILE_SIZE ); xTile++ ) {
				for ( int yPixel = 0; yPixel < TILE_SIZE; yPixel++ ) {
					for ( int xPixel = 0; xPixel < TILE_SIZE; xPixel++ ) {
						int colorIndex = pixels.getPixel( index );
						Color pixel = palette.getColor( colorIndex );
				
						getRaster().setPixel(xPixel + ( xTile * TILE_SIZE ), yPixel + ( yTile * TILE_SIZE ), 
								new int[] { pixel.getRed(), pixel.getGreen(), pixel.getBlue(),
										colorIndex == 0 ? 0 : 255 } );
						index++;
					}
				}
			}
		}
	}
	
	public BitmapImage( BitmapPixelData pixels, BitmapPaletteData palette, int width ) {
		this ( pixels, palette, width, 0 );
	}
	
	public BufferedImage getTile( int id, boolean xFlip, boolean yFlip ) {
		BufferedImage tile = getTile( id );
		if ( xFlip )
			tile = flipTileX( tile );
		if ( yFlip )
			tile = flipTileY( tile );
		return tile;
	}
	
	private BufferedImage getTile( int id ) {
		if ( !tiles.containsKey( id ) ) {
			int x = ( id % ( super.getWidth() / TILE_SIZE ) ) * TILE_SIZE;
			int y = ( id / ( super.getWidth() / TILE_SIZE ) ) * TILE_SIZE;
			tiles.put( id, super.getSubimage( x, y, TILE_SIZE, TILE_SIZE ) );
		}
		return tiles.get( id );
	}
	
	private BufferedImage flipTileX( BufferedImage tile ) {
		BufferedImage image = new BufferedImage( TILE_SIZE, TILE_SIZE, tile.getType() );
		Graphics2D graphics = image.createGraphics();
		graphics.drawImage( tile, 0, 0, TILE_SIZE, TILE_SIZE, TILE_SIZE, 0, 0, TILE_SIZE, null );
		graphics.dispose();
		return image;
	}
	
	private BufferedImage flipTileY( BufferedImage tile ) {
		BufferedImage image = new BufferedImage( TILE_SIZE, TILE_SIZE, tile.getType() );
		Graphics2D graphics = image.createGraphics();
		graphics.drawImage( tile, 0, 0, TILE_SIZE, TILE_SIZE, 0, TILE_SIZE, TILE_SIZE, 0, null );
		graphics.dispose();
		return image;
	}
	
	private static int getHeight( BitmapPixelData pixels, int width ) {
		return ( pixels.getValues().length / width ) * ( 8 / pixels.getDepth().value());
	}
}
