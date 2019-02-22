package interfaz;
import javax.swing.*;
import java.awt.*;
public class PanelBanner extends JPanel{

	private JLabel imagen;
	public PanelBanner(String ruta){
		setLayout(new BorderLayout());
		imagen = new JLabel();
		ImageIcon icon = new ImageIcon(ruta);
		imagen.setIcon(icon);
		add(imagen, BorderLayout.CENTER);
	}
}
