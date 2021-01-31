package imageviewermvp2.swing;

import imageviewermvp2.control.ImagePresenter;
import imageviewermvp2.model.Image;
import imageviewermvp2.view.ImageDisplay;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
    public static int MAX_SIZE = 100_000;
    public static void main(String[] args){
        new Main().execute();
        
    }
    private ImageDisplay imageDisplay;
    private final ImagePresenter imagePrensenter;
    
    public Main(){
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);        
        this.getContentPane().add(imagePanel());
        this.imagePrensenter = createImagePresenter();
    }
    
    private void execute(){
        this.setVisible(true);
    }
    
    private JPanel imagePanel(){
        ImagePanel panel = new ImagePanel();
        this.imageDisplay = panel;
        return panel;
    }

    private ImagePresenter createImagePresenter() {
        return new ImagePresenter(loadImages(), imageDisplay);
    }

    private List<Image> loadImages() {
        return new FileImageLoader(new File("fotos")).load();
    }

}
