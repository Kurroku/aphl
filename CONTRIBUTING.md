# APHL Contributors’ Style Norms #

## General text norms ##
- All newlines are the `\n` “line feed” character, not Windows `\r\n` or OS/2 `\r`
- All files must physically end with a newline
- All files are encoded in __UTF-8__
- No line may exceed 80 characters in width

## Coding norms ##
- All code is tab-formatted with __soft tabs,__ four spaces wide
- Opening and closing braces are on their own lines, Allman style
- Statements that extend past the width limit should be broken and printed on the next line with one indent (do not play space alignment with tokens)
- Code should be spaced out enough for optimum reading. Do not pack code into as few lines as possible

## Java specific norms ##
- The order of writing for classes is: class object variables → class primitive
variables → constructor → destructor → object-returning functions →
primitive-returning functions → void functions → enums
- The names for enums, enum members, structs, and classes shall be PascalCase; the names for struct members, class members, functions, function members, and namespaces shall be camelCase.
- No `import` declaration may contain the asterisk character. This is to avoid
ambiguity and confusion when understanding the code.
- Only use as much width of a number as you need; if you know you are not going to need more than 256 values, do not, for instance, use a `long`. Use a `byte`, be conserving!
- Be sure to use `long`s for numbers that could be incremented or set to an infinitely high value, such as with counters.
- Utilize exception handling, always. Be sure to use `try {}` tags for touchy areas for which you do not want to exit the program for. `main()` will catch all other exceptions, print their message, and exit with code `-1`, so take note.

## Project-specific norms ##
All files should have the following Javadoc header. It allows for easy identification of our code, and gives easy access to quick descriptions of things like file purpose, licensing, et cetera. The typeface quotes also give away whether the file was encoded properly along the way. Be sure to modify the three lines in the middle section accordingly with your new file.

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
