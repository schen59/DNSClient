package metadata;

import enums.RRClass;
import enums.RRType;
import rdata.RData;

import static common.Constants.*;

/**
 * Class to deal with resource record related manipulation.
 * @author Shaofeng Chen
 * @since 2/20/14
 */
public class ResourceRecord {
    private final AbstractName name;
    private final RRType rrType;
    private final RRClass rrClass;
    private final long ttl;
    private final int rLength;
    private final RData rData;

    public ResourceRecord(AbstractName name, RRType rrType, RRClass rrClass, long ttl,
            int rLength, RData rData) {
        this.name = name;
        this.rrType = rrType;
        this.rrClass = rrClass;
        this.ttl = ttl;
        this.rLength = rLength;
        this.rData = rData;
    }

    @Override
    public String toString() {
        return String.format(RESOURCE_RECORD_FMT, name.getName(), ttl, rrClass.getName(),
                rrType.getName(), rData.toString());
    }
}
