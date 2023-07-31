package familyTree;

import java.io.Serializable;

public interface Writable {
    void serializeObject(Serializable object, String fileName);
    public Object deserializeObject(String fileName);
}
