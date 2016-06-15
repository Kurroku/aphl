package com.gbahck.aphl.entity.mon;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.entity.mon.stat.PokemonAbilityData;
import com.gbahck.aphl.entity.mon.stat.PokemonBattleStatData;
import com.gbahck.aphl.entity.mon.stat.PokemonEffortYieldData;
import com.gbahck.aphl.entity.mon.stat.PokemonEggGroupData;
import com.gbahck.aphl.entity.mon.stat.PokemonGender;
import com.gbahck.aphl.entity.mon.stat.PokemonGrowthRate;
import com.gbahck.aphl.entity.mon.stat.PokemonItemData;
import com.gbahck.aphl.entity.mon.stat.PokemonTypeData;

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
 * <caption>3rd Generation Pokemon Base Stat Data Structure. Size: 26 bytes</caption>
 * <table style="border: 1px solid">
 * <tr><th>Offset</th><th>Field</th><th>Size</th></tr>
 * <tr><td>0</td><td>Base HP</td><td>1 Byte</td></tr>
 * <tr><td>1</td><td>Base Attack</td><td>1 Byte</td></tr>
 * <tr><td>2</td><td>Base Defense</td><td>1 Byte</td></tr>
 * <tr><td>3</td><td>Base Speed</td><td>1 Byte</td></tr>
 * <tr><td>4</td><td>Base Sp. Attack</td><td>1 Byte</td></tr>
 * <tr><td>5</td><td>Base Sp. Defense</td><td>1 Byte</td></tr>
 * <tr><td>6</td><td>Type 1</td><td>1 Byte</td></tr>
 * <tr><td>7</td><td>Type 2</td><td>1 Byte</td></tr>
 * <tr><td>8</td><td>Catch Rate</td><td>1 Byte</td></tr>
 * <tr><td>9</td><td>Base Exp. Yield</td><td>1 Byte</td></tr>
 * <tr><td>10</td><td>Effort Yield</td><td>2 Bytes</td></tr>
 * <tr><td>12</td><td>Item 1</td><td>2 Bytes</td></tr>
 * <tr><td>14</td><td>Item 2</td><td>2 Bytes</td></tr>
 * <tr><td>16</td><td>Gender</td><td>1 Byte</td></tr>
 * <tr><td>17</td><td>Egg Cycles</td><td>1 Byte</td></tr>
 * <tr><td>18</td><td>Base Friendship</td><td>1 Byte</td></tr>
 * <tr><td>19</td><td>Level-Up Type</td><td>1 Byte</td></tr>
 * <tr><td>20</td><td>Egg Group 1</td><td>1 Byte</td></tr>
 * <tr><td>21</td><td>Egg Group 2</td><td>1 Byte</td></tr>
 * <tr><td>22</td><td>Ability 1</td><td>1 Byte</td></tr>
 * <tr><td>23</td><td>Ability 2</td><td>1 Byte</td></tr>
 * <tr><td>24</td><td>Safari Rate</td><td>1 Byte</td></tr>
 * <tr><td>25</td><td>Color and Flip</td><td>1 Byte</td></tr>
 * <tr><td>26</td><td>Filler</td><td>1 Byte</td></tr>
 * </table>
 * 
 * @author Phillip Groves
 *
 */
public class PokemonStatData {

	private final MemoryBuffer memory;
	
	private final int address;
	
	public PokemonStatData( MemoryBuffer memory, int id ) {
		this.memory = memory;
		this.address = 0x2547A0 + ( id * 28 );
	}
	
	public PokemonBattleStatData getBattleStats() {
		return new PokemonBattleStatData( memory, address );
	}
	
	public PokemonTypeData getTypes() {
		return new PokemonTypeData( memory, address + 6 );
	}
	
	public int getCatchRate() {
		return memory.get( address + 8 );
	}
	
	public int getExperienceYield() {
		return memory.get( address + 9 );
	}
	
	public PokemonEffortYieldData getEffortYields() {
		return new PokemonEffortYieldData( memory, address + 10 );
	}
	
	public PokemonItemData getItems() {
		return new PokemonItemData( memory, address + 12 );
	}
	
	public PokemonGender getGender() {
		return PokemonGender.fromValue( memory.get( address + 16 ) );
	}
	
	public int getEggCycles() {
		return memory.get( address + 17 );
	}
	
	public int getFriendship() {
		return memory.get( address + 18 );
	}
	
	public PokemonGrowthRate getGrowthRate() {
		return PokemonGrowthRate.fromValue( memory.get( address + 19 ) );
	}
	
	public PokemonEggGroupData getEggGroups() {
		return new PokemonEggGroupData( memory, address + 20 );
	}
	
	public PokemonAbilityData getAbilities() {
		return new PokemonAbilityData( memory, address + 22 );
	}
	
	public int getSafariRate() {
		return memory.get( address + 24 );
	}
	
	public int getColor() {
		return 0;
	}
	
	public int getFlip() {
		return 0;
	}
}
