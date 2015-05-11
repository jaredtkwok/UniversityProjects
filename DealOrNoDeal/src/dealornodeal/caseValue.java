//********************************************************************
//  Project 50
//  Author: Jared Kwok(1382534) and Mike Jiang(1240129)    
//********************************************************************
package dealornodeal;

public class caseValue {

    String[] cases = new String[26];
    String[] value = {"50c", "$1", "$2", "$5", "$10", "$20", "$50", "$100",
        "$150", "$200", "$250", "$500", "$750", "$1000", "$2000", "$3000",
        "$4000", "$5000", "$10000", "$15000", "$20000", "CAR", "$50000",
        "$75000", "$100000", "$200000"
    };
    private int caseNum;
    private boolean isOpen;
    private boolean casePicked;
    private boolean selectedCase;

    public caseValue(int caseNum) throws Exception {

        if (caseNum < 0 || caseNum > 26) {
            throw new Exception("Case Number not found");
        }
        this.caseNum = caseNum;
        casePicked = false;
        isOpen = false;
    }

    public String getValue() {
        return value[caseNum];

    }

    public int getCaseNum() {
        return caseNum;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isCasePicked() {
        return casePicked;
    }

    public void setCasePicked(boolean picked) {
        this.casePicked = picked;
    }

    public String toString() {
        String caseName = "";
        if (!isOpen) {
            caseName = caseNum + "";
        } else {
            caseName += value[caseNum];
        }
        return caseName;
    }

}
