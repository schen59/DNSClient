package rdata;

/**
 * Class for AAAA type RData.
 * @author Shaofeng Chen
 * @since 2/22/14
 */
public class AAAARData extends RData {
    private final byte[] bytes;

    public AAAARData(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return stringtify();
    }

    private String stringtify() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if ((0xff & bytes[i]) < 0x10) {
                stringBuilder.append("0" + Integer.toHexString((0xFF & bytes[i])));
            } else {
                stringBuilder.append(Integer.toHexString(0xFF & bytes[i]));
            }
        }
        return stringBuilder.toString();
    }
}
