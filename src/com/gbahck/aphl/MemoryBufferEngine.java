package com.gbahck.aphl;

import java.io.File;

import com.gbahck.aphl.audio.AudioManager;
import com.gbahck.aphl.entity.EntityManager;
import com.gbahck.aphl.gfx.GraphicsManager;
import com.gbahck.aphl.util.UtilityManager;
import com.gbahck.aphl.world.WorldManager;

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
 *                     ADVANCED POKéMON  HACKING LIBRARY                     * 
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
 * <p>This high-level environment class holds references to various managers that 
 * are able to manipulate resources within 3rd Generation Pokémon games for GameBoy Advance.</p> 
 * 
 * <h2>Resource Overview</h2> 
 * <p>Throughout this API, resources are broken into four groups: audio, entity, graphics, 
 * and world. Additionally, generic utilities are available to act on these resources. Each 
 * resource group has a corresponding manager which encapsulates its available data:</p>
 * <ul>
 * <li>{@link AudioManager}</li>
 * <li>{@link EntityManager}</li>
 * <li>{@link GraphicsManager}</li>
 * <li>{@link UtilityManager}</li>
 * <li>{@link WorldManager}</li>
 * </ul>
 * 
 * <p>Each of these managers share the same underlying {@link MemoryBuffer} instance, so 
 * they are immediately aware of changes made by one-another without being tightly coupled.</p>
 * 
 * <h3>Audio Resources</h3>
 * <p>An audio resource is a resource whose purpose is to make a sound. Audio resources often 
 * encapsulate songs, cries, and other noises. Audio is one of the more complicated of resources 
 * to manipulate. Currently, this API does not guarantee support for audio resources.</p>
 * 
 * <h3>Entity Resources</h3>
 * <p>An entity is any game object that is represented in the game world. Currently, this API 
 * supports fives types of entities: hero, item, level, pokémon, and trainer. Note: "level" refers 
 * to all entities found on a level (NPCs, warps, signs, ect). </p>
 * 
 * <h1>Graphics Resources</h1>
 * <p>A graphics resource is anything that is displayed in the game. All graphics are composed of 
 * tiled bitmap images and a {@link BitmapImage} class was created to account for this.
 * {@link BitmapImage} extends {@link BufferedImage} and various graphics objects (spites, tilesets) 
 * extend {@link BitmapImage}. This allows for easy manipulation and compatibility 
 * with other Java APIs and GUIs.</p>
 * 
 * <h3>Utilities</h3>
 * <p>While not necessarily a resource, a group of utilities are available. These utilities include 
 * common methods for compression, integer manipulation, and the game's custom character set.</p>
 * 
 * <h3>World Resources</h3>
 * <p>A world resource is any structure that represents the game world itself. Resources available in 
 * this group include levels (single maps), and world map data. This API includes extensive support 
 * for manipulating data for levels. More support for world map data is coming soon.</p>
 * 
 * @author Phillip Groves
 */
public final class MemoryBufferEngine {
	
	/** The underlying {@link MemoryBuffer} which all managers use. */
	private final MemoryBuffer memory;
	
	/** The manager which encapsulates audio resources. */
	private final AudioManager audio;
	
	/** The manager which encapsulates entity resources. */
	private final EntityManager entities;
	
	/** The manager which encapsulates graphics resources. */
	private final GraphicsManager graphics;
	
	/** The manager which grants access to various common utilities. */
	private final UtilityManager utilities;
	
	/** The manager which encapsulates world resources. */
	private final WorldManager world;
	
	/**
	 * 
	 * @param rom The file containing the ROM
	 */
	public MemoryBufferEngine( File rom ) {
		this.memory = new MemoryBuffer( rom );
		
		this.audio = new AudioManager( memory );
		this.entities = new EntityManager( memory );
		this.graphics = new GraphicsManager( memory );
		this.utilities = new UtilityManager( memory );
		this.world = new WorldManager( memory );
	}
	
	/**
	 * 
	 * @return {@link #audio}
	 */
	public AudioManager getAudio() {
		return audio;
	}
	
	/**
	 * 
	 * @return {@link #entities}
	 */
	public EntityManager getEntities() {
		return entities;
	}
	
	/**
	 * 
	 * @return {@link #graphics}
	 */
	public GraphicsManager getGraphics() {
		return graphics;
	}
	
	/**
	 * 
	 * @return {@link #memory}
	 */
	public MemoryBuffer getMemory() {
		return memory;
	}
	
	/**
	 * 
	 * @return {@link #utilities}
	 */
	public UtilityManager getUtilities() {
		return utilities;
	}
	
	/**
	 * 
	 * @return {@link #world}
	 */
	public WorldManager getWorld() {
		return world;
	}
}
