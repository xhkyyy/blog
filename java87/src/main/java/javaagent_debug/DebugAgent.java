package javaagent_debug;

import java.lang.instrument.Instrumentation;

public class DebugAgent {

    public static void premain(String args, Instrumentation instrumentation){
        ZkClassFileTransformer cl = new ZkClassFileTransformer();
        instrumentation.addTransformer(cl);
    }

}
