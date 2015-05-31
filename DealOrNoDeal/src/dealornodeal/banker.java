//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

// Calculates the offer after certain amount of turns
public class Banker {

    private int offer = 0;
    private int total;

    public Banker() {
    }
    public void setOffer(int turn, CaseValue[] cases, int offer) {
        int count = 0;
        total =0;
        for (int i = 0; i < cases.length; i++) {
            if (!cases[i].isOpen()) {
                int caseInt = 0;
                String wordVal = cases[i].getValue();
                if (wordVal.compareToIgnoreCase("CAR") == 0) {
                    caseInt = 30000;
                } else if (wordVal.compareToIgnoreCase("50c") == 0) {
                    String intHolder = wordVal.subSequence(0, 1)+"";
                    caseInt = Integer.parseInt(intHolder)/100;
                } else {
                    String intHolder = wordVal.substring(1);
                    caseInt = Integer.parseInt(intHolder);
                }
                count++;
                total += caseInt;
            }
        }
        int average = total/count;
        
        this.offer = average*turn/10;
    }

    public int getOffer() {
        return offer;

    }
}
