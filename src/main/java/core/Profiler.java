package core;

import request.Request;

/**
 * Profiler for profile the performance for dns request.
 * @author Shaofeng Chen
 * @since 2/25/14
 */
public class Profiler {
    private long start;
    private long end;
    private int messageSize;

    private void start() {
        start = System.currentTimeMillis();
    }

    private void end() {
        end = System.currentTimeMillis();
    }

    public long getRuntime() {
        return end - start;
    }

    public byte[] profile(Request request) {
        start();
        byte[] response = request.execute();
        end();
        messageSize = response.length;
        return response;

    }

    public int getMessageSize() {
        return messageSize;
    }
}
