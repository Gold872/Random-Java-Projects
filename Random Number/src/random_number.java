import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class random_number implements ActionListener {
		private int min;
		private int max;
		private JFrame frame;
		private JPanel panel;
		private JLabel label;
		private JLabel minimum;
		private JLabel maximum;
		private JTextField minimumfield;
		private JTextField maximumfield;
		
		public random_number() {
			
			minimumfield = new JTextField();
			minimumfield.setBounds(35, 25, 25, 25);
			
			maximumfield = new JTextField();
			maximumfield.setBounds(135,25,25,25);
			
			minimum = new JLabel("Min:");
			minimum.setBounds(10,10,50,50);
			
			maximum = new JLabel("Max:");
			maximum.setBounds(105,10,50,50);
			
			JButton button = new JButton("Generate Number");
			button.setBounds(17,65,150,50);
			button.addActionListener(this);
			
			label = new JLabel("Random Number: 0");
			label.setBounds(35,105,155,50);
			
			
			panel = new JPanel();
			panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			panel.setLayout(null);
			panel.add(label);
			panel.add(button);
			panel.add(minimumfield);
			panel.add(minimum);
			panel.add(maximumfield);
			panel.add(maximum);
			
			frame = new JFrame();
			frame.add(panel, BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Random Number Generator");
			frame.pack();
			frame.setVisible(true);
			frame.setSize(200,200);
		}
	public static void main(String[]args) {
		
		new random_number();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	String minimumnum = minimumfield.getText();
	String maximumnum = maximumfield.getText();

if(minimumnum.isEmpty()||(maximumnum.isEmpty())) {
	label.setText("Random Number: Error");
		}		

max = Integer.parseInt(maximumfield.getText());
min = Integer.parseInt(minimumfield.getText());		
int x = (int)(Math.random() * (max - min + 1) + min);
label.setText("Random Number: " + x);

if(max < min) {
	label.setText("Random Number: Error");

		}

	}
		
}
