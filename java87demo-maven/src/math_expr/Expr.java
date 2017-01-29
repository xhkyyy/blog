package math_expr;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Len on 28/01/2017.
 */
public class Expr {

	private String exprStr = null;

	public Expr(String exprStr) {
		this.exprStr = exprStr;
	}

	public Object exec(){
		if(StringUtils.isBlank(exprStr)){
			return null;
		}


		return null;
	}
}
