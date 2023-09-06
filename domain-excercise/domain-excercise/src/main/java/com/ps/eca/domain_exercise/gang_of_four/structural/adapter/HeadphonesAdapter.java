package com.ps.eca.domain_exercise.gang_of_four.structural.adapter;

// Adapter classes that adapt the audio devices to the USBPort interface
class HeadphonesAdapter implements USBPort {
    private Headphones headphones;

    public HeadphonesAdapter(Headphones headphones) {
        this.headphones = headphones;
    }

    @Override
    public void connectUSB() {
        System.out.println("Using a USB-C to 3.5mm adapter for headphones.");
        headphones.plugIn35mmJack();
    }
}
