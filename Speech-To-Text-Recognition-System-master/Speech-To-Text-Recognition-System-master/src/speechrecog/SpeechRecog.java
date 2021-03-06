/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speechrecog;
//import of API classes
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.Lattice;
import edu.cmu.sphinx.result.WordResult;
//import of IO classes
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.FileWriter;
import  java.io.PrintWriter;
//import of swing components
import javax.swing.JOptionPane;



/**
 *
 * @author int3ll3ct, Fabusuyi012
 */
public class SpeechRecog {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Configuration config  = new Configuration();
            //declaration of paths to attributes
            String a_path = "resource:/edu/cmu/sphinx/models/en-us/en-us";
            String d_path = "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
            String l_path = "resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin";
            //setting paths to attributes
            config.setAcousticModelPath(a_path);
            config.setDictionaryPath(d_path);
            config.setLanguageModelPath(l_path);
            //adding attribute so a SpeechRecognizer
            //StreamSpeechRecognizer recog = new StreamSpeechRecognizer(config);
            LiveSpeechRecognizer recog = new LiveSpeechRecognizer(config);
            //creating the .wav audio file using IO FileWriting to make sure it exists in the project folder
            //String audio = "audio.wav";
            //PrintWriter output = new PrintWriter(new FileWriter(audio));
            //transcribing the audio.wav file
            //InputStream stream = new FileInputStream(new File(audio));
            //start of audio content recogniton
            //recog.startRecognition(stream);
            recog.startRecognition(true);
            SpeechResult result = recog.getResult(); //result obtained from recognized contents of the audio
            //decoding telephone audio quality with sample rate 8000Hz
            config.setSampleRate(8000);
            //while((result=recog.getResult())!=null){
                //printing user utterances without filtering
                System.out.println("Hypothesis: "+result.getHypothesis());
            //}
            for(WordResult w_result: result.getWords()){
                System.out.println(w_result);
            }
            //Lattice lattice = null;
            result.getLattice().dumpDot("lattice.dot", "lattice");
            //end of audio content recognition
            recog.stopRecognition();
            /*Questions to be asked - How to check for certain text in user voice input, 
            how to train the machine with certain words, how to accept user input,
            how to show audio wave audibility strength on JAVA Swing GUI
            */
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error in main method: "+ex.getMessage());
        }
        
    }
    /*public void resultAccepted(ResultEvent e){
        Result r = (Result)e.getSource();
        ResultToken tokens[] = r.getBestTokens();
        for(int i=0;i<tokens.length;i++){
            System.out.print(tokens[i].getText()+" "); 
            System.out.println("");
        }
        //deallocating the recognizer and exiting
        try{
        rec.deallocate();
        System.exit(0);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Deallocation/Exiting error: "+ex.getMessage());
        }
        
    }*/
    
}
