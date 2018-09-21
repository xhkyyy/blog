package jmh;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Group)
public class StateContended {
    @sun.misc.Contended
    int readOnly;

    @sun.misc.Contended
    int writeOnly;
}
