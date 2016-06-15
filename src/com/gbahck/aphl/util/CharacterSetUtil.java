package com.gbahck.aphl.util;

import java.util.HashMap;
import java.util.Map;

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
 * temporarily character set implementation.
 * 
 * @author Phillip Groves
 *
 */
public class CharacterSetUtil {
	
	private static Map<Integer, String> characters = new HashMap<Integer, String>();
	
	public static String get( int value ) {
		if ( characters.containsKey( value ) )
			return characters.get( value );
		else
			return characters.get( 0xFF );
	}
	
	public static int valueOf( String character ) {
		if ( characters.containsValue( character ) ) {
			for ( int i = 0; i <= 0xFF; i++ ) {
				if ( characters.containsKey( i ) && characters.get( i ).equals( character ) ) {
					return i;
				}
			}
		}
		return 0;
	}
	
	static {
		// TODO: Load this from file instead
		characters.put( 0x00, " " );
		characters.put( 0x01, "À" );
		characters.put( 0x02, "Á" );
		characters.put( 0x03, "Â" );
		characters.put( 0x04, "Ç" );
		characters.put( 0x05, "È" );
		characters.put( 0x06, "É" );
		characters.put( 0x07, "Ê" );
		characters.put( 0x08, "Ë" );
		characters.put( 0x09, "Ì" );
		characters.put( 0x0B, "Î" );
		characters.put( 0x0C, "Ï" );
		characters.put( 0x0D, "Ò" );
		characters.put( 0x0E, "Ó" );
		characters.put( 0x0F, "Ô" );
		characters.put( 0x10, "Œ" );
		characters.put( 0x11, "Ù" );
		characters.put( 0x12, "Ú" );
		characters.put( 0x13, "Û" );
		characters.put( 0x14, "Ñ" );
		characters.put( 0x15, "ß" );
		characters.put( 0x16, "à" );
		characters.put( 0x17, "á" );
		characters.put( 0x19, "ç" );
		characters.put( 0x1A, "è" );
		characters.put( 0x1B, "é" );
		characters.put( 0x1C, "ê" );
		characters.put( 0x1D, "ë" );
		characters.put( 0x1E, "ì" );
		characters.put( 0x20, "î" );
		characters.put( 0x21, "ï" );
		characters.put( 0x22, "ò" );
		characters.put( 0x23, "ó" );
		characters.put( 0x24, "ô" );
		characters.put( 0x25, "œ" );
		characters.put( 0x26, "ù" );
		characters.put( 0x27, "ú" );
		characters.put( 0x28, "û" );
		characters.put( 0x29, "ñ" );
		characters.put( 0x2A, "º" );
		characters.put( 0x2B, "ª" );
		characters.put( 0x2D, "&" );
		characters.put( 0x2E, "+" );
		characters.put( 0x34, "[Lv]" );
		characters.put( 0x35, "=" );
		characters.put( 0x36, ";" );
		characters.put( 0x51, "¿" );
		characters.put( 0x52, "¡" );
		characters.put( 0x53, "[pk]" );
		characters.put( 0x54, "[mn]" );
		characters.put( 0x55, "[po]" );
		characters.put( 0x56, "[ké]" );
		characters.put( 0x57, "[bl]" );
		characters.put( 0x58, "[oc]" );
		characters.put( 0x59, "[k]" );
		characters.put( 0x5A, "Í" );
		characters.put( 0x5B, "%" );
		characters.put( 0x5C, "(" );
		characters.put( 0x5D, ")" );
		characters.put( 0x68, "â" );
		characters.put( 0x6F, "í" );
		characters.put( 0x79, "[U]" );
		characters.put( 0x7A, "[D]" );
		characters.put( 0x7B, "[L]" );
		characters.put( 0x7C, "[R]" );
		characters.put( 0x85, "<" );
		characters.put( 0x86, ">" );
		characters.put( 0xA1, "0" );
		characters.put( 0xA2, "1" );
		characters.put( 0xA3, "2" );
		characters.put( 0xA4, "3" );
		characters.put( 0xA5, "4" );
		characters.put( 0xA6, "5" );
		characters.put( 0xA7, "6" );
		characters.put( 0xA8, "7" );
		characters.put( 0xA9, "8" );
		characters.put( 0xAA, "9" );
		characters.put( 0xAB, "!" );
		characters.put( 0xAC, "?" );
		characters.put( 0xAD, "." );
		characters.put( 0xAE, "-" );
		characters.put( 0xAF,"·" );
		characters.put( 0xB0, "..." );
		characters.put( 0xB1, "«" );
		characters.put( 0xB2, "»" );
		characters.put( 0xB3, "'" );
		characters.put( 0xB4, "'" );
		characters.put( 0xB5, "|m|" );
		characters.put( 0xB6, "|f|" );
		characters.put( 0xB7, "$" );
		characters.put( 0xB8, "," );
		characters.put( 0xB9, "*" );
		characters.put( 0xBA, "/" );
		characters.put( 0xBB, "A" );
		characters.put( 0xBC, "B" );
		characters.put( 0xBD, "C" );
		characters.put( 0xBE, "D" );
		characters.put( 0xBF, "E" );
		characters.put( 0xC0, "F" );
		characters.put( 0xC1, "G" );
		characters.put( 0xC2, "H" );
		characters.put( 0xC3, "I" );
		characters.put( 0xC4, "J" );
		characters.put( 0xC5, "K" );
		characters.put( 0xC6, "L" );
		characters.put( 0xC7, "M" );
		characters.put( 0xC8, "N" );
		characters.put( 0xC9, "O" );
		characters.put( 0xCA, "P" );
		characters.put( 0xCB, "Q" );
		characters.put( 0xCC, "R" );
		characters.put( 0xCD, "S" );
		characters.put( 0xCE, "T" );
		characters.put( 0xCF, "U" );
		characters.put( 0xD0, "V" );
		characters.put( 0xD1, "W" );
		characters.put( 0xD2, "X" );
		characters.put( 0xD3, "Y" );
		characters.put( 0xD4, "Z" );
		characters.put( 0xD5, "a" );
		characters.put( 0xD6, "b" );
		characters.put( 0xD7, "c" );
		characters.put( 0xD8, "d" );
		characters.put( 0xD9, "e" );
		characters.put( 0xDA, "f" );
		characters.put( 0xDB, "g" );
		characters.put( 0xDC, "h" );
		characters.put( 0xDD, "i" );
		characters.put( 0xDE, "j" );
		characters.put( 0xDF, "k" );
		characters.put( 0xE0, "l" );
		characters.put( 0xE1, "m" );
		characters.put( 0xE2, "n" );
		characters.put( 0xE3, "o" );
		characters.put( 0xE4, "p" );
		characters.put( 0xE5, "q" );
		characters.put( 0xE6, "r" );
		characters.put( 0xE7, "s" );
		characters.put( 0xE8, "t" );
		characters.put( 0xE9, "u" );
		characters.put( 0xEA, "v" );
		characters.put( 0xEB, "w" );
		characters.put( 0xEC, "x" );
		characters.put( 0xED, "y" );
		characters.put( 0xEE, "z" );
		characters.put( 0xEF, "|>|" );
		characters.put( 0xF0, ":" );
		characters.put( 0xF1, "Ä" );
		characters.put( 0xF2, "Ö" );
		characters.put( 0xF3, "Ü" );
		characters.put( 0xF4, "ä" );
		characters.put( 0xF5, "ö" );
		characters.put( 0xF6, "ü" );
		characters.put( 0xF7, "|A|" );
		characters.put( 0xF8, "|V|" );
		characters.put( 0xF9, "|<|" );
		characters.put( 0xFA, "|nb|" );
		characters.put( 0xFB, "|nb2|" );
		characters.put( 0xFC, "|FC|" );
		characters.put( 0xFD, "|FD|" );
		characters.put( 0xFE, "|br|" );
		characters.put( 0xFF, "|end|" );
	}
}
