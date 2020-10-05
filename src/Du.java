
import java.awt.Frame;
import java.awt.TextArea;
import java.util.Date;
public class Du {
    public static void main(String[] args) {
        Frame f = new Frame();
        TextArea txt = new TextArea();
        txt.setText(new Date().toString());
        f.add(txt);
        f.pack();
        f.setVisible(true);
    }}