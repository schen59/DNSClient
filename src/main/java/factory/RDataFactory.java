package factory;

import exception.DNSClientException;
import metadata.AbstractName;
import rdata.AAAARData;
import rdata.ARData;
import rdata.CNameRData;
import rdata.MXRData;
import rdata.NSRData;
import rdata.RData;
import rdata.SOARData;
import response.RawResponse;

import static common.Messages.UNKNOWN_RR_TYPE;
import static common.Constants.*;

/**
 * Factory to create RData type object from response data.
 * @author Shaofeng Chen
 * @since 2/22/14
 */
public class RDataFactory {

    /**
     * Create SOA type RData type object from response data.
     * @param rawResponse
     * @return SOARData
     */
    public static SOARData createSOARData(RawResponse rawResponse) {
        AbstractName primaryNS = MetaDataFactory.createCompositeName(rawResponse);
        AbstractName adminNS = MetaDataFactory.createCompositeName(rawResponse);
        long serialNumber = rawResponse.getU32();
        long refreshInterval = rawResponse.getU32();
        long refreshLimit = rawResponse.getU32();
        long expirationLimit = rawResponse.getU32();
        long minimumTTL = rawResponse.getU32();
        return new SOARData(primaryNS, adminNS, serialNumber, refreshInterval, refreshLimit,
                expirationLimit, minimumTTL);
    }

    /**
     * Create the specified type of RData from response data.
     * @param rawResponse
     * @param rrType
     * @return RData
     */
    public static RData createRDataWithType(RawResponse rawResponse, int rrType) {

        switch (rrType) {
            case RRTYPE_A:
                return createARData(rawResponse);
            case RRTYPE_NS:
                return createNSRData(rawResponse);
            case RRTYPE_CNAME:
                return createCNameRData(rawResponse);
            case RRTYPE_SOA:
                return createSOARData(rawResponse);
            case RRTYPE_MX:
                return createMXRData(rawResponse);
            case RRTYPE_AAAA:
                return createAAAARData(rawResponse);
            default:
                throw  new DNSClientException(String.format(UNKNOWN_RR_TYPE, rrType));
        }
    }

    /**
     * Create A type RData from response data.
     * @param rawResponse
     * @return ARData
     */
    public static ARData createARData(RawResponse rawResponse) {
        return new ARData(rawResponse.getU8(), rawResponse.getU8(), rawResponse.getU8(),
                rawResponse.getU8());
    }

    /**
     * Create NS type RData from response data.
     * @param rawResponse
     * @return NSRData
     */
    public static NSRData createNSRData(RawResponse rawResponse) {
        AbstractName name = MetaDataFactory.createCompositeName(rawResponse);
        return new NSRData(name);
    }

    /**
     * Create CName type RData from response data.
     * @param rawResponse
     * @return CNameRData
     */
    public static CNameRData createCNameRData(RawResponse rawResponse) {
        AbstractName name = MetaDataFactory.createCompositeName(rawResponse);
        return new CNameRData(name);
    }

    /**
     * Create AAAA type RData from response data.
     * @param rawResponse
     * @return AAAARData
     */
    public static AAAARData createAAAARData(RawResponse rawResponse) {
        byte[] bytes = new byte[IPV6_BYTE_LEN];
        for (int i=0; i<bytes.length; i++) {
            bytes[i] = rawResponse.getByte();
        }
        return new AAAARData(bytes);
    }

    /**
     * Create MX type RData from response data.
     * @param rawResponse
     * @return MXRData
     */
    public static MXRData createMXRData(RawResponse rawResponse) {
        int preference = rawResponse.getU16();
        AbstractName mailExchanger = MetaDataFactory.createCompositeName(rawResponse);
        return new MXRData(preference, mailExchanger);
    }
}
