package rdata;

import metadata.AbstractName;

/**
 * Class for CName type RData.
 * @author Shaofeng Chen
 * @since 2/26/14
 */
public class CNameRData extends RData {
    private AbstractName name;

    public CNameRData(AbstractName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.getName();
    }
}
