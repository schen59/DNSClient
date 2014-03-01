package factory;

import enums.RRClass;
import enums.RRType;
import metadata.AbstractName;
import metadata.CompositeName;
import metadata.Flags;
import metadata.Header;
import metadata.Label;
import metadata.Pointer;
import metadata.QName;
import metadata.Question;
import rdata.RData;
import metadata.ResourceRecord;
import response.RawResponse;
import util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory to create meta data type object from response data.
 * @author Shaofeng Chen
 * @since 2/21/14
 */
public class MetaDataFactory {

    /**
     * Create Flags type object from response data.
     * @param rawResponse
     * @return Flags
     */
    public static Flags createFlags(RawResponse rawResponse) {
        int flags = rawResponse.getU16();
        return new Flags(flags);
    }

    /**
     * Create Header type object from response data.
     * @param rawResponse
     * @return Header
     */
    public static Header createHeader(RawResponse rawResponse) {
        int id = rawResponse.getU16();
        Flags flags = createFlags(rawResponse);
        int qdcount = rawResponse.getU16();
        int ancount = rawResponse.getU16();
        int nscount = rawResponse.getU16();
        int arcount = rawResponse.getU16();
        Header header = new Header();
        header.setId(id);
        header.setFlags(flags);
        header.withQdcount(qdcount)
                .withAncount(ancount)
                .withNscount(nscount)
                .withArcount(arcount);
        return header;
    }

    /**
     * Create Label type object from response data.
     * @param rawResponse
     * @return Label
     */
    public static Label createLabel(RawResponse rawResponse) {
        int componentLength = rawResponse.getU8();
        String name = "";
        while (componentLength-- > 0) {
            name += (char)(rawResponse.getU8());
        }
        return new Label(name);
    }

    /**
     * Create QName type object from response data.
     * @param rawResponse
     * @return QName
     */
    public static QName createQName(RawResponse rawResponse) {
        int componentLength = rawResponse.peekU8();
        QName qName = new QName();
        while (componentLength != 0) {
            Label label = createLabel(rawResponse);
            qName.addLabel(label);
            componentLength = rawResponse.peekU8();
        }
        rawResponse.getU8();
        return qName;
    }

    /**
     * Create Question type object from response data.
     *
     * @param rawResponse
     * @param qdcounts
     * @return Question
     */
    public static List<Question> createQuestions(RawResponse rawResponse, int qdcounts) {
        List<Question> questions = new ArrayList<Question>();
        while (qdcounts-- > 0) {
            QName qname = createQName(rawResponse);
            RRType qtype = EnumFactory.createRRType(rawResponse.getU16());
            RRClass qclass = EnumFactory.createRRClass(rawResponse.getU16());
            questions.add(new Question(qname, qtype, qclass));
        }
        return questions;
    }

    /**
     * Create the specified number of ResourceRecord type objects from response data.
     * @param rawResponse
     * @param counts
     * @return List
     */
    public static List<ResourceRecord> createResourceRecords(RawResponse rawResponse, int counts) {
        List<ResourceRecord> resourceRecords = new ArrayList<ResourceRecord>();
        while (counts-- > 0) {
            ResourceRecord resourceRecord = createResourceRecord(rawResponse);
            resourceRecords.add(resourceRecord);
        }
        return resourceRecords;
    }

    /**
     * Create Pointer type object from response data.
     * @param rawResponse
     * @return Pointer
     */
    public static Pointer createPointer(RawResponse rawResponse) {
        int pointTo = rawResponse.getPointer();
        int cursor = rawResponse.getCursor();
        rawResponse.seek(pointTo);
        CompositeName name = createCompositeName(rawResponse);
        rawResponse.seek(cursor);
        return new Pointer(name);
    }

    /**
     * Create CompositeName type object from response data.
     * @param rawResponse
     * @return CompositeName
     */
    public static CompositeName createCompositeName(RawResponse rawResponse) {
        CompositeName compositeName = new CompositeName();
        while (!CommonUtil.isPointer(rawResponse.peekU8()) && !CommonUtil.isLabelEnds(rawResponse
                .peekU8())) {
            Label label = createLabel(rawResponse);
            compositeName.addComponent(label);
        }
        if (CommonUtil.isPointer(rawResponse.peekU8())) {
            compositeName.addComponent(createPointer(rawResponse));
        } else {
            rawResponse.getU8();
        }
        return compositeName;
    }

    /**
     * Create ResourceRecord type object from response data.
     * @param rawResponse
     * @return ResourceRecord
     */
    public static ResourceRecord createResourceRecord(RawResponse rawResponse) {
        AbstractName name;
        if (CommonUtil.isPointer(rawResponse.peekU8())) {
            name = createPointer(rawResponse);
        } else {
            name = createQName(rawResponse);
        }
        RRType rrType = EnumFactory.createRRType(rawResponse.getU16());
        RRClass rrClass = EnumFactory.createRRClass(rawResponse.getU16());
        long ttl = rawResponse.getU32();
        int rLength = rawResponse.getU16();
        RData rData = RDataFactory.createRDataWithType(rawResponse, rrType.getValue());
        return new ResourceRecord(name, rrType, rrClass, ttl, rLength, rData);
    }
}
