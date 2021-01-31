package imageviewermvp2.swing;

import imageviewermvp2.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay {
    private String current;
    private String future;
    private BufferedImage bitmap;
    private BufferedImage futureBitmap;
    private int offset = 0;
    private Shift shift;
    
    
    public ImagePanel(){
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if (bitmap == null) return;
        g.drawImage(bitmap, offset, 0, null);
        if (offset == 0) return;
        g.drawImage(futureBitmap, offset < 0 ? bitmap.getWidth()+offset : -(futureBitmap.getWidth()-offset), 0, null);
    }
    
    public void setOffset(int offset){
        this.offset = offset;
        if (offset < 0) setFuture(shift.left());
        if (offset > 0) setFuture(shift.right());
        repaint();
    }
    
    private void toogle(){
        this.current = this.future;
        this.bitmap = this.futureBitmap;
        setOffset(0);
    }
    
    private void setFuture(String name) {
        if (name.equals(future)) return;
        this.future = name;
        this.futureBitmap = loadBitmap(name);
    }
    
    @Override
    public void display(String image){
        this.current = image;
        repaint();
    }
    
    @Override
    public String current(){
        return current;
    }

    private BufferedImage loadBitmap(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    public class MouseHandler implements MouseListener, MouseMotionListener{

        private int initial;
        
        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            initial = event.getX();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            if (Math.abs(offset) > getWidth() / 2)
                toogle();
            else
                setOffset(0);
            setOffset(0);
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            setOffset(event.getX()-initial);
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }
        
    }
    
    
}

