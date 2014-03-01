package response;

import factory.MetaDataFactory;
import metadata.Header;
import metadata.Question;
import metadata.ResourceRecord;

import java.util.List;

/**
 * Response builder which builds a decoded response object from raw response data.
 * @author Shaofeng Chen
 * @since 2/19/14
 */
public class ResponseBuilder {

    /**
     * Build response object from raw response data stream.
     * @param rawResponse
     * @return Response
     */
    public static Response build(RawResponse rawResponse) {
        Header header = MetaDataFactory.createHeader(rawResponse);
        List<Question> questions = MetaDataFactory.createQuestions(rawResponse, header.getQdcount());
        List<ResourceRecord> answers = MetaDataFactory.createResourceRecords(rawResponse, header.getAncount());
        List<ResourceRecord> authorityAnswers = MetaDataFactory.createResourceRecords(rawResponse,
                header.getNscount());
        List<ResourceRecord> additionalAnswers = MetaDataFactory.createResourceRecords(rawResponse,
                header.getArcount());
        Response response = new Response();
        response.withHeader(header)
                .withQuestion(questions)
                .withAnswers(answers)
                .withAuthorityAnswers(authorityAnswers)
                .withAdditionalAnswers(additionalAnswers);
        return response;
    }
}
