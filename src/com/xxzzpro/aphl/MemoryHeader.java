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
 *                     ADVANCED POKéMON  HACKING LIBRARY                     * 
 *                                                                           * 
 *     A Java library for developers interested in helping ROM hackers.      * 
 *                                                                           * 
 *                  Copyright (C) 2016  Jugales, A. Nicholi                  * 
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

package com.xxzzpro.aphl;

import com.xxzzpro.aphl.MemoryComponent.DataType;

/**
 * This Memory Header is a representative of the ROM header and its essential data. The key components in this 
 * object include the game code, ROM title, maker code, and software version. I've left many header values 
 * out of this class due to their immutable state or uselessness to ROM hacking. This class is immutable, however 
 * a new header can be written to the ROM via the {@link MemoryComponent} class if needed. </br>
 * </br>
 * More information: <a href="http://problemkaputt.de/gbatek.htm#gbacartridgeheader">GbaTek</a>
 * 
 * @author Kurroku
 */
public final class MemoryHeader {

	/** The game code, such as BPRE for Fire Red */
	private final String gameCode;
	
	/** The maker code of this ROM, such as 01 for Nintendo */
	private final String makerCode;
	
	/** The 12-character title of this ROM */
	private final String title;
	
	/** The software version. This is usually 00 but sometimes 01 (e.g. Fire Red v1.0 vs. Fire Red v1.1) */
	private final byte version;
	
	/**
	 * 
	 * @param rom The currently accessed ROM whose header is being read
	 */
	public MemoryHeader(MemoryComponent rom) {
		this.gameCode = rom.getAsciiString(4, 0xAC);
		this.makerCode = rom.getAsciiString(2, 0xB0);
		this.title = rom.getAsciiString(12, 0xA0);
		this.version = (byte) rom.get(0xBC, DataType.BYTE);
	}
	
	/**
	 * 
	 * @param gameCode The game code, such as BPRE for Fire Red
	 * @param makerCode The maker code of this ROM, such as 01 for Nintendo
	 * @param title The 12-character title of this ROM
	 * @param version The software version. This is usually 00 but sometimes 01 (e.g. Fire Red v1.0 vs. Fire Red v1.1)
	 */
	public MemoryHeader(String gameCode, String makerCode, String title, byte version) {
		this.gameCode = gameCode;
		this.makerCode = makerCode;
		this.title = title;
		this.version = version;
	}
	
	/**
	 * 
	 * @return The game code, such as BPRE for Fire Red
	 */
	public String getGameCode() {
		return gameCode;
	}
	
	/**
	 * 
	 * @return Unique Code -- usually "A" or "B" or special meaning
	 */
	public char getUniqueCode() {
		return gameCode.charAt(0);
	}
	
	/**
	 * 
	 * @return Short Title such as "PE" for Pokemon Emerald
	 */
	public String getShortTitle() {
		return gameCode.substring(1, 3);
	}
	
	/**
	 * 
	 * @return Destination/Language (usually "J" or "E" or "P" or specific language)
	 */
	public char getLanguage() {
		return gameCode.charAt(3);
	}
	
	/**
	 * 
	 * @return The maker code of this ROM, such as 01 for Nintendo
	 */
	public String getMakerCode() {
		return makerCode;
	}
	
	/**
	 * 
	 * @return The 12-character title of this ROM
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @return The software version. This is usually 00 but sometimes 01 (e.g. Fire Red v1.0 vs. Fire Red v1.1)
	 */
	public byte getVersion() {
		return version;
	}
}
