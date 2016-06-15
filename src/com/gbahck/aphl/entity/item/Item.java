package com.gbahck.aphl.entity.item;

import static com.gbahck.aphl.entity.EntityType.ITEM;

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
 * <p>Each instance of this class represents a single item entity within the game world. Any 
 * changes made to the item are automatically applied to the underlying {@link MemoryBuffer}.</p>
 * 
 * <caption>3rd Generation Item Data Structure. Size: 44 bytes</caption>
 * <table style="border: 1px solid">
 * <tr><th>Offset</th><th>Field</th><th>Size</th></tr>
 * <tr><td>0</td><td>Name</td><td>14 Bytes</td></tr>
 * <tr><td>14</td><td>Index Number</td><td>2 Bytes</td></tr>
 * <tr><td>16</td><td>Price</td><td>2 Bytes</td></tr>
 * <tr><td>18</td><td>Hold Effect</td><td>1 Byte</td></tr>
 * <tr><td>19</td><td>???</td><td>1 Byte</td></tr>
 * <tr><td>20</td><td>Description Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>24</td><td>Mystery Value</td><td>2 Bytes</td></tr>
 * <tr><td>26</td><td>Pocket</td><td>1 Byte</td></tr>
 * <tr><td>27</td><td>Type</td><td>1 Byte</td></tr>
 * <tr><td>28</td><td>Field Usage Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>32</td><td>Battle Usage</td><td>4 Bytes</td></tr>
 * <tr><td>36</td><td>Battle Usage Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>40</td><td>???</td><td>4 Bytes</td></tr>
 * </table>
 * 
 * @author Phillip Groves
 *
 */
public class Item extends Entity {
	
	private final MemoryBuffer memory;
	
	private final int address;
	
	public Item( MemoryBuffer memory, int id ) {
		super ( ITEM, true );
		this.memory = memory;
		this.address = 0;
	}
	
	public String getName() {
		return memory.getString( address, 14 );
	}
	
	public void setName( String name ) {
		if ( name.length() > 14 )
			throw new IllegalStateException( "New item name must be 14 characters or less!" );
		// TODO: memory.putString( address, name );
	}
	
	public int getIndex() {
		return memory.getShort( address + 14 );
	}
	
	public void setIndex( int index ) {
		memory.putShort( address + 14, index );
	}
	
	public int getPrice() {
		return memory.getShort( address + 16 );
	}
	
	public void setPrice( int price ) {
		memory.putShort( address + 16, price );
	}
	
	public int getHoldEffect() {
		return memory.get( address + 18 );
	}
	
	public void setHoldEffect( int effect ) {
		memory.put( address + 18, effect );
	}
	
	public String getDescription() {
		return memory.getString( memory.getPointer( address + 20 ) );
	}
	
	public int getDescriptionPointer() {
		return memory.getPointer( address + 20 );
	}
	
	public void setDescriptionPointer( int pointer ) {
		memory.putInteger( address + 20, pointer );
	}
	
	public int getMysteryValue() {
		return memory.getShort( address + 24 );
	}
	
	public void setMysteryValue( int value ) {
		memory.putShort( address + 24, value );
	}
	
	public int getPocket() {
		return memory.get( address + 26 );
	}
	
	public void setPocket( int pocket ) {
		memory.put( address + 26, pocket );
	}
	
	public int getItemType() {
		return memory.get( address + 27 );
	}
	
	public void setItemType( int type ) {
		memory.put( address + 27, type );
	}
	
	public int getFieldUsagePointer() {
		return memory.getPointer( address + 28 );
	}
	
	public void setFieldUsagePointer( int pointer ) {
		memory.putInteger( address + 28, pointer );
	}
	
	public int getBattleUsage() {
		return memory.getInteger( address + 32 );
	}
	
	public void setBattleUsage( int usage ) {
		memory.putInteger( address + 32, usage );
	}
	
	public int getBattleUsagePointer() {
		return memory.getPointer( address + 36 );
	}
	
	public void setBattleUsagePointer( int pointer ) {
		memory.putInteger( address + 36, pointer );
	}
}
