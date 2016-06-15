package com.gbahck.aphl.entity;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.entity.hero.Hero;
import com.gbahck.aphl.entity.item.Item;
import com.gbahck.aphl.entity.lvl.LevelEntityManager;
import com.gbahck.aphl.entity.mon.Pokemon;
import com.gbahck.aphl.world.lvl.Level;
import com.gbahck.aphl.world.lvl.LevelEntityHeader;

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
 * highest abstraction for entity data.
 * 
 * @author Phillip Groves
 */
public class EntityManager {

	private final MemoryBuffer memory;
	
	public EntityManager( MemoryBuffer memory ) {
		this.memory = memory;
	}
	
	public Pokemon getPokemon( int id ) {
		return new Pokemon( memory, id );
	}
	
	public Item getItem( int id ) {
		return new Item( memory, id );
	}
	
	/**
	 * @deprecated unfinished.
	 * @param memory
	 * @param levelId
	 * @return
	 */
	public LevelEntityHeader getLevelEntities(int bankId, int levelId ) {
		return null;
	}
	
	public LevelEntityManager getLevelEntities( Level level ) {
		return new LevelEntityManager( memory, level.getEntityHeader() );
	}
	
	public Hero getHero() {
		return new Hero( memory );
	}
}