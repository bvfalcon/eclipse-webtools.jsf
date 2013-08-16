package org.eclipse.jst.jsf.ui.internal;

import org.eclipse.core.runtime.Platform;

/**
 * Defines that standard runtime trace options for debugging. See .options file
 * for definitions.
 * 
 * @author cbateman
 * 
 */
public final class JSFUITraceOptions
{
    /**
     * True if debug tracing is enabled. Other tracing cannot be enabled unless
     * this is enabled.
     */
    public static final boolean ENABLED;

    /**
     * True if development mode for meta-data generation is enabled
     */
    public static final boolean TRACE_METADATAGEN;

    private static final String KEY_DEBUG_ENABLED = "/debug"; //$NON-NLS-1$
    private static final String KEY_DESIGNTIME = "/metadatagen"; //$NON-NLS-1$

    
    static
    {
        ENABLED = getBooleanOption(JSFUiPlugin.PLUGIN_ID
                        + KEY_DEBUG_ENABLED);

        if (ENABLED)
        {
            TRACE_METADATAGEN = getBooleanOption(
                    JSFUiPlugin.PLUGIN_ID + KEY_DESIGNTIME);
        }
        else
        {
            TRACE_METADATAGEN = false;
        }
    }

    private static boolean getBooleanOption(String key)
    {
    	Boolean enabled = Boolean.valueOf(Platform.getDebugOption(key));
    	return enabled != null ? enabled.booleanValue() : false;
    }

    /**
     * @param message
     */
    public static void log(final String message)
    {
        System.out.println(message);
    }
    
    /**
     * @param msg A short label placed before the trace of t to show the source
     * @param t
     */
    public static void log(final String msg, final Throwable t)
    {
        System.out.printf("%s: Exception Trace:\n\n",msg); //$NON-NLS-1$
        t.printStackTrace(System.out);
        System.out.print("\n\n\n"); //$NON-NLS-1$
    }

    private JSFUITraceOptions()
    {
        // no instantiation
    }
}
