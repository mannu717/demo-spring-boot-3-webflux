package com.ps.eca.domain_exercise.gang_of_four.structural.adapter;

public class Application {
    public static void main(String[] args) {
        // Incompatible audio devices
        Headphones headphones = new Headphones();
        SpeakerSystem speakerSystem = new SpeakerSystem();

        // Use adapters to connect devices to a laptop's USB port
        USBPort headphonesAdapter = new HeadphonesAdapter(headphones);
        USBPort speakerSystemAdapter = new SpeakerSystemAdapter(speakerSystem);

        // Connecting devices to the laptop's USB-C port
        System.out.println("Connecting audio devices to a laptop's USB-C port:");
        headphonesAdapter.connectUSB();
        speakerSystemAdapter.connectUSB();
    }
}
