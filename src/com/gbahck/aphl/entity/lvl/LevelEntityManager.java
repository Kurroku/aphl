package com.gbahck.aphl.entity.lvl;

import com.gbahck.aphl.MemoryBuffer;
import com.gbahck.aphl.world.lvl.LevelEntityHeader;

/**
 * 
 * @author Phillip Groves
 *
 */
public class LevelEntityManager {
	
	private final MemoryBuffer memory;
	
	private final LevelEntityHeader header;
	
	public LevelEntityManager( MemoryBuffer memory, LevelEntityHeader header ) {
		this.memory = memory;
		this.header = header;
	}
	
	public NPC getCharacter( int index ) {
		return new NPC( memory, header.getCharacterPointer() + ( index * 24 ) );
	}
	
	public Warp getWarp( int index ) {
		return new Warp( memory, header.getWarpPointer() + ( index * 8 ) );
	}
	
	public Sign getSign( int index ) {
		return new Sign( memory, header.getSignPointer() + ( index * 12 ) );
	}
}
