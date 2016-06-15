package com.gbahck.aphl.world.lvl.struct;

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
 * <caption>3rd Generation Level Header Data Structure. Size: 28 bytes</caption>
 * <table style="border: 1px solid">
 * <tr><th>Offset</th><th>Field</th><th>Size</th></tr>
 * <tr><td>0</td><td>Footer Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>4</td><td>Entity Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>8</td><td>Script Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>12</td><td>Connection Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>16</td><td>Music Index</td><td>2 Bytes</td></tr>
 * <tr><td>18</td><td>Level Index</td><td>2 Bytes</td></tr>
 * <tr><td>20</td><td>Label Index</td><td>1 Byte</td></tr>
 * <tr><td>21</td><td>Visibility</td><td>1 Byte</td></tr>
 * <tr><td>22</td><td>Weather</td><td>1 Byte</td></tr>
 * <tr><td>23</td><td>Level Type</td><td>1 Byte</td></tr>
 * <tr><td>24</td><td>???</td><td>2 Bytes</td></tr>
 * <tr><td>26</td><td>Label Type</td><td>1 Byte</td></tr>
 * <tr><td>27</td><td>Battle Field</td><td>1 Byte</td></tr>
 * </table>
 * 
 * @author Phillip Groves
 *
 */
public class LevelHeader {

	private final MemoryBuffer memory;
	
	private final int address;
	
	public LevelHeader( MemoryBuffer memory, int address ) {
		this.memory = memory;
		this.address = address;
	}
	
	public int getFooterPointer() {
		return memory.getPointer( address );
	}
	
	public void setFooterPointer( int pointer ) {
		memory.putInteger( pointer, pointer );
	}
	
	public int getEntityPointer() {
		return memory.getPointer( address + 4 );
	}
	
	public void setEntityPointer( int pointer ) {
		memory.putInteger( address + 4, pointer );
	}
	
	public int getScriptPointer() {
		return memory.getPointer( address + 8 );
	}
	
	public void setSciptPointer( int pointer ) {
		memory.putInteger( pointer + 8, pointer );
	}
	
	public int getConnectionPointer() {
		return memory.getPointer( address + 12 );
	}
	
	public void setConnectionPointer( int pointer ) {
		memory.putInteger( address + 12, pointer );
	}
	
	public int getMusicIndex() {
		return memory.getShort( address + 16 );
	}
	
	public void setMusicIndex( int index ) {
		memory.putShort( address + 16, index );
	}
	
	public int getLevelIndex() {
		return memory.getShort( address + 18 );
	}
	
	public int getLabelIndex() {
		return memory.get( address + 20 );
	}
	
	public int getVisibility() {
		return memory.get( address + 21 );
	}
	
	public int getWeather() {
		return memory.get( address + 22 );
	}
	
	public int getLevelType() {
		return memory.get( address + 23 );
	}
	
	public int getLabelType() {
		return memory.get( address + 26 );
	}
	
	public int getBattlefield() {
		return memory.get( address + 27 );
	}
}