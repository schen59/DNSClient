package tests.common;

import enums.Opcode;
import enums.Rcode;
import metadata.Flags;
import metadata.Header;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for Header class.
 * @author Shaofeng Chen
 * @since 2/17/14
 */
public class HeaderTest {

    @Test
    public void testAsQuery() {
        Header header = new Header();
        header.asQuery();
        assertTrue(header.isQuery());
    }

    @Test
    public void testIsQuery() {
        Header header = new Header();
        Flags flags = new Flags(0xffff);
        header.setFlags(flags);
        assertFalse(header.isQuery());
    }

    @Test
    public void testWithOpcode() {
        Header header = new Header();
        Flags flags = new Flags(0x0000);
        header.setFlags(flags);
        header.withOpcode(Opcode.QUERY);
        assertEquals(0x0000, header.getFlags().getU16());
    }

    @Test
    public void testWithRecursionDesired() {
        Header header = new Header();
        Flags flags = new Flags(0x0000);
        header.setFlags(flags);
        header.withRecursionDesired();
        assertEquals(0x0100, header.getFlags().getU16());
    }

    @Test
    public void testIsAuthoritativeAnswer() {
        Header header = new Header();
        Flags flags = new Flags(0x0400);
        header.setFlags(flags);
        assertTrue(header.isAuthoritativeAnswer());
    }

    @Test
    public void testIsTruncated() {
        Header header = new Header();
        Flags flags = new Flags(0x0200);
        header.setFlags(flags);
        assertTrue(header.isTruncated());
    }

    @Test
    public void testIsRecursionAvailable() {
        Header header = new Header();
        Flags flags = new Flags(0x0080);
        header.setFlags(flags);
        assertTrue(header.isRecursionAvailable());
    }

    @Test
    public void testGetOpcode() {
        Header header = new Header();
        Flags flags = new Flags(0x0800);
        header.setFlags(flags);
        assertEquals(Opcode.IQUERY.getValue(), header.getOpcode().getValue());
    }

    @Test
    public void testGetRcode() {
        Header header = new Header();
        Flags flags = new Flags(0x0002);
        header.setFlags(flags);
        assertEquals(Rcode.SERVER_FAILURE.getValue(), header.getRcode().getValue());
    }

    @Test
    public void testToByteArray() {
        Header header = new Header();
        header.setId(290);
        header.asQuery().withOpcode(Opcode.QUERY)
                .withQdcount(1)
                .withAncount(0)
                .withNscount(0)
                .withArcount(0);
        byte[] bytes = header.toByteArray();
        byte[] expected = new byte[] {0x01, 0x22, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00};
        assertArrayEquals(expected, bytes);
    }
}
