package code.google.com;

public class Local {  
	
    private String localName;
    private String localMedida;
    private int localImage;  
   
    
    public String getLocalMedida(){
		return localMedida;
    }
    public void setLocalMedida(String localMedida){
    	this.localMedida = localMedida;
    }
    public String getLocalName() {
        return localName;
    }
    public void setLocalName(String localName) {
        this.localName = localName;
    }
	public int getLocalImage() {
		return localImage;
	}
	public void setLocalImage(int i) {
		this.localImage = i;
	}

}

