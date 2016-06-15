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
public enum PokemonGrowthRate {
	MEDIUM_FAST( 0 , 1000000 ),
	ERRATIC( 1, 600000 ),
	FLUCTUATING( 2, 1640000 ),
	MEDIUM_SLOW( 3, 1059860),
	FAST( 4, 800000),
	SLOW( 5, 125000),
	UNKNOWN(Integer.MAX_VALUE, 0);
	
	private final int value;
	private final int maximumExp;
	
	private PokemonGrowthRate( int value, int maximumExp ) {
		this.value = value;
		this.maximumExp = maximumExp;
	}
	
	public int value() {
		return value;
	}
	
	public int getMaxExperience() {
		return maximumExp;
	}
	
	public static PokemonGrowthRate fromValue( int value ) {
		for( PokemonGrowthRate rate : PokemonGrowthRate.values() ) {
			if( rate.value == value )
				return rate;
		}
		return UNKNOWN;
	}
}
