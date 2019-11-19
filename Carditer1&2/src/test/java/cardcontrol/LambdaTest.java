package cardcontrol;

import com.amazonaws.services.lambda.runtime.Context;


public class LambdaTest {
		
	protected Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
	
}