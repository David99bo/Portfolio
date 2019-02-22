package interfaz;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
public class PanelInformacion extends JPanel{

	private JTextArea info;
	
	public PanelInformacion(){
		setLayout(new BorderLayout());
		TitledBorder border = new TitledBorder("Informacion");
		setBorder(border);
		info = new JTextArea(10,40);
		info.setEditable(false);
		info.setPreferredSize(new Dimension(100, 40));
		add(info,BorderLayout.CENTER);
		
	}
	
	public void setInformacion(String informacion){
		info.setText(informacion);
	}
	
}
