package kotyoxutils;

/**
 * Created by wei.
 * Date: 2019/1/6 下午10:17
 * Description:
 */
public class XLog {
    public interface XLogDelegate {
        void e(final String tag, final String msg, final Object... obj);

        void w(final String tag, final String msg, final Object... obj);

        void i(final String tag, final String msg, final Object... obj);

        void d(final String tag, final String msg, final Object... obj);

        void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj);
    }

    private static XLogDelegate sDelegete = null;

    public static void setDelegete(XLogDelegate delegete) {
        sDelegete = delegete;
    }

    public static void e(final String tag, final String msg, final Object... obj) {
        if (sDelegete != null) {
            sDelegete.e(tag, msg, obj);
        }
    }

    public static void w(final String tag, final String msg, final Object... obj) {
        if (sDelegete != null) {
            sDelegete.w(tag, msg, obj);
        }
    }

    public static void i(final String tag, final String msg, final Object... obj) {
        if (sDelegete != null) {
            sDelegete.i(tag, msg, obj);
        }
    }

    public static void d(final String tag, final String msg, final Object... obj) {
        if (sDelegete != null) {
            sDelegete.d(tag, msg, obj);
        }
    }

    public static void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj) {
        if (sDelegete != null) {
            sDelegete.printErrStackTrace(tag, tr, format, obj);
        }
    }
}
