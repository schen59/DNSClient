package metadata;

/**
 * Class to deal with pointer related manipulation.
 * @author Shaofeng Chen
 * @since 2/20/14
 */
public class Pointer extends AbstractName {
    private AbstractName name;

    public Pointer(AbstractName qname) {
        this.name = qname;
    }

    public String getName() {
        return name.getName();
    }
}
