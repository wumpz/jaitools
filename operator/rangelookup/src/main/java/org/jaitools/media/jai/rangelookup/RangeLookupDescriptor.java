/* 
 *  Copyright (c) 2009-2013, Michael Bedward. All rights reserved. 
 *   
 *  Redistribution and use in source and binary forms, with or without modification, 
 *  are permitted provided that the following conditions are met: 
 *   
 *  - Redistributions of source code must retain the above copyright notice, this  
 *    list of conditions and the following disclaimer. 
 *   
 *  - Redistributions in binary form must reproduce the above copyright notice, this 
 *    list of conditions and the following disclaimer in the documentation and/or 
 *    other materials provided with the distribution.   
 *   
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR 
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */   

package org.jaitools.media.jai.rangelookup;

import javax.media.jai.OperationDescriptorImpl;
import javax.media.jai.registry.RenderedRegistryMode;

/**
 * Describes the "RangeLookup" operation.
 * <p>
 * This is a variation on the JAI {@linkplain javax.media.jai.LookupDescriptor}.
 * It works with a {@linkplain RangeLookupTable} object in which each entry maps
 * a source image value range to a destination image value.
 * <p>
 * Example of use...
 * <pre><code>
 *
 * // Perform a lookup as follows:
 * //   Src Value     Dest Value
 * //     x < 5            1
 * //   5 <= x < 10        2
 * //  10 <= x <= 20       3
 * //  any other value    99
 * 
 * RenderedImage myIntImg = ...
 *
 * RangeLookupTable<Integer> table = new RangeLookupTable<Integer>(99);
 *
 * Range<Integer> r = new Range<Integer>(null, true, 5, false);  // x < 5
 * table.add(r, 1);
 *
 * r = new Range<Integer>(5, true, 10, false);  // 5 <= x < 10
 * table.add(r, 2);
 *
 * r = new Range<Integer>(10, true, 20, true);  // 10 <= x <= 20
 * table.add(r, 2);
 *
 * ParameterBlockJAI pb = new ParameterBlockJAI("rangelookup");
 * pb.setSource("source0", myIntImg);
 * pb.setParameter("table", table);
 * RenderedImage luImg = JAI.create("rangelookup", pb);
 * </code></pre>
 * 
 * <b>Parameters</b>
 * <table border="1">
 * <tr>
 * <th>Name</th><th>Type</th><th>Description</th><th>Default value</th>
 * </tr>
 * <tr>
 * <td>table</td>
 * <td>RangeLookupTable</td>
 * <td>Table mapping source value ranges to destination values</td>
 * <td>NO DEFAULT</td>
 * </tr>
 * <tr>
 * <td>default</td>
 * <td>Object</td>
 * <td>Specifies the value to return for source values that do not map to any
 * ranges in the lookup table. Can be either a single value (Number);
 * or null to indicate that unmatched source values should be passed
 * through</td>
 * <td>null (pass-through)</td>
 * </tr>
 * </table>
 *
 * @see org.jaitools.numeric.Range
 * @see RangeLookupTable
 * 
 * @author Michael Bedward
 * @since 1.0
 * @version $Id$
 */
public class RangeLookupDescriptor extends OperationDescriptorImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 6435703646431578734L;

    static final int TABLE_ARG = 0;
    static final int DEFAULT_ARG = 1;

    private static final String[] paramNames = {
        "table", 
        "default"
    };

    private static final Class<?>[] paramClasses = {
        RangeLookupTable.class, 
        Object.class
    };

    private static final Object[] paramDefaults = {
        NO_PARAMETER_DEFAULT,
        null
    };

    /** Constructor. */
    public RangeLookupDescriptor() {
        super(new String[][]{
                {"GlobalName", "RangeLookup"},
                {"LocalName", "RangeLookup"},
                {"Vendor", "org.jaitools.media.jai"},
                {"Description", "Maps source image value ranges to destination image values"},
                {"DocURL", "http://code.google.com/p/jaitools/"},
                {"Version", "1.0.0"},
        
                {"arg0Desc",
                 String.format("%s - table holding source value ranges mapped to "
                             + "destination values", paramNames[TABLE_ARG])},
                {"arg1Desc",
                 String.format("%s - value to use for unmatched source values "
                             + "(default: null for pass-through)", paramNames[DEFAULT_ARG])},
                },

                new String[]{RenderedRegistryMode.MODE_NAME},   // supported modes
                
                1,                                              // number of sources
                
                paramNames,
                paramClasses,
                paramDefaults,
                    
                null                                            // valid values (none defined)
                );


    }

}

