import java.awt.EventQueue;

import com.javonet.JavonetException;

public class Program {

	public static void main(String[] args) {
		new Program();
	}
	
    public Program()
    {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Form();
				} catch (JavonetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }
}
