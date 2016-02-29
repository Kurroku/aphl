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
    private int rgbValue;
    
    /**
     * 
     * @param isGBA Tells if <code>value</code> is a 15-bit GBA colour. Default     *              is 24-bit RGB colour.
     * @param red   The red value to parse during instantiation.
     * @param green The green value to parse during instantiation.
     * @param blue  The blue value to parse during instantiation.
     */
    
    public Color(boolean isGBA, byte red, byte green, byte blue)
    {
        if(isGBA)
        {
            this.rgbValue = ColorManip.gbaToRGB(ColorManip.assembleGBA(red,
                green, blue));
        }
        else
        {
            this.rgbValue = ColorManip.assembleRGB(red, green, blue);
        }
    }
    
    /**
     * 
     * @param isGBA Tells if <code>value</code> is a 15-bit GBA colour. Default     *              is 24-bit RGB colour.
     * @param value the colour value to instantiate the object with.
     */
    
    public Color(boolean isGBA, int value)
    {
        if(isGBA)
        {
            this.rgbValue = ColorManip.gbaToRGB((short)value);
        }
        else
        {
            this.rgbValue = value;
        }
    }
    
    /**
     * 
     * @returns the GBA colour value of the colour object.
     */
    
    public short getGBA()
    {
        return ColorManip.rgbToGBA(this.rgbValue);
    }
    
    /**
     * 
     * @returns the RGB colour value of the colour object.
     */
    
    public short getRGB()
    {
        return this.rgbValue;
    }
    
    /**
     * 
     * @param value the GBA colour value to set the Color object to.
     */
    
    public void setGBA(short value)
    {
        this.rgbValue = ColorManip.gbaToRGB(value);
    }
    
    /**
     * 
     * @param value the RGB colour value to set the Color object to.
     */
    
    public void setRGB(int value)
    {
        this.rgbValue = value;
    }
}
