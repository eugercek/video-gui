package com.umut.videostream.view;

import com.umut.videostream.model.enums.EMovieQuality;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WatchScene extends JFrame implements IFreezable{
    public static final String FIRST_MESSAGE = "Watching movie only 480p :(";
    JComboBox<EMovieQuality> qualityComboBox;
    Container container;
    JLabel label;
    JFrame caller;

    public WatchScene(JFrame caller){
        this.caller = caller;
        label = new JLabel(FIRST_MESSAGE);
        label.setFont(new Font("Serif", Font.PLAIN, 60));

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setSize(1000, 500);
        centerComponent(label);

        qualityComboBox = new JComboBox<EMovieQuality>();
        qualityComboBox.setSize(80,20);
        centerComponent(qualityComboBox, 1920, 100);

        container = getContentPane();
        container.setLayout(null);

        container.add(label);
        container.add(qualityComboBox);


        setSize(1920,1080);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                caller.setVisible(true);
            }
        });
    }

    @Override
    public void freezeScene() {

    }

    @Override
    public void unfreezeScene() {

    }

    private void centerComponent(JComponent component){
        component.setBounds(1920 /2  - component.getWidth() / 2  , 1080/2 - component.getHeight() / 2,component.getWidth(), component.getHeight());
    }

    private void centerComponent(JComponent component, int width, int height){
        component.setBounds(width / 2 - component.getWidth() / 2  , height/2 - component.getHeight() / 2,component.getWidth(), component.getHeight());
    }

    public void setQualityList(EMovieQuality[] qualities, ActionListener listener){
        qualityComboBox.removeActionListener(listener);

        qualityComboBox.removeAllItems();
        for (EMovieQuality quality : qualities) {
            qualityComboBox.addItem(quality);
        }

        qualityComboBox.addActionListener(listener);
    }

    public JComboBox<EMovieQuality> getQualityComboBox() {
        return qualityComboBox;
    }

    public void setQualityComboBox(JComboBox<EMovieQuality> qualityComboBox) {
        this.qualityComboBox = qualityComboBox;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
