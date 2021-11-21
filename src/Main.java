public class Main {

    public static MainGUI gui = new MainGUI();

    public static void main(String[] args)
    {
        try
        {
            gui.createGUI();
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
