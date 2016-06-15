package com.gbahck.aphl.entity.lvl;

import static com.gbahck.aphl.entity.EntityType.NPC;

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
public class NPC extends Entity {

	private final MemoryBuffer memory;
	
	private final int address;
	
	public NPC( MemoryBuffer memory, int address ) {
		super ( NPC, true );
		this.memory = memory;
		this.address = address;
	}
	
	public int getLocalCharacterId() {
		return memory.get( address );
	}
	
	public void setLocalCharacterId( int id ) {
		memory.put( address, id );
	}
	
	public int getSpriteId() {
		return memory.get( address + 1);
	}
	
	public void setSpriteId( int id ) {
		memory.put( address + 1, id );
	}
	
	public int getTileX() {
		return memory.getShort( address + 4 );
	}
	
	public void setTileX( int x ) {
		memory.putShort( address + 4, x );
	}
	
	public int getTileY() {
		return memory.getShort( address + 6 );
	}
	
	public void setTileY( int y ) {
		memory.putShort( address + 6, y );
	}
	
	public int getTalkingLevelId() {
		return memory.get( address + 8 );
	}
	
	public void setTalkingLevelId( int id ) {
		memory.put( address + 8, id );
	}
	
	public int getMovementTypeId() {
		return memory.get( address + 9);
	}
	
	public void setMovementTypeId( int id ) {
		memory.put( address + 9, id );
	}
	
	/**
	 * still researching this
	 * @return
	 */
	public int getMovement() {
		return memory.get( address + 10 );
	}
	
	public void setMovement( int value ) {
		memory.put( address + 10, value );
	}
	
	public boolean isTrainer() {
		return memory.get( address + 12) == 1;
	}
	
	public void setTrainer( boolean isTrainer ) {
		memory.put( address + 12, isTrainer ? 1 : 0 );
	}
	
	public int getViewDistance() {
		return memory.getShort( address + 14 );
	}
	
	public void setViewDistance( int distance ) {
		memory.putShort( address + 14, distance );
	}
	
	public int getScriptPointer() {
		return memory.getPointer( address + 16 );
	}
	
	public void setScriptPointer( int pointer ) {
		memory.putInteger( address + 16, pointer );
	}
	
	public int getGlobalCharacterId() {
		return memory.getShort( address + 20 );
	}
	
	public void setGlobalCharacterId( int id ) {
		memory.putShort( address + 20, id );
	}
}
