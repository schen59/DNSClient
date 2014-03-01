package tests.request;

import enums.RRType;
import metadata.QName;
import metadata.Question;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Test cases for Question class.
 * @author Shaofeng Chen
 * @since 2/17/14
 */
public class QuestionTest {
    @Test
    public void testToByteArray() {
        String queryName = "www.google.com";
        QName qName = new QName(queryName);
        RRType qtype = RRType.A;
        Question question = new Question(qName, qtype);
        byte[] bytes = question.toByteArray();
        byte[] expected = new byte[] {0x03, 0x77, 0x77, 0x77, 0x06, 0x67, 0x6f, 0x6f, 0x67, 0x6c,
                0x65, 0x03, 0x63, 0x6f, 0x6d, 0x00, 0x00, 0x01, 0x00, 0x01};
        assertArrayEquals(expected, bytes);
    }
}
