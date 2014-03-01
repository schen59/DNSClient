package rdata;

import metadata.AbstractName;

import static common.Constants.RDATA_SOA_FMT;

/**
 * Class for SOA type RData.
 * @author Shaofeng Chen
 * @since 2/21/14
 */
public class SOARData extends RData {
    private AbstractName primaryNS;
    private AbstractName adminMB;
    private long serialNumber;
    private long refreshInterval;
    private long refreshLimit;
    private long expirationLimit;
    private long minimumTTL;

    public SOARData(AbstractName primaryNS, AbstractName adminMB, long serialNumber,
            long refreshInterval, long refreshLimit, long expirationLimit, long minimumTTL) {
        this.primaryNS = primaryNS;
        this.adminMB = adminMB;
        this.serialNumber = serialNumber;
        this.refreshInterval = refreshInterval;
        this.refreshLimit = refreshLimit;
        this.expirationLimit = expirationLimit;
        this.minimumTTL = minimumTTL;
    }

    @Override
    public String toString() {
        return String.format(RDATA_SOA_FMT, primaryNS.getName(), adminMB.getName(),
                serialNumber, refreshInterval, refreshLimit, expirationLimit, minimumTTL);
    }
}
