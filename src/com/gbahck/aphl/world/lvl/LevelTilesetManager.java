package com.gbahck.aphl.world.lvl;

import java.awt.image.BufferedImage;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.world.lvl.struct.LevelFooter;
import com.gbahck.aphl.world.lvl.struct.LevelTileset;
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

/**
 * 
 * @author Phillip Groves
 *
 */
public class LevelTilesetManager {

	private final BufferedImage atlas;
	
	private final LevelTileset primary;
	
	private final LevelTileset secondary;
	
	public LevelTilesetManager( LevelTileset primary, LevelTileset secondary ) {
		this.primary = primary;
		this.secondary = secondary;
		this.atlas = null;
	}
	
	public LevelTilesetManager( MemoryBuffer memory, LevelFooter footer ) {
		this.primary = null;
		this.secondary = null;
		this.atlas = null;
	}

	public BufferedImage getTile( int id ) {
		return null;
	}
	
	public LevelTileset getPrimary() {
		return primary;
	}
	
	public LevelTileset getSecondary() {
		return secondary;
	}
	
	public BufferedImage getAtlas() {
		return atlas;
	}
}