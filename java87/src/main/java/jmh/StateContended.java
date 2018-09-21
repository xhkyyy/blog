package jmh;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * @sun.misc.Contended 加在类级别上，将使一个类的所有字段都在一个缓存行上。
 * @sun.misc.Contended 加在单个字段上，将使这个字段与其它字段不在一个缓存行上
 * @sun.misc.Contended 加在所有字段上，将使每个字段都在各自的缓存行上。
 */

@State(Scope.Group)
public class StateContended {
    //@sun.misc.Contended
    int readOnly;

    @sun.misc.Contended
    int writeOnly;
}
