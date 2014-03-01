package response;

import metadata.Header;
import metadata.Question;
import metadata.ResourceRecord;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static common.Constants.*;

/**
 * Class which holds the decoded response data.
 * @author Shaofeng Chen
 * @since 2/19/14
 */
public class Response {
    private Header header;
    private List<Question> questions;
    private List<ResourceRecord> answers;
    private List<ResourceRecord> authorityAnswers;
    private List<ResourceRecord> additionalAnswers;

    /**
     * Set header part for the response object.
     * @param header
     * @return Response
     */
    public Response withHeader(Header header) {
        this.header = header;
        return this;
    }

    /**
     * Set questions part for the response object.
     * @param questions
     * @return Response
     */
    public Response withQuestion(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    /**
     * Set answers for the response object.
     * @param answers
     * @return Response
     */
    public Response withAnswers(List<ResourceRecord> answers) {
        this.answers = answers;
        return this;
    }

    /**
     * Set authortiy answer section for the response object.
     * @param authorityAnswers
     * @return Resposne
     */
    public Response withAuthorityAnswers(List<ResourceRecord> authorityAnswers) {
        this.authorityAnswers = authorityAnswers;
        return this;
    }

    /**
     * Set additional answer section for the response object.
     * @param additionalAnswers
     * @return Response
     */
    public Response withAdditionalAnswers(List<ResourceRecord> additionalAnswers) {
        this.additionalAnswers = additionalAnswers;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GOT_ANSWER).append(NEW_LINE)
                .append(header.toString()).append(NEW_LINE);
        if (!questions.isEmpty()) {
            stringBuilder.append(QUESTION_SECTION).append(NEW_LINE)
                    .append(stringtify(questions)).append(NEW_LINE);
        }
        if (answers.size() > 0) {
            stringBuilder.append(ANSWER_SECTION).append(NEW_LINE)
                    .append(stringtify(answers)).append(NEW_LINE);
        }
        if (authorityAnswers.size() > 0) {
            stringBuilder.append(AUTHORITY_SECTION).append(NEW_LINE)
                    .append(stringtify(authorityAnswers)).append(NEW_LINE);
        }
        if (additionalAnswers.size() > 0) {
            stringBuilder.append(ADDITIONAL_SECTION).append(NEW_LINE)
                    .append(stringtify(additionalAnswers)).append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    private String stringtify(List<?> objects) {
        List<String> objStrings = new ArrayList<String>();
        for (Object obj : objects) {
            objStrings.add(obj.toString());
        }
        return StringUtils.join(objStrings, NEW_LINE);
    }
}
