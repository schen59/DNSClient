package metadata;

import enums.Opcode;
import enums.Rcode;
import factory.EnumFactory;
import util.CommonUtil;

/**
 * Class to deal with bit related manipulation for flags.
 * @author Shaofeng Chen
 * @since 2/22/14
 */
public class Flags {
    private int flags;

    public Flags() {
        flags = 0;
    }

    public Flags(int flags) {
        this.flags = flags;
    }

    public int getU16() {
        return CommonUtil.getU16From(flags);
    }

    public void resetBitAt(int index) {
        flags &= (0xff - 1 << (15 - index));
    }

    public void setBitAt(int index) {
        flags |= (1 << (15 - index));
    }

    public int getBitAt(int index) {
        return (flags & (1 << (15 - index))) >> (15 - index);
    }

    public void setOpcode(Opcode opcode) {
        int value = opcode.getValue();
        flags &= 0x87ff;
        flags |= value << 11;
    }

    public Opcode getOpcode() {
        return EnumFactory.createOpcode((flags & 0x7800) >> 11);
    }

    public Rcode getRcode() {
        return EnumFactory.createRcode(flags & 0xf);
    }
}
