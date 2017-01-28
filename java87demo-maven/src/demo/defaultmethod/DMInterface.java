package demo.defaultmethod;

/**
 * Created by WY on 2015/10/20.
 */
public interface DMInterface {

    long getId();

    default String getName(){
        return "hello world!";
    }

}
