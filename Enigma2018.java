import hsa.*;
public class Enigma2018
{
    public static void main (String[] args)
    {
	//Initializing the label and rotors
	String label = "ABCDEFGH";
	String rotor1 = "FCGHAEBD";
	String rotor2 = "DGECHFAB";
	String rotor3 = "GFHCDBEA";

	int rotor1Clicks = 0;    
	int rotor2Clicks = 0;
	int rotor3Clicks = 0;
	
	//loop for the slowest rotor
	rotor1Clicks = 0;
	do {
	    //loop for the middle rotor
	    rotor2Clicks = 0;
	    do{
		//loop for the fastest rotor
		rotor3Clicks = 0;
		do{
		    //Asking the user for plaintext
		    char letter = Stdin.readChar ();
		    
		    //Going through first rotor
		    int position = label.indexOf(letter);
		    letter = rotor1.charAt(position);

		    //Going through second rotor 
		    position = label.indexOf (letter);
		    letter = rotor2.charAt (position);
		    
		    //Going through last rotor
		    position = label.indexOf (letter);
		    Stdout.println (rotor3.charAt (position));

		    //Turning the fastest rotor
		    rotor3 = rotor3.substring (1) + rotor3.charAt (0);
		    rotor3Clicks++;
		}while (rotor3Clicks != rotor3.length ());
		//Turning the middle rotor
		rotor2 = rotor2.substring (1) + rotor2.charAt (0);
		rotor2Clicks++;
	    }while (rotor2Clicks != rotor2.length ());
	    //turning the slowest rotor
	    rotor1 = rotor1.substring (1) + rotor1.charAt (0);
	    rotor1Clicks++; 
	}while (true);
	//Condition kept as true as to not exit the loop
    }
}

