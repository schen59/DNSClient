package metadata;

import java.nio.ByteBuffer;

/**
 * Class to deal with label related manipulation.
 * @author Shaofeng Chen
 * @since 2/20/14
 */
public class Label extends AbstractName {

    private String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getByteLength() {
        return name.length() + 1;
    }

    public byte[] toByteArray() {
        ByteBuffer buffer = ByteBuffer.allocate(name.length()+1);
        buffer.put((byte)name.length());
        for (char c : name.toCharArray()) {
            buffer.put((byte)c);
        }
        return buffer.array();
    }
}
