package metadata;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for Name related type data.
 * @author Shaofeng Chen
 * @since 2/20/14
 */
public abstract class AbstractName {

    /**
     * Get the string representation of the concrete name.
     * @return String
     */
    public abstract String getName();

    /**
     * Get the string representation of list of name objects in case of list of labels of list of
     * labels and pointers.
     * @param abstractNames
     * @return String
     */
    protected String getName(List<? extends AbstractName> abstractNames) {
        List<String> names = new ArrayList<String>();
        for (AbstractName abstractName : abstractNames) {
            names.add(abstractName.getName());
        }
        return StringUtils.join(names, ".");
    }
}
