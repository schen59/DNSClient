package rdata;

import metadata.AbstractName;

/**
 * Class for NS type RData.
 * @author Shaofeng Chen
 * @since 2/22/14
 */
public class NSRData extends RData {

    private AbstractName name;

    public NSRData(AbstractName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.getName();
    }
}
