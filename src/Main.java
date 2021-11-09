import javax.swing.*;

public class Main {

    public static MainGUI gui = new MainGUI();

    public static void main(String[] args) {
        try {// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if (info.getName().equals("Nimbus")) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
            gui.createGUI();
        } catch (Exception e) {
            //e.printStackTrace();
        }


    }

}
