package com.gbahck.aphl.entity.mon.stat;

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
public enum PokemonEggGroup {
	MONSTER( 1 ),
	WATER_1( 2 ),
	BUG( 4 ),
	FLYING( 5 ),
	FIELD( 6 ),
	FAIRY( 7 ),
	GRASS( 8 ),
	HUMAN_LIKE( 9 ),
	WATER_3( 10 ),
	MINERAL( 11 ),
	AMORPHOUS( 12 ),
	WATER_2( 13 ),
	DITTO( 14 ),
	DRAGON( 15 ),
	UNDISCOVERED( 16 );
	
	private final int value;
	
	private PokemonEggGroup( int value ) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
	public static PokemonEggGroup fromValue( int value ) {
		for ( PokemonEggGroup group : values() ) {
			if ( value == group.value() )
				return group;
		}
		return UNDISCOVERED;
	}
}
