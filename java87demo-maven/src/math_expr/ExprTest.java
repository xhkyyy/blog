package math_expr;

import com.udojava.evalex.Expression;
import org.junit.Test;

import java.math.BigDecimal;


/**
 * Created by Len on 28/01/2017.
 */
public class ExprTest {

	@Test
	public void test(){

		Expression expression = new Expression("1+4/2");
		BigDecimal result = expression.eval();

		System.out.println(result);

	}

}
