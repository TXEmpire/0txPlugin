package me.flash1110.Otxplugin.objects.nick;

public class TxPlayerNick {
	
	private		String		Name, NickSoFar, LastColour;
	private		Integer		LetterOn;
	
	public TxPlayerNick(String name) {
		
		Name = name;
		LastColour = "";
		NickSoFar = "";
		LetterOn = 1;
		
	}
	
	public boolean addColour(String Colour) {
		
		if (LetterOn > Name.length())
			return false;
		
		int firstletter = LetterOn - 1;
		
		String letter = this.Name.substring(firstletter, LetterOn);
		
		if (LastColour.equals(Colour))
			NickSoFar = NickSoFar + letter;
		else
			NickSoFar = NickSoFar + Colour + letter;
		
		
		LastColour = Colour;
		
		LetterOn++;
		
		if (LetterOn > Name.length())
			return false;
		
		return true;
		
	}
	
	public boolean removeColour() {
		
		if (LetterOn < 1)
			return false;
		
		int testlength = NickSoFar.length() - 2;
		
		if (testlength <= -1)
			return false;
		
		String lastcolourmatch = new String(NickSoFar).substring(0, testlength);
		
		if (lastcolourmatch.endsWith("�")) {
			int endlength = NickSoFar.length() - 3;			
			NickSoFar = NickSoFar.substring(0, endlength);
		} else {
			int endlength = NickSoFar.length() - 1;			
			NickSoFar = NickSoFar.substring(0, endlength);
		}
		
		LetterOn--;
		
		if (LetterOn < 1)
			return false;
		
		return true;
	}
	
	public String getNick() {
		return NickSoFar;
	}

}
