package com.umut.videostream.view;

import com.umut.videostream.model.Movie;
import com.umut.videostream.model.enums.EMovieQuality;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WatchScene extends JFrame implements IFreezable{
    JComboBox<EMovieQuality> qualityComboBox;
    Container container;
    JLabel qualityLabel;
    JLabel titleLabel;

    public WatchScene(){
        qualityLabel = new JLabel("Select Quality");
        qualityLabel.setFont(new Font("Serif", Font.PLAIN, 60));

        qualityLabel.setHorizontalAlignment(JLabel.CENTER);
        qualityLabel.setVerticalAlignment(JLabel.CENTER);
        qualityLabel.setSize(1000, 500);
        centerComponent(qualityLabel);

        qualityComboBox = new JComboBox<EMovieQuality>();
        qualityComboBox.setSize(40,20);
        centerComponent(qualityComboBox, 1920, 100);

        container = getContentPane();
        container.setLayout(null);

        container.add(qualityLabel);
        container.add(qualityComboBox);


        setSize(1920,1080);
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

    public JLabel getQualityLabel() {
        return qualityLabel;
    }

    public void setQualityLabel(JLabel qualityLabel) {
        this.qualityLabel = qualityLabel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }
}
