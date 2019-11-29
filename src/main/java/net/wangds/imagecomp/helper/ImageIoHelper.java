package net.wangds.imagecomp.helper;

import net.wangds.log.helper.LogHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageIoHelper {

    public static byte[] toByteArray(RenderedImage image, String format){
        byte[] res = new byte[0];
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, format, out);
            out.flush();
            res = out.toByteArray();
        } catch (IOException e) {
            LogHelper.error(e);
        }
        return res;
    }

    public static byte[] toByteArray(Image image, String format){
        RenderedImage img = null;
        byte[] res = new byte[0];
        if(image instanceof RenderedImage){
            img = (RenderedImage)image;
        }else{
            img = toBufferedImage(image);
        }
        toByteArray(img, format);
        return res;
    }

    public static BufferedImage toBufferedImage(Image image){
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int type = BufferedImage.TYPE_INT_ARGB;
        BufferedImage bi = new BufferedImage(w,h,type);
        Graphics g = bi.getGraphics();
        g.drawImage(image,0,0,w,h,null);
        g.dispose();
        return bi;
    }

}
