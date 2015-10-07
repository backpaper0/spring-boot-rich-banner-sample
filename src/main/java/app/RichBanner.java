package app;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class RichBanner implements Banner {

    private final int[] colors;
    private final String name;

    public RichBanner(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass,
            PrintStream out) {
        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream(name)) {
            BufferedImage img = ImageIO.read(in);
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int rgb = img.getRGB(x, y);
                    int index = 0;
                    int distance = getDistance(rgb, colors[index]);
                    for (int i = 1; i < colors.length; i++) {
                        int temp = getDistance(rgb, colors[i]);
                        if (temp < distance) {
                            distance = temp;
                            index = i;
                        }
                    }
                    out.write(0x1b);
                    out.write(("[48;05;" + index + "m  ").getBytes());
                    out.write(0x1b);
                    out.write(("[0m").getBytes());
                }
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int getDistance(int rgb1, int rgb2) {
        int r = ((rgb1 >> 16) & 0xff) - ((rgb2 >> 16) & 0xff);
        int g = ((rgb1 >> 8) & 0xff) - ((rgb2 >> 8) & 0xff);
        int b = (rgb1 & 0xff) - (rgb2 & 0xff);
        return (int) Math.sqrt(r * r + g * g + b * b);
    }

    {
        colors = new int[] { 0x000000, 0x97040c, 0x17a41a, 0x99981d, 0x0516af,
                0xb119b0, 0x1aa6b1, 0xbfbfbf, 0x666666, 0xe30a17, 0x21d726,
                0xe5e431, 0x0b24fb, 0xe323e3, 0x27e5e4, 0xe6e5e6, 0x000000,
                0x01075d, 0x030e85, 0x0515ac, 0x071cd3, 0x0b24fb, 0x085e0b,
                0x0a5f5f, 0x0b6085, 0x0d61ad, 0x0f63d4, 0x1265fb, 0x118614,
                0x128660, 0x128786, 0x1488ad, 0x1589d4, 0x178afb, 0x19ad1d,
                0x1aae62, 0x1aae88, 0x1bafae, 0x1cafd5, 0x1eb0fc, 0x21d526,
                0x22d565, 0x22d689, 0x23d6af, 0x24d7d6, 0x25d7fd, 0x29fd2f,
                0x2afd68, 0x2afd8c, 0x2bfdb1, 0x2cfed7, 0x2dfffe, 0x5e0204,
                0x5e085e, 0x5e0f85, 0x5e16ac, 0x5f1dd3, 0x5f24fb, 0x5f5e0e,
                0x5f5f5f, 0x5f6086, 0x5f61ad, 0x6063d4, 0x6065fb, 0x608616,
                0x608660, 0x608787, 0x6188ad, 0x6189d4, 0x618afc, 0x62ad1e,
                0x62ae62, 0x62ae88, 0x62afae, 0x62b0d5, 0x63b1fc, 0x64d527,
                0x64d565, 0x64d68a, 0x64d6b0, 0x65d7d6, 0x65d7fd, 0x66fd30,
                0x67fd68, 0x67fd8c, 0x67fdb1, 0x67fed7, 0x68fffe, 0x850309,
                0x850a5e, 0x861085, 0x8617ac, 0x861ed4, 0x8625fb, 0x865e12,
                0x865f60, 0x866086, 0x8661ad, 0x8763d4, 0x8765fb, 0x878619,
                0x878661, 0x878787, 0x8788ae, 0x8789d5, 0x888afc, 0x88ad20,
                0x88ae63, 0x88ae88, 0x88afaf, 0x88b0d5, 0x89b1fc, 0x89d528,
                0x89d566, 0x8ad68a, 0x8ad6b0, 0x8ad7d6, 0x8ad8fd, 0x8bfd31,
                0x8bfd69, 0x8bfd8c, 0x8bfeb2, 0x8cfed8, 0x8cfffe, 0xad060f,
                0xad0c5f, 0xad1286, 0xad18ad, 0xad1fd4, 0xae26fb, 0xad5f16,
                0xae5f60, 0xae6087, 0xae62ad, 0xae63d4, 0xae65fc, 0xae861c,
                0xae8762, 0xae8788, 0xae88ae, 0xae89d5, 0xaf8bfc, 0xafae23,
                0xafae64, 0xafae89, 0xafafaf, 0xafb0d6, 0xafb1fd, 0xb0d52b,
                0xb0d566, 0xb0d68b, 0xb0d6b0, 0xb0d7d7, 0xb0d8fd, 0xb1fd33,
                0xb1fd69, 0xb1fd8d, 0xb1feb2, 0xb2fed8, 0xb2fffe, 0xd50915,
                0xd50f60, 0xd51487, 0xd51aad, 0xd520d4, 0xd527fb, 0xd55f1b,
                0xd56061, 0xd56187, 0xd562ae, 0xd564d5, 0xd566fc, 0xd58620,
                0xd58763, 0xd58788, 0xd688af, 0xd689d5, 0xd68bfc, 0xd6ae26,
                0xd6ae65, 0xd6af8a, 0xd6afb0, 0xd6b0d6, 0xd6b1fd, 0xd7d52d,
                0xd7d667, 0xd7d68b, 0xd7d6b1, 0xd7d7d7, 0xd7d8fe, 0xd8fd35,
                0xd8fd6a, 0xd8fd8d, 0xd8feb2, 0xd8fed8, 0xd8ffff, 0xfc0d1b,
                0xfc1262, 0xfc1687, 0xfc1cae, 0xfc22d5, 0xfc28fc, 0xfc6020,
                0xfc6063, 0xfc6188, 0xfd63af, 0xfd64d5, 0xfd66fc, 0xfd8724,
                0xfd8764, 0xfd8889, 0xfd89af, 0xfd8ad6, 0xfd8bfd, 0xfdae2a,
                0xfdae66, 0xfdaf8a, 0xfdafb0, 0xfdb0d7, 0xfeb1fd, 0xfed530,
                0xfed669, 0xfed68c, 0xfed7b1, 0xfed7d8, 0xfed8fe, 0xfffd38,
                0xfffd6c, 0xfffd8e, 0xfffeb3, 0xfffed9, 0xffffff, 0x080808,
                0x121212, 0x1c1c1c, 0x262626, 0x303030, 0x3a3a3a, 0x444444,
                0x4e4e4e, 0x585858, 0x626262, 0x6c6c6c, 0x767676, 0x808080,
                0x8a8a8a, 0x949494, 0x9e9e9e, 0xa8a8a8, 0xb2b2b2, 0xbcbcbc,
                0xc6c6c6, 0xd0d0d0, 0xdadada, 0xe4e4e4, 0xeeeeee };
    }
}
