package imageviewermvp2.control;

import imageviewermvp2.model.Image;
import imageviewermvp2.view.ImageDisplay;
import java.util.List;

public class ImagePresenter {
    
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.imageDisplay.display(images.get(0).getName());
        this.imageDisplay.on(shift());
    }

    private ImageDisplay.Shift shift() {
        return new ImageDisplay.Shift(){
            @Override
            public String left() {
                return images.get(bounds(index()+1)).getName();
            }

            @Override
            public String right() {
                return images.get(bounds(index()-1)).getName();
            }
            private int bounds(int index){
                return (index + images.size()) % images.size();
            }
        };
    }
    private int index(){
        for (int i = 0; i < images.size(); i++) {
            if(imageDisplay.current().equals(images.get(i).getName())) return i;
        }
        return -1;
    }
    
}
