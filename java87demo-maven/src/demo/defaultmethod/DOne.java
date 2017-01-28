package demo.defaultmethod;

/**
 * Created by WY on 2015/10/20.
 */
public class DOne implements DMInterface {
    @Override
    public long getId() {
        return 10;
    }


    public static void main(String[] args) {
        DOne one = new DOne();

        System.out.println(one.getName());
    }

}
