package metadata;

import enums.RRClass;
import enums.RRType;

import java.nio.ByteBuffer;

import static common.Constants.*;

/**
 * Class to deal with question related manipulation.
 * @author Shaofeng Chen
 * @since 2/17/14
 */
public class Question {

    private final QName qname;
    private final RRType qtype;
    private final RRClass qclass;

    public Question(QName qname, RRType qtype) {
        this.qname = qname;
        this.qtype = qtype;
        qclass = RRClass.IN;
    }

    public Question(QName qname, RRType qtype, RRClass qclass) {
        this.qname = qname;
        this.qtype = qtype;
        this.qclass = qclass;
    }

    public QName getQname() {
        return qname;
    }

    public RRType getQtype() {
        return qtype;
    }

    public RRClass getQclass() {
        return qclass;
    }

    public byte[] toByteArray() {
        int bytesLength = getByteLength();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytesLength);
        byteBuffer.put(qname.toByteArray());
        byteBuffer.putShort((short) qtype.getValue());
        byteBuffer.putShort((short) qclass.getValue());
        return byteBuffer.array();
    }

    public int getByteLength() {
        return qname.getByteLength() + RRType.getByteLength() +
                RRClass.getByteLength();
    }

    @Override
    public String toString() {
        return String.format(QUESTION_FMT, qname.getName(), qclass.getName(), qtype.getName());
    }
}
