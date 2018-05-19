package Vista;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grafic extends JPanel {
    /**
     * Funcio que s'encarrega de crear el gràfic del top de plats
     */
    private int[] graphSource;
    private Integer max = 0;
    private ArrayList<String> nomPlat;


    /**
     * Funcio que crea el grafic
     *
     * @param graphSource array de numeros que indiquen quantes unitats s'han gastat de cada plat
     * @param nomPlat     Array de strings que conté el nom de cada plat
     * @throws Exception error al crear el grafic
     */
    public void grafic(int[] graphSource, ArrayList<String> nomPlat) throws Exception {
        this.graphSource = graphSource;
        this.nomPlat = nomPlat;
        for (int j = 0; j < graphSource.length; j++) {

            if (graphSource[j] > max) {
                max = graphSource[j];
            }
        }
        if (max > 0 && max <= 4) {
            max = 5;
        }

        setupPanel();
        repaint();
    }

    /**
     * Funcio que pinta tots els elements del grafic
     *
     * @param grafic tipus graphic
     * @throws ArithmeticException error de dividir entre 0
     */
    public void paintComponent(Graphics grafic) throws ArithmeticException {
        if (max > 0) {
            super.paintComponent(grafic);
            int ymax = 0;
            int hmax = 0;
            int eix = (this.getWidth()) / (graphSource.length + 2);
            int width = (this.getWidth()) / (graphSource.length + 2);
            Graphics2D maingrafic = (Graphics2D) grafic;
            for (int i = 0; i < llargada(); i++) {
                int height = (graphSource[i] * (((this.getHeight()) / max))) / 2;

                int x = (width) * (i + 1);
                int y = (this.getHeight() - (graphSource[i] * (this.getHeight() / max))) / 2 + this.getHeight() / 4;

                int red = (int) (Math.random() * 256);
                int blue = (int) (Math.random() * 256);
                int green = (int) (Math.random() * 256);
                ymax = (this.getHeight() - (max * (this.getHeight() / max))) / 2 + this.getHeight() / 4;
                hmax = (max * (((this.getHeight()) / max))) / 2;


                maingrafic.setColor(new Color(red, green, blue));
                maingrafic.fill(new Rectangle(x, y, width, height));

                if (nomPlat.get(i).length() > 10) {
                    char a = nomPlat.get(i).charAt((nomPlat.get(i).length() / 2) - 1);
                    if (a == ' ') {
                        maingrafic.drawString(nomPlat.get(i).substring(0, nomPlat.get(i).length() / 2), x + width / 2 - (nomPlat.get(i).length() / 2) * 3, this.getHeight() - 30);

                    } else {
                        maingrafic.drawString(nomPlat.get(i).substring(0, nomPlat.get(i).length() / 2) + '-', x + width / 2 - (nomPlat.get(i).length() / 2) * 3, this.getHeight() - 30);

                    }
                    maingrafic.drawString(nomPlat.get(i).substring(nomPlat.get(i).length() / 2, nomPlat.get(i).length()), x + width / 2 - (nomPlat.get(i).length() / 2) * 3, this.getHeight() - 10);


                } else {
                    maingrafic.drawString(nomPlat.get(i), x + width / 2 - nomPlat.get(i).length() * 3, this.getHeight() - 30);

                }


            }
            for (int i = 0; i < 5; i++) {
                Integer a = max / 5 * (i + 1);
                maingrafic.setColor(Color.BLACK);
                maingrafic.drawString(a.toString(), eix / 2, ymax + (hmax) / 5 * (4 - i));
            }
            maingrafic.setColor(Color.BLACK);
            maingrafic.drawString("0", eix / 2, ymax + hmax);
            maingrafic.setColor(Color.LIGHT_GRAY);
            maingrafic.fill(new Rectangle(eix, 20, 5, ymax + hmax - 20));
            maingrafic.fill(new Rectangle(eix, ymax + hmax, width * 5, 5));
        }

    }

    private void setupPanel() {
        this.setBackground(Color.WHITE);
    }

    /**
     * Funció que calcula la llargada dels plats que ens passsen, en cas que sigui menor a 5
     *
     * @return llargada
     */
    private int llargada() {
        int i = 0;
        try {
            while (i < 5) {
                i++;
                nomPlat.get(i);
            }
            return i;
        } catch (Exception e) {
            return i;
        }
    }
}