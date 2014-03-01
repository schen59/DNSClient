package tests.util;

import util.CommonUtil;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for CommonUtil Class.
 * @author Shaofeng Chen
 * @since 2/18/14
 */
public class CommonUtilTest {

    @Test
    public void testToU16From() {
        byte high = (byte)(0x5f & 0xff);
        byte low = (byte)(0xe1 & 0xff);
        int u16 = CommonUtil.toU16From(high, low);
        assertEquals(24545, u16);
    }

    @Test
    public void testIsPointer() {
        int pointer = 0xc000;
        assertTrue(CommonUtil.isPointer(pointer));
    }

    @Test
    public void testToU32From() {
        byte first = 120;
        byte second = 11;
        byte third = -120;
        byte fourth = -88;
        assertEquals(2014021800, CommonUtil.toU32From(first, second, third, fourth));
    }
}
