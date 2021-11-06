package interceptor;

public class REQUESTTYPE
{
    static final public int REQUESTTYPE_GET = 0;
    static final public int REQUESTTYPE_POST = 1;
    static final public int REQUESTTYPE_PUT = 2;
    static final public int REQUESTTYPE_DELETE = 3;
    static final public int REQUESTTYPE_HEAD = 4;
    static final public int REQUESTTYPE_CONNECT = 5;

    public static String toMethodName(int type)
    {
        switch (type)
        {
            case REQUESTTYPE_GET:
                return "GET";

            case REQUESTTYPE_POST:
                return "POST";

            case REQUESTTYPE_PUT:
                return "PUT";

            case REQUESTTYPE_DELETE:
                return "DELETE";

            case REQUESTTYPE_HEAD:
                return "HEAD";

            case REQUESTTYPE_CONNECT:
                return "CONNECT";

            default:
                return null;
        }
    }
}