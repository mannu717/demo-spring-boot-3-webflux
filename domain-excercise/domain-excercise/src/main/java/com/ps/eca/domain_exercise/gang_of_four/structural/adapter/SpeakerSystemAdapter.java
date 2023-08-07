package com.ps.eca.domain_exercise.gang_of_four.structural.adapter;

class SpeakerSystemAdapter implements USBPort {
    private SpeakerSystem speakers;

    public SpeakerSystemAdapter(SpeakerSystem speakers) {
        this.speakers = speakers;
    }

    @Override
    public void connectUSB() {
        System.out.println("Using a USB-C to RCA adapter for speaker system.");
        speakers.plugInRCAConnectors();
    }
}
