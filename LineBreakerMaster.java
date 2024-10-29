package linebreaker;
	import javax.swing.*;
	import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

	public class LineBreakerMaster extends JFrame {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public LineBreakerMaster() {
	        setTitle("LineBreaker");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();

	        // 첫 번째 텍스트 필드 (입력 필드)
	        JTextField howManyCutText = new JTextField("10");
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.insets = new Insets(10, 0, 10, 0);
	        add(howManyCutText, gbc);

	        // 두 번째 텍스트 필드 (출력 필드)
	        JTextField inputText = new JTextField("                                                                ");
	        gbc.gridy = 1;
	        add(inputText, gbc);

	        // 버튼
	        JButton button = new JButton("LineBreak!");
	        gbc.gridy = 2;
	        add(button, gbc);
	        
	        button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int howCut = Integer.parseInt(howManyCutText.getText());
			            JFileChooser fileChooser = new JFileChooser();
		                fileChooser.setDialogTitle("Save as");
		                
		                int userSelection = fileChooser.showSaveDialog(LineBreakerMaster.this);
		                
		                if (userSelection == JFileChooser.APPROVE_OPTION) {
		                	System.out.println(userSelection);
		                    File fileToSave = fileChooser.getSelectedFile();
		                    
		                    // 파일에 텍스트 쓰기
		                    
		                   saveTextToFile(lineBreak(inputText.getText(), howCut), fileToSave);
		                   JOptionPane.showMessageDialog(LineBreakerMaster.this, "저장이 완료되었습니다.", "", JOptionPane.YES_OPTION);
		                   inputText.setText("");
		                } else {
		                }
		                
			        } catch (NumberFormatException error) {
			        	JOptionPane.showMessageDialog(LineBreakerMaster.this, "숫자만 입력해 주십시오", "첫번째 칸엔", JOptionPane.ERROR_MESSAGE);
			        } catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
	        	
	        });

	        setSize(300, 200);
	        setLocationRelativeTo(null); // 화면 중앙에 표시
	        setVisible(true);
	        
	       
	    }
		
		private void saveTextToFile(String txt, File file) throws IOException {
	        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	        writer.write(txt);
	        writer.flush();
	        writer.close();
	    }
		
		 private String lineBreak(String input, int length) {
		        StringTokenizer st = new StringTokenizer(input);
		        String output = "";
		        Loop1 : while (st.hasMoreTokens()) {
		            for (int i=0; i<length; i++) {
		                if(!st.hasMoreTokens()) {break Loop1;}
		                output += st.nextToken() + " ";
		            }
		            output += "\n";
		        }
		        return output;
		    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(LineBreakerMaster::new);
	    }
	}


