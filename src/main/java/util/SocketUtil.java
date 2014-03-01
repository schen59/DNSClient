package util;

import exception.DNSClientException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import static common.Messages.SEND_PACKET_ERR;
import static common.Messages.CREATE_UDP_SOCKET_ERR;
import static common.Messages.TIMEOUT_ERR;
import static common.Messages.RECEIVE_PACKET_ERR;
import static common.Constants.*;

/**
 * Utility functions for udp related socket manipulation.
 * @author Shaofeng Chen
 * @since 2/19/14
 */
public class SocketUtil {

    /**
     * Create datagram socket.
     * @return DatagramSocket
     */
    public static DatagramSocket createSocket() {
        try {
            return new DatagramSocket();
        } catch (SocketException ex) {
            throw new DNSClientException(CREATE_UDP_SOCKET_ERR, ex);
        }
    }

    /**
     * Send packet through the specified datagram socket.
     * @param socket
     * @param packet
     */
    public static void sendPacket(DatagramSocket socket, DatagramPacket packet) {
        try {
            socket.send(packet);

        } catch (IOException ex) {
            throw new DNSClientException(String.format(SEND_PACKET_ERR, packet.getAddress().getHostName()), ex);
        }
    }

    /**
     * Receive byte data from the specified datagram socket.
     * @param socket
     * @return byte[]
     */
    public static byte[] receiveFrom(DatagramSocket socket) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.setSoTimeout(SOCKET_TIME_OUT_MSEC);
            socket.receive(packet);
            outputStream.write(packet.getData(), packet.getOffset(), packet.getLength());
        } catch (SocketTimeoutException ex) {
            if (outputStream.size() == 0) {
                throw new DNSClientException(TIMEOUT_ERR, ex);
            }
        } catch (IOException ex) {
            throw new DNSClientException(RECEIVE_PACKET_ERR, ex);
        }
        return outputStream.toByteArray();
    }
}
