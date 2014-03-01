package rdata;

import metadata.AbstractName;

import static common.Constants.RDATA_MX_FMT;

/**
 * Class for MX type RData.
 * @author Shaofeng Chen
 * @since 2/22/14
 */
public class MXRData extends RData {
    private int preference;
    private AbstractName mailExchanger;

    public MXRData(int preference, AbstractName mailExchanger) {
        this.preference = preference;
        this.mailExchanger = mailExchanger;
    }

    @Override
    public String toString() {
        return String.format(RDATA_MX_FMT, preference, mailExchanger.getName());
    }
}
