package com.gbahck.aphl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import com.gbahck.aphl.util.CharacterSetUtil;
import com.gbahck.aphl.util.IntegerUtil;

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
 * <p>An array-backed buffer representing a Pokémon game's memory.</p>
 * 
 * <p>
 * This class defines four categories of operations upon memory buffers.
 * <ul>
 * <li>Absolute and relative get and put methods that read and write single bytes;</li>
 * <li>Relative bulk get methods that transfer contiguous sequences of bytes from 
 * this buffer into an array;</li>
 * <li>Relative bulk put methods that transfer contiguous sequences of bytes from a 
 * byte array into this buffer;</li>
 * <li>Absolute and relative get and put methods that read and write values of other 
 * primitive types, translating them to and from the appropriate byte order;</li>
 * </ul>
 * </p>
 * 
 * <h2>Access to binary data</h2>
 * <p>This class defines methods for reading and writing values of all other primitive 
 * types, except boolean, long, and floating-point types. Primitive values are translated to 
 * little endian byte order when written, and translated to big endian when read. </p>
 * <p>For access to heterogeneous binary data, that is, sequences of values of different 
 * types, this class defines a family of absolute and relative get and put methods for each 
 * type. For 32-bit integer values, for example, this class defines:</p>
 * <blockquote><pre>
 * float  {@link #getInteger()}
 * float  {@link #getInteger(int) getInteher(int address)}
 *  void  {@link #putInteger() putInteger(int address, int value)}
 * </pre></blockquote>
 * 
 * @author Phillip Groves
 */
public class MemoryBuffer {
	
	/** The internal position, or cursor, of this buffer. */
	private int position;
	
	/** A representation of memory as a byte array */
	private byte[] memory;
	
	/**
	 * 
	 * @param rom A valid 3rd Generation Pokémon ROM file.
	 */
	public MemoryBuffer( File rom ) {
		try {
			RandomAccessFile bytes = new RandomAccessFile( rom, "r" );
			memory = new byte[(int)bytes.length()];
			bytes.read( memory );
			bytes.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Relative get method. Reads the byte at this buffer's current position, and then 
	 * increments the position.
	 * 
	 * @return The byte at the buffer's current position
	 */
	public int get() {
		return get( position );
	}
	
	/**
	 * Absolute get method. Reads the byte at the given address.
	 * 
	 * @param address The address from which the byte will be read
	 * @return The byte at the given address
	 */
	public int get( int address ) {
		setPosition(address + 1);
		return memory[address] & 0xFF;
	}
	
	/**
	 * 
	 * @param value
	 * @param address
	 */
	public void put( int address, int value ) {
		setPosition( address + 1 );
		memory[address] = (byte) value;
	}
	
	/**
	 * <p>Relative bulk get method.</p>
	 * <p>This method transfers bytes from the buffer into an array, then returns the array.</p>
	 * <p>This method copies length bytes from this buffer into the array, starting at the
	 * address given. The position of this buffer is then incremented by length.</p>
	 * 
	 * @param address The address from which the bytes will be read
	 * @param length The amount of bytes to read from the address
	 * @return The array of bytes read from the buffer
	 */
	public int[] getBytes( int address, int length ) {
		int[] values = new int[length];
		for ( int i = 0; i < values.length; i++ )
			values[i] = get( address + i );
		return values;
	}
	
	/**
	 * 
	 * @param values
	 * @param address
	 */
	public void putBytes( int[] values, int address ) {
		for ( int i = 0; i < values.length; i++ )
			put( values[i], address + i );
	}
	
	/**
	 * <p>Relative get method for reading a short value.</p>
	 * <p>Reads two bytes at the buffer's current position, composing them into a short value 
	 * of big endian byte order, then increments the position by two.<p>
	 * @return The short value at the buffer's current position
	 */
	public int getShort() {
		return getShort( position );
	}
	
	/**
	 * <p>Absolute get method for reading a short value.</p>
	 * <p>Reads two bytes at the given address, composing them into a short value 
	 * of big endian byte order, then increments the position by two.<p>
	 * 
	 * @param address The address from which the bytes will be read
	 * @return The short value at the given address
	 */
	public int getShort( int address ) {
		setPosition(address + 2);
		return get( address ) | ( get( address + 1 ) << 8 );
	}
	
	/**
	 * 
	 * @param value
	 * @param address
	 */
	public void putShort( int address, int value ) {
		setPosition( address + 2 );
		value = IntegerUtil.reverseBytes( value );
		memory[address] = (byte)( value << 8 );
		memory[address + 1] = (byte)value;
	}
	
	/**
	 * <p>Relative bulk get method of the short primitive type.</p>
	 * <p>This method transfers short values from the buffer into an array, then returns the array.</p>
	 * <p>This method copies length short values from this buffer into the array, starting at the
	 * address given. The position of this buffer is then incremented by length * 2.</p>
	 * <p>Each value read from the buffer is transferred to big endian byte order.</p>
	 * 
	 * @param address The address from which the short values will be read
	 * @param length The amount of short values to read from the address
	 * @return The array of short values read from the buffer
	 */
	public int[] getShorts( int address, int length ) {
		int[] values = new int[length];
		for ( int i = 0; i < values.length; i++ )
			values[i] = getShort( address + ( i * 2) );
		return values;
	}
	
	/**
	 * 
	 * @param values
	 * @param address
	 */
	public void putShorts( int address, int[] values ) {
		for ( int i = 0; i < values.length; i++ )
			putShort( address + ( i * 2 ), values[i] );
	}
	
	/**
	 * <p>Absolute get method for reading an int value.</p>
	 * <p>Reads four bytes at the given address, composing them into an int value 
	 * of big endian byte order, then increments the position by four.</p>
	 * @return The int value at the buffer's current position
	 */
	public int getInteger() {
		return getInteger( position );
	}
	
	/**
	 * <p>Absolute get method for reading an int value.</p>
	 * <p>Reads four bytes at the given address, composing them into an int value 
	 * of big endian byte order, then increments the position by four.</p>
	 * @param address The address from which the bytes will be read
	 * @return The int value at the given address
	 */
	public int getInteger( int address ) {
		setPosition( address + 4 );
		int value = 0;
		for ( int i = 0; i < 4; i++ )
			value |= get( address + i ) << ( 0 + ( i * 8 ) );
		return value;
	}
	
	/**
	 * 
	 * @param value
	 * @param address
	 */
	public void putInteger( int address, int value ) {
		setPosition( address + 4 );
		value = IntegerUtil.reverseBytes( value );
		for ( int i = 0; i < 4; i++ )
			memory[address + i] = (byte)( value << ( ( i * 8 ) ) );
	}
	
	/**
	 * <p>Relative bulk get method of the int primitive type.</p>
	 * <p>This method transfers int values from the buffer into an array, then returns the array.</p>
	 * <p>This method copies length int values from this buffer into the array, starting at the
	 * address given. The position of this buffer is then incremented by length * 4.</p>
	 * <p>Each value read from the buffer is transferred to big endian byte order.</p>
	 * 
	 * @param address The address from which the values will be read
	 * @param length The amount of int values to read from the address
	 * @return The array of int values read from the buffer
	 */
	public int[] getIntegers( int address, int length ) {
		int[] values = new int[length];
		for ( int i = 0; i < values.length; i++ )
			values[i] = getInteger( address + ( i * 4) );
		return values;
	}
	
	/**
	 * 
	 * @param values
	 * @param address
	 */
	public void putIntegers( int address, int[] values ) {
		for ( int i = 0; i < values.length; i++ )
			putInteger( address + ( i * 4 ), values[i] );
	}
	
	/**
	 * <p>Relative get method for reading a pointer.</p>
	 * <p>A pointer is an integer that represents a location of data in memory. By default, 
	 * the leading byte of a pointer is usually 0x08 or 0x09 due to how the 
	 * GameBoy Advance reads memory. Due to this, the leading byte is removed for this library's 
	 * purposes.</p>
	 * <p>Reads four bytes at the buffer's current position, composing them into a valid pointer 
	 * of big endian byte order, then increments the position by four.</p>
	 * @return The pointer at the given address
	 */
	public int getPointer() {
		return getPointer( position );
	}
	
	/**
	 * <p>Absolute get method for reading a pointer.</p>
	 * <p>A pointer is an integer that represents a location of data in memory. By default, 
	 * the leading byte of a pointer is usually 0x08 or 0x09 due to how the 
	 * GameBoy Advance reads memory. Due to this, the leading byte is removed for this library's 
	 * purposes.</p>
	 * <p>Reads four bytes at the given address, composing them into a valid pointer 
	 * of big endian byte order, then increments the position by four.</p>
	 * @param address The address from which the pointer will be read
	 * @return The pointer at the given address
	 */
	public int getPointer( int address ) {
		return getInteger( address ) & 0x1FFFFFF;
	}
	
	/**
	 * <p>Relative bulk get method for reading pointers.</p>
	 * <p>This method transfers pointer values from the buffer into an array, then returns the array.</p>
	 * <p>This method copies length pointer values from this buffer into the array, starting at the
	 * address given. The position of this buffer is then incremented by length * 4.</p>
	 * <p>Each value read from the buffer is transferred to big endian byte order, then has its 
	 * leading 8 bits removed.</p>
	 * 
	 * @param address The address from which the pointers will be read
	 * @param length The amount of pointer values to read from the address
	 * @return The array of pointer values read from the buffer
	 */
	public int[] getPointers( int address, int length ) {
		int[] values = new int[length];
		for ( int i = 0; i < values.length; i++ )
			values[i] = getPointer( address + ( i * 4) );
		return values;
	}
	
	/**
	 * 
	 * @param address
	 * @param length
	 * @return
	 */
	public String getString( int address, int length ) {
		StringBuilder builder = new StringBuilder();
		for ( int i = 0; i < length; i++ )
			builder.append( CharacterSetUtil.get( get( address + i ) ) );
		return builder.toString();
	}
	
	/**
	 * 
	 * @param address
	 * @return
	 */
	public String getString( int address ) {
		StringBuilder builder = new StringBuilder();
		String current;
		setPosition( address );
		while ( !( current = CharacterSetUtil.get( get() )).equals("|end|") )
			builder.append( current );
		return builder.toString();	
	}
	
	/**
	 * 
	 * @param address
	 * @param length
	 * @return
	 */
	public String[] getStrings( int address, int length ) {
		String[] strings = new String[length];
		setPosition( address );
		for ( int i = 0; i < length; i++ )
			strings[i] = getString( position );
		return strings;
	}
	
	/**
	 * <p>Returns the byte array that backs this buffer</p>
	 * <p>Modifications to this buffer's content will cause the returned 
	 * array's content to be modified, and vice versa.</p>
	 * 
	 * @return The array that backs this buffer
	 */
	public byte[] array() {
		return memory;
	}
	
	/**
	 * 
	 * @param address
	 */
	private void setPosition( int address ) {
		position = address;
	}
}
