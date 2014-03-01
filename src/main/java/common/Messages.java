package common;

/**
 * Error messages.
 * @author Shaofeng Chen
 * @since 2/23/14
 */
public class Messages {

    public static final String UNKNOWN_RR_TYPE = "Unknown RR type %s.";
    public static final String UNKNOWN_RR_CLASS = "Unknown RR class %s.";
    public static final String UNKNOWN_OPCODE = "Unknown Opcode %s.";
    public static final String UNKNOWN_RCODE = "Unknown Rcode %s.";
    public static final String MESSAGE_ID_ERR = "DNS message id should be 16 bits long.";
    public static final String EOF_ERR = "EOF error.";
    public static final String UNKNOWN_HOST = "Unknow host %s.";
    public static final String SEND_PACKET_ERR = "Can not send packet to %s.";
    public static final String CREATE_UDP_SOCKET_ERR = "Can not create datagrame socket.";
    public static final String TIMEOUT_ERR = "Timeout while receiving data.";
    public static final String RECEIVE_PACKET_ERR = "Can not receive packet.";
}
