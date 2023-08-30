/*
 * Copyright (c) 2018. Ernesto Castellotti <erny.castell@gmail.com>
 * This file is part of JTdlib.
 *
 *     JTdlib is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License.
 *
 *     JTdlib is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with JTdlib.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.tdlight;

import it.tdlight.util.UnsupportedNativeLibraryException;
import it.tdlight.util.Native;
import it.tdlight.jni.TdApi.LogStreamEmpty;
import it.tdlight.jni.TdApi.SetLogStream;
import it.tdlight.jni.TdApi.SetLogVerbosityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initialize TDLight
 */
public final class Init {

	public static final Logger LOG = LoggerFactory.getLogger("it.tdlight.TDLight");

	private static volatile boolean started = false;

	/**
	 * Initialize TDLight.
	 * This method is idempotent.
	 *
	 * @throws UnsupportedNativeLibraryException An exception that is thrown when the LoadLibrary class fails to load the library.
	 */
	public static void init() throws UnsupportedNativeLibraryException {
		if (!started) {
			boolean shouldStart = false;
			synchronized (Init.class) {
				if (!started) {
					started = true;
					shouldStart = true;
				}
			}
			if (shouldStart) {
				Native.loadNativesInternal();
				ConstructorDetector.init();
				try {
					NativeClientAccess.execute(new SetLogVerbosityLevel(3));
					Log.setLogMessageHandler(3, new Slf4JLogMessageHandler());
					Log.setLogStream(null);
					NativeClientAccess.execute(new SetLogStream(new LogStreamEmpty()));
				} catch (Throwable ex) {
					LOG.error("Can't set verbosity level on startup", ex);
				}
			}
		}
	}

}
