import java.util.*;
import java.io.*;
import javax.swing.*;

public class OtoSuffixer {
   public static void main(String [] args) {
      ArrayList<String> otoFile = new ArrayList<String>();
      boolean windows, error = false;
      String macHeader = "";
      ImageIcon utau = new ImageIcon("utaulogo.png");
      
      while (true) {
         /*System.out.print("Windows or Mac? ");
         String os = input.next();*/
         Object[] possibleValues = { "Windows", "Mac"};
         Object os = JOptionPane.showInputDialog(null,"Which operating system?", "OTO Suffixer",JOptionPane.INFORMATION_MESSAGE, utau,possibleValues, possibleValues[0]);
         //os = os.toLowerCase();
         if (os.equals("Windows")) {
            windows = true;
            break;
         } else if (os.equals("Mac")) {
            windows = false;
            break;
         }   
      }
      
      String inFile, outFile;
      if (windows) {
         inFile = "oto.ini";
         outFile = "suffixed-oto.ini";
      } else {
         inFile = "oto_ini.txt";
         outFile = "suffixed-oto_ini.txt";
      }
      
      try {         
         Scanner reader = new Scanner(new File(inFile));
         while (reader.hasNext()) {
            otoFile.add(reader.next());
         }
         reader.close();
         
         if (!windows) {
            macHeader = otoFile.remove(0);
         }
      } catch (Exception ex) {
         error = true;
         ex.printStackTrace();
         JOptionPane.showMessageDialog(null, "Could not read oto.", "OTO Suffixer Error", JOptionPane.ERROR_MESSAGE);
      }
      
      ArrayList<String[]> otoMatrix = new ArrayList<String[]>();
      
	if (!error) {
    	for (int i = 0; i < otoFile.size(); i++) {
			 otoFile.set(i, otoFile.get(i) + ",");
			 String line = otoFile.get(i);
			 String[] arr = new String[7];
			 int start = 0;
			 int end = line.indexOf("=");
			 for (int j = 0; j < 7; j++) {
				 arr[j] = line.substring(start,end);
				 start = end + 1;
				 end = line.indexOf(",",start);
			 }
			 arr[0] = arr[0].substring(0,arr[0].length()-4);
			 otoMatrix.add(arr);
	     }
         
         /*System.out.print("Suffix: ");
         String suffix = input.next();*/
         String suffix = JOptionPane.showInputDialog(null,"Enter suffix","OTO Suffixer",JOptionPane.PLAIN_MESSAGE);
               
         try {
            PrintWriter writer = new PrintWriter(new File(outFile));
            
            if (!windows)
               writer.println(macHeader);
            
            for (String[] line : otoMatrix) {
               writer.println(line[0] + ".wav=" + line[1] + suffix + "," + line[2] + "," + line[3] + "," + line[4] + "," + line[5] + "," + line[6]);
            }
            writer.close();
         } catch (Exception ex) {
        	 ex.printStackTrace();
        	 JOptionPane.showMessageDialog(null, "Could not write oto.", "OTO Suffixer Error", JOptionPane.ERROR_MESSAGE);
         }
         //System.out.print("Done.");
         JOptionPane.showMessageDialog(null, "Done.", "OTO Suffixer", JOptionPane.INFORMATION_MESSAGE,utau);
      }
   }     
}