package com.gbahck.aphl.entity.lvl;

import static com.gbahck.aphl.entity.EntityType.SIGN;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.entity.Entity;

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
public class Sign extends Entity {
	
	private final MemoryBuffer memory;
	
	private final int address;
	
	public Sign( MemoryBuffer memory, int address ) {
		super ( SIGN );
		this.memory = memory;
		this.address = address;
	}
	
	public int getTileX() {
		return memory.getShort( address );
	}
	
	public void setTileX( int x ) {
		memory.putShort( address, x );
	}
	
	public int getTileY() {
		return memory.getShort( address + 2);
	}
	
	public void setTileY( int y ) {
		memory.putShort( address + 2, y );
	}
	
	public int getTalkingLevelId() {
		return memory.get( address + 4 );
	}
	
	public void setTalkingLevelId( int id ) {
		memory.put( address + 4, id );
	}
	
	public int getSignTypeId() {
		return memory.get( address + 5 );
	}
	
	public void setSignTypeId( int id ) {
		memory.put( address + 5, id );
	}
	
	public int getScriptPointer() {
		return memory.get( address + 8 );
	}
	
	public void setScriptPointer( int pointer ) {
		memory.putInteger( address + 8, pointer );
	}
}
