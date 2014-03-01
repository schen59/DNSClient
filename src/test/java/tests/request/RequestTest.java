package tests.request;

import enums.RRType;
import metadata.Header;
import metadata.QName;
import metadata.Question;
import request.Request;
import response.RawResponse;
import response.Response;
import response.ResponseBuilder;

import org.junit.Test;

/**
 * Test cases for Request class.
 * @author Shaofeng Chen
 * @since 2/19/14
 */
public class RequestTest {

    @Test
    public void testExecute() {
        Header header = new Header();
        header.asQuery()
                .withRecursionDesired()
                .withQdcount(1);
        QName qName = new QName("google.com");
        Question question = new Question(qName, RRType.MX);
        Request request = new Request();
        byte[] responseData = request.buildRequestTo("75.75.75.75").withHeader(header)
                .withQuestion(question).execute();
        RawResponse rawResponse = new RawResponse(responseData);
        Response response = ResponseBuilder.build(rawResponse);
        System.out.println(response.toString());
    }
}
