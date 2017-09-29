import com.javonet.JavonetException;
import com.javonet.api.NObject;

public class Compute extends NObject {

	protected Compute() throws JavonetException {
		super("Compute");
	}

	public Integer Sum(int a, int b) throws JavonetException
	{
		return this.invoke("Sum",a,b);
	}
}
