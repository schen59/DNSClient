package request;

import metadata.Header;
import metadata.Question;
import util.CommonUtil;
import util.SocketUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import static common.Constants.DNS_PORT;

/**
 * Class to deal with dns request.
 * @author Shaofeng Chen
 * @since 2/19/14
 */
public class Request {
    private Header header;
    private Question question;
    private String server;

    /**
     * Build request against the specified dns server.
     * @param server
     * @return Request
     */
    public Request buildRequestTo(String server) {
        this.server = server;
        return this;
    }

    /**
     * Build request with the specified header.
     * @param header
     * @return Request
     */
    public Request withHeader(Header header) {
        this.header = header;
        return this;
    }

    /**
     * Build request with the specified question.
     * @param question
     * @return Request
     */
    public Request withQuestion(Question question) {
        this.question = question;
        return this;
    }

    /**
     * Execute the request to get byte response data.
     * @return byte[]
     */
    public byte[] execute() {
        DatagramSocket socket = SocketUtil.createSocket();
        InetAddress address = CommonUtil.getInetAddressFrom(server);
        DatagramPacket packet = new DatagramPacket(toByteArray(), getByteLength(), address, DNS_PORT);
        SocketUtil.sendPacket(socket, packet);
        return SocketUtil.receiveFrom(socket);
    }

    private byte[] toByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(getByteLength());
        byteBuffer.put(header.toByteArray());
        byteBuffer.put(question.toByteArray());
        return byteBuffer.array();
    }

    private int getByteLength() {
        return Header.getBytesLength() + question.getByteLength();
    }

}
