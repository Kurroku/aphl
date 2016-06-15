package com.gbahck.aphl.world.lvl;

import com.gbahck.aphl.MemoryBuffer;

/**
 * 
 * @author Phillip Groves
 *
 */
public class LevelEntityHeader {
	
	private final MemoryBuffer memory;
	
	private final int address;
	
	public LevelEntityHeader( MemoryBuffer memory, int address ) {
		this.memory = memory;
		this.address = address;
	}
	
	public int getCharacterCount() {
		return memory.get( address );
	}
	
	public void setCharacterCount( int count ) {
		memory.put( address, count );
	}
	
	public int getWarpCount() {
		return memory.get( address + 1 );
	}
	
	public void setWarpCount( int count ) {
		memory.put( address + 1, count );
	}
	
	public int getTileScriptCount() {
		return memory.get( address + 2);
	}
	
	public void setTileScriptCount( int count ) {
		memory.put( address + 2, count );
	}
	
	public int getSignCount() {
		return memory.get( address + 3 );
	}
	
	public void setSignCount( int count ) {
		memory.put( address + 3, count );
	}
	
	public int getCharacterPointer() {
		return memory.getPointer( address + 4 );
	}
	
	public void setCharacterPointer( int pointer ) {
		memory.putInteger( address + 4, pointer );
	}
	
	public int getWarpPointer() {
		return memory.getPointer( address + 8 );
	}
	
	public void setWarpPointer( int pointer ) {
		memory.putInteger( address + 8, pointer );
	}
	
	public int getTileScriptPointer() {
		return memory.getPointer( address + 12 );
	}
	
	public void setTileScriptPointer( int pointer ) {
		memory.putInteger( address + 12, pointer );
	}
	
	public int getSignPointer() {
		return memory.getPointer( address + 16 );
	}
	
	public void setSignPointer( int pointer ) {
		memory.putInteger( address + 16, pointer );
	}
}
