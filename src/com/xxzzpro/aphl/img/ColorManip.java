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

public static class ColorManip
{
    /**
     * 
     * @param red   the red channel value.
     * @param green the green channel value.
     * @param blue  the blue channel value.
     * @returns the assembled RGB colour as an <code>int</code>.
     */
    
    public int assembleRGB(byte red, byte green, byte blue)
    {
        return this.assembleColor(false, red, green, blue);
    }
    
    /**
     * 
     * @param red   the red channel value.
     * @param green the green channel value.
     * @param blue  the blue channel value.
     * @returns the assembled GBA colour as a <code>short</code>.
     */
    
    public short assembleGBA(byte red, byte green, byte blue)
    {
        return (short)this.assembleColor(true, red, green, blue);
    }
    
    /**
     * 
     * @param isGBA tells if we're supposed to output a 15-bit colour. Defaults
     *              to 24-bit colour.
     * @param red   the red channel value.
     * @param green the green channel value.
     * @param blue  the blue channel value.
     * @returns the assembled color as an <code>int</code>.
     */
    
    private int assembleColor(boolean isGBA, byte red, byte green, byte blue)
    {
        int  red_   = ((int)red) & 0xFF;
        int  green_ = ((int)green) & 0xFF;
        int  blue_  = ((int)blue) & 0xFF;
        byte width  = 8;
        
        if(isGBA)
        {
            width = 5;
        }
        
        return (blue_ << (width * 2)) | (green_ << width) | red_;
    }
    
    /**
     * 
     * @param value the GBA colour to convert.
     * @returns the converted RGB colour.
     */
    
    public int gbaToRGB(short value)
    {
        byte red   = this.gbaChanToRGB(this.getRed(true, value));
        byte green = this.gbaChanToRGB(this.getGreen(true, value));
        byte blue  = this.gbaChanToRGB(this.getBlue(true, value));
        
        return this.assembleRGB(red, green, blue);
    }
    
    /**
     * 
     * @param value the RGB colour to convert.
     * @returns the converted GBA colour.
     */
    
    public short gbaToRGB(short value)
    {
        byte red   = this.rgbChanToGBA(this.getRed(false, value));
        byte green = this.rgbChanToGBA(this.getGreen(false, value));
        byte blue  = this.rgbChanToGBA(this.getBlue(false, value));
        
        return this.assembleGBA(red, green, blue);
    }
    
    /**
     * 
     * @param value a single channel value of a 15-bit GBA colour.
     * @returns <code>value</code> converted to an 8-bit channel value.
     */
    
    public byte rgbChanToGBA(byte value)
    {
        return this.convertChans(5, value);
    }
    
    /**
     * 
     * @param value a single channel value of a 15-bit GBA colour.
     * @returns <code>value</code> converted to an 8-bit channel value.
     */
    
    public byte gbaChanToRGB(byte value)
    {
        return this.convertChans(8, value);
    }
    
    /**
     * 
     * @param outputWidth the bit width of a single channel, for the result.
     * @param value       a single channel value of a 15-bit GBA colour.
     * @returns <code>value</code> converted to an 8-bit channel value.
     */
    
    private byte convertChans(byte outputWidth, byte value)
    {
        short rawval = value;
        rawval &= 0xFF;
        
        double percent = ((double)rawval) / 256.0;
        
        return (byte)(((double)outputWidth) * percent);
    }
    
    /**
     * 
     * @param isGBA tells if the colour values is 15-bit. Otherwise 24-bit is
     *              assumed.
     * @param value The combined R/G/B colour. If it is 15-bit, the value must
     *              be raw (completely signed).
     * @returns the retrieved red channel value.
     */
    
    public byte getRed(boolean isGBA, int value)
    {
        return this.getColorVal(isGBA, ColorChannel.Red, value);
    }
    
    /**
     * 
     * @param isGBA tells if the colour values is 15-bit. Otherwise 24-bit is
     *              assumed.
     * @param value The combined R/G/B colour. If it is 15-bit, the value must
     *              be raw (completely signed).
     * @returns the retrieved green channel value.
     */
    
    public byte getGreen(boolean isGBA, int value)
    {
        return this.getColorVal(isGBA, ColorChannel.Green, value);
    }
    
    /**
     * 
     * @param isGBA tells if the colour values is 15-bit. Otherwise 24-bit is
     *              assumed.
     * @param value The combined R/G/B colour. If it is 15-bit, the value must
     *              be raw (completely signed).
     * @returns the retrieved blue channel value.
     */
    
    public byte getBlue(boolean isGBA, int value)
    {
        return this.getColorVal(isGBA, ColorChannel.Blue, value);
    }
    
    /**
     * 
     * @param isGBA   tells if the colour values is 15-bit. Otherwise 24-bit is
     *                assumed.
     * @param channel a member of the ColourChannel enum, must be either Red,
     *                Green or Blue.
     * @param value   The combined R/G/B colour. If it is 15-bit, the value
     *                must be raw (completely signed).
     * @returns the retrieved channel value.
     */
    
    private byte getColorVal(boolean isGBA, ColorChannel channel, int value)
    {
        // these are the defaults, RGB channel width, colour offset for Red
        byte chanWidth    = 8;
        byte colourOffset = 0;
        
        if(isGBA)
        {
            chanWidth = 5;
        }
        
        if(channel == ColorChannel.Green)
        {
            colourOffset = 1;
        }
        else if(channel == ColorChannel.Blue)
        {
            colourOffset = 2;
        }
        
        value <<= 32 - chanWidth;
        return (byte)(value >>> (32 - (chanWidth * colourOffset)));
    }
}
