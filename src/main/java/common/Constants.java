package common;

/**
 * Class for constants.
 * @author Shaofeng Chen
 * @since 2/22/14
 */
public class Constants {

    public static final int DNS_PORT = 53;

    public static final int RRTYPE_BYTE_LEN = 2;
    public static final int RRCLASS_BYTE_LEN = 2;

    public static final int RRTYPE_A = 1;
    public static final int RRTYPE_NS = 2;
    public static final int RRTYPE_CNAME = 5;
    public static final int RRTYPE_SOA = 6;
    public static final int RRTYPE_WKS = 11;
    public static final int RRTYPE_PTR = 12;
    public static final int RRTYPE_MX = 15;
    public static final int RRTYPE_AAAA = 28;
    public static final int RRTYPE_SRV = 33;
    public static final int RRTYPE_A6 = 38;
    public static final int RRTYPE_ANY = 255;

    public static final int RRCLASS_IN = 1;
    public static final int RRCLASS_CH = 3;
    public static final int RRCLASS_HS = 4;
    public static final int RRCLASS_NONE = 254;
    public static final int RRCLASS_ANY = 255;

    public static final int OPCODE_QUERY = 0;
    public static final int OPCODE_IQUERY = 1;
    public static final int OPCODE_STATUS = 2;

    public static final int RCODE_NO_ERROR = 0;
    public static final int RCODE_FMT_ERROR = 1;
    public static final int RCODE_SERVER_FAILURE = 2;
    public static final int RCODE_NAME_ERROR = 3;
    public static final int RCODE_NOT_IMPLEMENTED = 4;
    public static final int RCODE_REFUSED = 5;

    public static final int IPV6_BYTE_LEN = 16;

    public static final String HEADER_FMT = ";; ->>HEADER<<- opcode: %s, status: %s, id: %s\n" +
            ";; flags: %s; QUERY: %s, ANSWER: %s, AUTHORITY: %s, ADDITIONAL: %s";
    public static final String QUESTION_FMT = ";%s.          %s  %s";
    public static final String RESOURCE_RECORD_FMT = "%s.       %s  %s  %s  %s";
    public static final String RDATA_A_FMT = "%s.%s.%s.%s";
    public static final String RDATA_MX_FMT = "%s %s.";
    public static final String RDATA_SOA_FMT = "%s. %s. %s %s %s %s %s";

    public static final String FLAGS_QR = "qr";
    public static final String FLAGS_AA = "aa";
    public static final String FLAGS_RD = "rd";
    public static final String FLAGS_RA = "ra";
    public static final String FLAGS_TC = "tc";

    public static final String NEW_LINE = "\n";
    public static final String GOT_ANSWER = ";; Got answer:";
    public static final String QUESTION_SECTION = ";; QUESTION SECTION:";
    public static final String ANSWER_SECTION = ";; ANSWER SECTION:";
    public static final String AUTHORITY_SECTION = ";; AUTHORITY SECTION:";
    public static final String ADDITIONAL_SECTION = ";; ADDITIONAL SECTION:";

    public static final String DNS_CLIENT_NAME_FMT = "; <<>> %s <<>> %s";
    public static final String QUERY_TIME_FMT = ";; Query time: %s mec";
    public static final String SERVER_ADDR_FMT = ";; SERVER: %s#53";
    public static final String TIMESTAMP_FMT = ";; WHEN: %s";
    public static final String MSG_SIZE_FMT = ";; MSG SIZE rcvd: %s";
    public static final String USAGE_INFO = "Usage: java -cp jar-name core.DNSClient 'server' " +
            "'query' 'type'";

    public static final String QTYPE_A = "A";
    public static final String QTYPE_MX = "MX";
    public static final String QTYPE_NS = "NS";
    public static final String QTYPE_SOA = "SOA";

    public static final int SOCKET_TIME_OUT_MSEC = 1000;
    public static final int BUFFER_SIZE = 1024;
}
