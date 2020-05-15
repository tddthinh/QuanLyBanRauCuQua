import View.DangNhapView;
public class main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            DangNhapView view  = new DangNhapView();
            view.setVisible(true);
        });
    }
}
