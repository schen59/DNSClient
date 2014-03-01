package rdata;

import static common.Constants.RDATA_A_FMT;

/**
 * Class for A type RData.
 * @author Shaofeng Chen
 * @since 2/21/14
 */
public class ARData extends RData {
    private final int segment1;
    private final int segment2;
    private final int segment3;
    private final int segment4;

    public ARData(int segment1, int segment2, int segment3, int segment4) {
        this.segment1 = segment1;
        this.segment2 = segment2;
        this.segment3 = segment3;
        this.segment4 = segment4;
    }

    @Override
    public String toString() {
        return String.format(RDATA_A_FMT, segment1, segment2, segment3, segment4);
    }
}
