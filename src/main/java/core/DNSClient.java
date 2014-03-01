package core;

import enums.RRType;
import exception.DNSClientException;
import factory.EnumFactory;
import request.Request;
import request.RequestBuilder;
import response.RawResponse;
import response.Response;
import response.ResponseBuilder;
import util.CommonUtil;

import static common.Constants.*;

/**
 * DNSClient class to handle dns query.
 * @author Shaofeng Chen
 * @since 2/22/14.
 */
public class DNSClient {
    private final String name;
    private final Profiler profiler;
    private String server;
    private String queryName;
    private String queryType;

    public DNSClient(String name, Profiler profiler) {
        this.name = name;
        this.profiler = profiler;
    }

    /**
     * Make a dns request to the specified server.
     * @param server
     * @return DNSClient
     */
    public DNSClient makeRequestTo(String server) {
        this.server = server;
        return this;
    }

    /**
     * Specify the query name for the dns request.
     * @param queryName
     * @return DNSClient
     */
    public DNSClient withQuery(String queryName) {
        this.queryName = queryName;
        return this;
    }

    /**
     * Specify the query type for the dns request.
     * @param queryType
     * @return
     */
    public DNSClient withType(String queryType) {
        this.queryType = queryType;
        return this;
    }

    public String execute() {
        RRType rrType = EnumFactory.createRRType(queryType);
        Request request = RequestBuilder.build(server, queryName, rrType);
        byte[] responseData = profiler.profile(request);
        RawResponse rawResponse = new RawResponse(responseData);
        Response response = ResponseBuilder.build(rawResponse);
        return stringtify(response);

    }

    private String stringtify(Response response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(DNS_CLIENT_NAME_FMT, name, queryName)).append(NEW_LINE);
        stringBuilder.append(response.toString()).append(NEW_LINE);
        stringBuilder.append(String.format(QUERY_TIME_FMT,
                profiler.getRuntime())).append(NEW_LINE);
        stringBuilder.append(String.format(SERVER_ADDR_FMT, CommonUtil.getInetAddressFrom
                (server).toString())).append(NEW_LINE);
        stringBuilder.append(String.format(TIMESTAMP_FMT, CommonUtil.getDateString())).append(NEW_LINE);
        stringBuilder.append(String.format(MSG_SIZE_FMT, profiler.getMessageSize()))
                .append(NEW_LINE);
        return stringBuilder.toString();
    }

    public static void main(String[] argv) {
        if (argv.length < 3) {
            throw new DNSClientException(USAGE_INFO);
        }
        String server = argv[0];
        String queryName = argv[1];
        String type = argv[2];
        DNSClient client = new DNSClient("DNSClient", new Profiler());
        String response = client.makeRequestTo(server)
                .withQuery(queryName)
                .withType(type)
                .execute();
        System.out.println(response);
    }
}
