/*
 * Copyright (C) 2005-2007 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */
/*
 * Created on 03.10.2003
 *
 */
package crystal.common.data;

/**
 * @author Oleh Hapon
 */
public class SystemInfo {

    public static final String WINDOWS_PLATFORM = "windows";

    public static final String LINUX_PLATFORM = "linux";

    public static final String OS2_PLATFORM = "os2";

    public static final String MAC_PLATFORM = "mac";

    public static final String JAVA_VERSION = System
	    .getProperty("java.version");

    public static final String OS_NAME = System.getProperty("os.name");

    public static final String OS_NAME_LOWER_CASE = OS_NAME.toLowerCase();

    public static final boolean isWindows = OS_NAME_LOWER_CASE
	    .startsWith("windows");

    public static final boolean isWindowsNT = OS_NAME_LOWER_CASE
	    .startsWith("windows nt");

    public static final boolean isWindows2000 = OS_NAME_LOWER_CASE
	    .startsWith("windows 2000");

    public static final boolean isWindowsXP = OS_NAME_LOWER_CASE
	    .startsWith("windows xp");

    public static final boolean isWindows9x = OS_NAME_LOWER_CASE
	    .startsWith("windows 9")
	    || OS_NAME_LOWER_CASE.startsWith("windows me");

    public static final boolean isOS2 = OS_NAME_LOWER_CASE.startsWith("os/2")
	    || OS_NAME_LOWER_CASE.startsWith("os2");

    public static final boolean isMac = OS_NAME_LOWER_CASE.startsWith("mac");

    public static final boolean isUnix = !isWindows && !isOS2 && !isMac;

    public static final boolean isFileSystemCaseSensitive = !isWindows
	    && !isOS2 && !isMac;

    public static final String getPlatform() {
	if (isWindows) {
	    return WINDOWS_PLATFORM;
	} else if (isOS2) {
	    return OS2_PLATFORM;
	} else if (isMac) {
	    return MAC_PLATFORM;
	} else {
	    return LINUX_PLATFORM;
	}
    }

}
