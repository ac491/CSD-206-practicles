
package storage;

import java.io.IOException;


public abstract class Storage {
    abstract void persistent_save()throws IOException;
    abstract void capacity();
}
