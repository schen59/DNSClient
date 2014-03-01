package request;

import enums.RRType;
import metadata.Header;
import metadata.QName;
import metadata.Question;

/**
 * Request builder to build a dns request object.
 * @author Shaofeng Chen
 * @since 2/19/14
 */
public class RequestBuilder {

    /**
     * Build the request object to the specified dns server with query name and query type.
     * @param server dns server to query to
     * @param queryName
     * @param rrType
     * @return Request
     */
    public static Request build(String server, String queryName, RRType rrType) {
        Header header = new Header();
        header.asQuery()
                .withRecursionDesired()
                .withQdcount(1);
        QName qName = new QName(queryName);
        Question question = new Question(qName, rrType);
        Request request = new Request();
        request.buildRequestTo(server).withHeader(header)
                .withQuestion(question);
        return request;
    }
}
