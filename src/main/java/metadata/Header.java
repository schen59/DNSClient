package metadata;

import enums.Opcode;
import enums.Rcode;
import exception.DNSClientException;

import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static common.Messages.MESSAGE_ID_ERR;
import static common.Constants.*;

/**
 * Class to represent the dns query header.
 * @author Shaofeng Chen
 * @since 2/17/14
 */
public class Header {
    private int id;
    private Flags flags;
    private int qdcount;
    private int ancount;
    private int nscount;
    private int arcount;

    private static Random random = new Random();

    public Header() {
        id = random.nextInt(0xffff);
        flags = new Flags();
        qdcount = 0;
        ancount = 0;
        nscount = 0;
        arcount = 0;
    }

    public void setId(int id) {
        if (id < 0 || id > 0xffff) {
            throw new DNSClientException(MESSAGE_ID_ERR);
        } else {
            this.id = id;
        }
    }

    public int getId() {
        return id;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Flags getFlags() {
        return flags;
    }

    public Header asQuery() {
        flags.resetBitAt(0);
        return this;
    }

    public boolean isQuery() {
        return flags.getBitAt(0) == 0;
    }

    public boolean isAuthoritativeAnswer() {
        return flags.getBitAt(5) == 1;
    }

    public boolean isTruncated() {
        return flags.getBitAt(6) == 1;
    }

    public boolean isRecursionAvailable() {
        return flags.getBitAt(8) == 1;
    }

    public Header withRecursionDesired() {
        flags.setBitAt(7);
        return this;
    }

    public boolean isRecursionDesired() {
        return flags.getBitAt(7) == 1;
    }

    public Header withOpcode(Opcode opcode) {
        flags.setOpcode(opcode);
        return this;
    }

    public Opcode getOpcode() {
        return flags.getOpcode();
    }

    public Rcode getRcode() {
        return flags.getRcode();
    }

    public int getQdcount() {
        return qdcount;
    }

    public Header withQdcount(int qdcount) {
        this.qdcount = qdcount;
        return this;
    }

    public int getNscount() {
        return nscount;
    }

    public Header withNscount(int nscount) {
        this.nscount = nscount;
        return this;
    }

    public int getArcount() {
        return arcount;
    }

    public Header withArcount(int arcount) {
        this.arcount = arcount;
        return this;
    }

    public int getAncount() {
        return ancount;
    }

    public Header withAncount(int ancount) {
        this.ancount = ancount;
        return this;
    }

    public byte[] toByteArray() {
        int bytesLength = getBytesLength();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytesLength);
        byteBuffer.putShort((short) id);
        byteBuffer.putShort((short) flags.getU16());
        byteBuffer.putShort((short) qdcount);
        byteBuffer.putShort((short) ancount);
        byteBuffer.putShort((short) nscount);
        byteBuffer.putShort((short) arcount);
        return byteBuffer.array();
    }

    public static int getBytesLength() {
        return 12;
    }

    public String toString() {

        return String.format(HEADER_FMT, getOpcode().getName(), getRcode().getName(), id,
                getFlagsString(), getQdcount(), getAncount(), getNscount(), getArcount());
    }

    private String getFlagsString() {
        List<String> flags = new ArrayList<String>();
        if (!isQuery()) {
            flags.add(FLAGS_QR);
        }
        if (isAuthoritativeAnswer()) {
            flags.add(FLAGS_AA);
        }
        if (isTruncated()) {
            flags.add(FLAGS_TC);
        }
        if (isRecursionDesired()) {
            flags.add(FLAGS_RD);
        }
        if (isRecursionAvailable()) {
            flags.add(FLAGS_RA);
        }

        return StringUtils.join(flags, " ");
    }
}
