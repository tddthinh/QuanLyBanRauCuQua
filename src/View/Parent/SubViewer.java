package View.Parent;

import View.GiaoDienChinhView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SubViewer extends Viewer {

    protected final GiaoDienChinhView parentView;
    protected final SubViewer thisView;

    public SubViewer(GiaoDienChinhView parent) {
        super();
        parentView = parent;
        thisView = this;
        initListenner();
    }

    protected void close() {
        thisView.setVisible(false);
        parentView.setVisible(true);
        parentView.setEnabled(true);
    }

    private void initListenner() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }

}
