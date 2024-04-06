package org.example.tts;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSPOC {

    public static void listAllVoices() {
        System.out.println();
        System.out.println("All voices available:");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        for (int i = 0; i < voices.length; i++) {
            System.out.println("    " + voices[i].getName()
                    + " (" + voices[i].getDomain() + " domain)");
        }
    }

    public static void main(String[] args) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        listAllVoices();

        String voiceName = (args.length > 0)
                ? args[0]
                : "kevin16";

        System.out.println();
        System.out.println("Using voice: " + voiceName);

        /* The VoiceManager manages all the voices for FreeTTS.
         */
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice helloVoice = voiceManager.getVoice(voiceName);

        if (helloVoice == null) {
            System.err.println(
                    "Cannot find a voice named "
                            + voiceName + ".  Please specify a different voice.");
            System.exit(1);
        }

        /* Allocates the resources for the voice.
         */
        helloVoice.allocate();

        /* Synthesize speech.
         */
        helloVoice.speak("Thank you for giving me a voice. "
                + "I'm so glad to say hello to this world.");

        /* Clean up and leave.
         */
        helloVoice.deallocate();
        System.exit(0);
    }
}
