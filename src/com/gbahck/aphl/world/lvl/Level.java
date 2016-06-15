package com.gbahck.aphl.world.lvl;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.world.lvl.struct.LevelFooter;
import com.gbahck.aphl.world.lvl.struct.LevelHeader;
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
public class Level {
	
	private final LevelHeader header;
	
	private final LevelFooter footer;
	
	private final LevelTilesetManager tilesetManager;
	
	private final LevelEntityHeader entityHeader;
	
	private final LevelScriptHeader scriptHeader;
	
	private final LevelConnectionHeader connectionHeader;
	
	public Level( MemoryBuffer memory, int id ) {
		this.header = null;
		this.footer = new LevelFooter( memory, memory.getPointer( 0x34EB8C + ( id * 4) ) );
		this.tilesetManager = null;
		this.entityHeader = null;
		this.scriptHeader = null;
		this.connectionHeader = null;
	}
	
	public Level( MemoryBuffer memory, int bankId, int mapId ) {
		this.header = new LevelHeader( memory, getHeaderAddress( memory, bankId, mapId ) );
		this.footer = new LevelFooter( memory, header.getFooterPointer() );
		this.tilesetManager = new LevelTilesetManager( memory, footer );
		this.entityHeader = new LevelEntityHeader( memory, header.getEntityPointer() );
		this.scriptHeader = new LevelScriptHeader( memory, header.getScriptPointer() );
		this.connectionHeader = new LevelConnectionHeader( memory, header.getConnectionPointer() );
	}
	
	public LevelHeader getLevelHeader() {
		return header;
	}
	
	public LevelFooter getLevelFooter() {
		return footer;
	}
	
	public LevelTilesetManager getTilesets() {
		return tilesetManager;
	}
	
	public LevelEntityHeader getEntityHeader() {
		return entityHeader;
	}
	
	public LevelScriptHeader getScripts() {
		return scriptHeader;
	}
	
	public LevelConnectionHeader getConnectionHeader() {
		return connectionHeader;
	}
	
	private int getHeaderAddress( MemoryBuffer memory, int bankId, int mapId ) {
		int bankAddress = memory.getPointer(0x3526A8 + (bankId * 4));
		return memory.getPointer(bankAddress + (mapId * 4));
	}
}