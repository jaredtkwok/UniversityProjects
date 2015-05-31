//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

import java.util.Random;

// Generates cases and shuffles the order
public class CaseGenerator{

    private CaseValue[] caseVal = new CaseValue[26];

    public CaseGenerator() {

        Random ranNum = new Random();
        for (int i = 0; i < caseVal.length; i++) {
            try {
                caseVal[i] = new CaseValue(i);
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
        try {
            for (int i = 25; i >= 0; i--) {
                int selectIndex = ranNum.nextInt(i + 1);
                CaseValue temp = caseVal[i];
                caseVal[i] = caseVal[selectIndex];
                caseVal[selectIndex] = temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public CaseValue pickCase(int c) {
        caseVal[c].setIsOpen(true);
        return caseVal[c];
    }
    
    public CaseValue showCase(int c){
        return caseVal[c];    
    }
    

}
