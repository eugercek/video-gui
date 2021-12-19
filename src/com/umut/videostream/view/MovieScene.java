package com.umut.videostream.view;

import com.umut.videostream.model.enums.EDirection;
import com.umut.videostream.model.enums.EMovieGenre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.EnumMap;

public class MovieScene extends JFrame implements IFreezable {
    private Container container;
    private JPanel top;
    private JPanel left;
    private JPanel center;
    private JPanel right;
    private JPanel bottom;

    private JComboBox<EMovieGenre> selectGenreComboBox;

    // Calculated  wanted look with reponse size my secreen
        /*
        +---------------------------------------------------------------------+
        |         75                                                          |
        |         +--------------------------------------------------+        |
        |   200   |                                                  |  200   |
        |         |                                                  |        |
        |         |                                                  |        |
        |         |                                                  |        |
        |         |                   CONTENT                        |        |
        |         |                                                  |        |
        |         |                                                  |        |
        |         |                                                  |        |
        |         |                                                  |        |
        |         |                                                  |        |
        |         |                                                  |        |
        |         |                                                  |        |
        |         +--------------------------------------------------+        |
        |          100                                                        |
        +---------------------------------------------------------------------+   1080
                    1920 px
         Which become base of below calculation
*/
    private static EnumMap<EDirection, Double> ratioMap = new EnumMap<EDirection, Double>(EDirection.class);

    static {
        final double left = 200 / 1920F;
        final double right = 200 / 1920F;
        final double top = 75 / 1080F;
        final double bottom = 100 / 1080F;

        ratioMap.put(EDirection.TOP, top);
        ratioMap.put(EDirection.LEFT, left);
        ratioMap.put(EDirection.RIGHT, right);
        ratioMap.put(EDirection.BOTTOM, bottom);
    }

    public MovieScene() {
        // TODO
        setSize(1000, 800);
        container = getContentPane();

        container.setLayout(new BorderLayout());

        top = new JPanel(new FlowLayout());
        left = new JPanel();
        center = new JPanel(new GridLayout(3, 3, 20, 20));
        right = new JPanel();
        bottom = new JPanel();

        selectGenreComboBox = new JComboBox<EMovieGenre>();

        top.setPreferredSize(new Dimension(100, getAppropriateHeightPixel(EDirection.TOP)));
        left.setPreferredSize(new Dimension(getAppropriateWidthPixel(EDirection.LEFT), 100));
        right.setPreferredSize(new Dimension(getAppropriateWidthPixel(EDirection.RIGHT), 100));
        bottom.setPreferredSize(new Dimension(100, getAppropriateHeightPixel(EDirection.BOTTOM)));

        container.add(top, BorderLayout.NORTH);
        container.add(left, BorderLayout.WEST);
        container.add(center, BorderLayout.CENTER);
        container.add(right, BorderLayout.EAST);
        container.add(bottom, BorderLayout.SOUTH);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
        });
    }

    @Override
    public void freezeScene() {

    }

    @Override
    public void unfreezeScene() {

    }

    public void renderMovie(String name, Image image) {
        JLabel label = new JLabel(new ImageIcon(image));
        center.add(label);
    }

    // TODO
    private int getWindowWidth() {
        return 1000; // getBounds().width;
    }

    // TODO
    private int getWindowHeight() {
        return 800; // getBounds().height;
    }

    private int getAppropriateWidthPixel(EDirection direction) {
        if (direction == EDirection.TOP || direction == EDirection.BOTTOM) {
            // TODO Actually this Error should be a warning etc
            throw new Error("Wrong ratio usage");
        }
        return (int) (ratioMap.get(direction).doubleValue() * getWindowWidth());
    }

    private int getAppropriateHeightPixel(EDirection direction) {
        if (direction == EDirection.LEFT || direction == EDirection.RIGHT) {
            // TODO Actually this Error should be a warning etc
            throw new Error("Wrong ratio usage");
        }
        return (int) (ratioMap.get(direction).doubleValue() * getWindowHeight());
    }

    /*
    Combobox trigger itself while adding items so temporarily closing its event handler is a solution
     */
    public void renderComboBox(EMovieGenre[] genres, EMovieGenre initialGenre, ActionListener listener) {
        selectGenreComboBox.removeActionListener(listener);
        selectGenreComboBox.removeAllItems();
        ;

        for (EMovieGenre genre : genres) {
            selectGenreComboBox.addItem(genre);
        }

        selectGenreComboBox.setSelectedItem(initialGenre);

        top.add(selectGenreComboBox);

        selectGenreComboBox.addActionListener(listener);
    }

    public JComboBox<EMovieGenre> getSelectGenreComboBox() {
        return selectGenreComboBox;
    }

    public void deleteMovies() {
        center.removeAll();
    }

}
