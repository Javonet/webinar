import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;

import com.javonet.Javonet;
import com.javonet.JavonetApartmentState;
import com.javonet.JavonetException;
import com.javonet.JavonetFramework;
import com.javonet.api.INEventListener;
import com.javonet.api.NControlContainer;
import com.javonet.api.NObject;

public class Form {
	Frame guiFrame = new Frame();
	NControlContainer wac;
	NObject uiComponent;
	
	public Form() throws JavonetException {
		initializeComponents();
	}
	
    private void initializeComponents() throws JavonetException
    {
    	guiFrame = new Frame();
    
        //make sure the program exits when the frame closes
        guiFrame.addWindowListener(new WindowAdapter(){
        	  public void windowClosing(WindowEvent we){
        		     guiFrame.dispose();
        		  }
      
        		});
        guiFrame.setTitle("Native Integration Sample - Webinar");
        guiFrame.setSize(980,500);
      
        guiFrame.setLayout(new BorderLayout());
        
        Panel navigationBar = new Panel();
        
        Panel mainPanel = new Panel();

		BoxLayout layout = new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
		mainPanel.setLayout(layout);
   
		Label lblHeader = new Label();
		lblHeader.setText("Java control...");
		lblHeader.setFont(new Font("Microsoft Sans Serif",0,20));
		navigationBar.add(lblHeader);
		
		try {
			Javonet.setApartmentState(JavonetApartmentState.STA);
			// TODO: Register for Javonet trial https://www.javonet.com/signup-for-trial/
			// TODO: Type the email address and license key (will be sent to your email)
			Javonet.activate("your-mail", "your-key",JavonetFramework.v45);
			Javonet.addReference("System.Windows.Forms");
			Javonet.addReference("UIFrontendComponent.dll");
			
			//Create instance of user control
			uiComponent = Javonet.New("UserControl1");
			
			//Wrap .NET user control with NControlContainer
			wac = new NControlContainer(uiComponent);
			
			//Add user control to Java UI
			mainPanel.add(wac, BorderLayout.CENTER);
		} catch (JavonetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		uiComponent.getRef("Controls").<NObject>getIndex("Button1").addEventListener("Click", new INEventListener() {

			@Override
			public void eventOccurred(Object[] arg0) {
				// TODO Auto-generated method stub
				try {
					Javonet.getType("System.Windows.Forms.MessageBox").invoke("Show",".NET Button was clicked!");
				} catch (JavonetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
  
		guiFrame.add(mainPanel);
    	guiFrame.add(navigationBar,BorderLayout.NORTH);
    	
        guiFrame.setVisible(true);
    }
}
