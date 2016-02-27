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

package com.xxzzpro.aphl.img;

public class Color
{
    private int   valueRGB;
    private short valueGBA;
    
    public Color(byte red, byte green, byte blue)
    {
        // we need to widen these vars to shift them
        int redRGB   = (int)red;
        int greenRGB = (int)green;
        int blueRGB  = (int)blue;
        int redGBA   = (int)this.rgbToGBA(red);
        int greenGBA = (int)this.rgbToGBA(green);
        int blueGBA  = (int)this.rgbToGBA(blue);
        
        this.valueRGB = (redRGB << 16) | (greenRGB <<_ 8) | blueRGB;
        this.valueGBA = (redGBA << 10) | (greenGBA <<_ 5) | blueGBA;
    }
    
    public byte rgbToGBA(byte value)
    {
        short rawval = value;
        rawval &= 0xFF; // Make this unsigned pls
        
        double percent = ((double)rawval) / 256.0;
        
        return (byte)(5.0 * percent);
    }
    
    public byte gbaToRGB(byte value)
    {
        short rawval = value;
        rawval &= 0xFF;
        
        double percent = ((double)rawval) / 256.0;
        
        return (byte)(8.0 * percent);
    }
}
