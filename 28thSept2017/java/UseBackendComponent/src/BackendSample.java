import com.javonet.Javonet;
import com.javonet.JavonetException;
import com.javonet.JavonetFramework;
import com.javonet.api.NObject;

public class BackendSample {

	public static void main(String[] args) throws JavonetException {
		// TODO: Register for Javonet trial https://www.javonet.com/signup-for-trial/
		// TODO: Type the email address and license key (will be sent to your email)
		Javonet.activate("your-mail", "your-key",JavonetFramework.v45);
		Javonet.addReference("BackendComponent.dll");
		
		//Using Javonet API
		NObject computeObj = Javonet.New("Compute");
		Integer result = computeObj.invoke("Sum",4,6);
		System.out.println("Hello: "+result);
		
		//Using strongly-typed wrapper
		Compute computeObjWrapped = new Compute();
		Integer r = computeObjWrapped.Sum(4, 5);
		
		System.out.println("Hello: "+r);
		
	}

}
