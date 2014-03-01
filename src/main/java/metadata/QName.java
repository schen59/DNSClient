package metadata;

import util.CommonUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to deal with qname related manipulation.
 * @author Shaofeng Chen
 * @since 2/20/14
 */
public class QName extends AbstractName {
    private List<Label> labels;

    public QName() {
        labels = new ArrayList<Label>();
    }

    public QName(String qname) {
        labels = new ArrayList<Label>();
        String[] components = CommonUtil.getQnameComponents(qname);
        for (String component : components) {
            labels.add(new Label(component));
        }
    }

    public String getName() {
        return getName(labels);
    }

    public void addLabel(Label label) {
        labels.add(label);
    }

    public int getByteLength() {
        int byteLength = 0;
        for (Label label : labels) {
            byteLength += label.getByteLength();
        }
        return byteLength + 1;
    }

    public byte[] toByteArray() {
        int byteLength = getByteLength();
        ByteBuffer buffer = ByteBuffer.allocate(byteLength);
        for (Label label : labels) {
            buffer.put(label.toByteArray());
        }
        buffer.put((byte)0);
        return buffer.array();
    }
}
