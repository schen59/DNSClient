package metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for CompositeName type object which is combination of labels and pointers.
 * @author Shaofeng Chen
 * @since 2/20/14
 */
public class CompositeName extends AbstractName {
    private List<AbstractName> components;

    public CompositeName() {
        components = new ArrayList<AbstractName>();
    }

    public String getName() {
        return getName(components);
    }

    public void addComponent(AbstractName component) {
        components.add(component);
    }
}
