package BackEnd;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Created by ViruSs on 25-Oct-17.
 */
public class Dictionary extends DicAbstract {
        
    public Dictionary() {
        data = new HashMap();
        keys = new ArrayList();
        /*try {
            readData();
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void readData() throws IOException, BiffException {
        String FilePath = "Book1.xls";
        FileInputStream fs = new FileInputStream(FilePath);
        WorkbookSettings ws = new WorkbookSettings();
        ws.setEncoding("Cp1252");
        Workbook wb = Workbook.getWorkbook(fs, ws);        
        int totalNoOfSheet = wb.getNumberOfSheets();
        for (int sheet = 0; sheet < totalNoOfSheet; sheet++){
            int totalNoOfRows = wb.getSheet(sheet).getRows();
            for (int row = 0; row < totalNoOfRows; row++) {
                String word = wb.getSheet(sheet).getCell(0, row).getContents();
                keys.add(word);
                data.put(word, wb.getSheet(sheet).getCell(1, row).getContents());
            }
            Collections.sort(keys);
        }
    }

    public int binarySearch(String w, ArrayList<String> k){
        if (k.get(0).compareTo(w)>0) return 0;
        int d = 0, c = k.size();
        while (d<c-1){
            int g = (d+c)/2;
            if (k.get(g).compareTo(w)<0) 
                d=g;
            else c=g;
        }
        return c;
    }
    
    @Override
    public void addWord(String word,String definition){
        word = word.toLowerCase();
        data.put(word, definition);
        int n = binarySearch(word,keys);
        keys.add(n, word);
    }
    @Override
    public void removeWord(String word) {
        int n = keys.lastIndexOf(word);
        if (n>=0 && n<=keys.size()-1){
            keys.remove(n);
            data.remove(word);
        }
    }
    @Override
    public void modifyWord(String word, String newWord, String newDefinition) {
        if (newWord.equals("")){
            data.replace(word, newDefinition);
        }
        else if (newDefinition.equals("")){
            String defin = new String();
            defin = data.get(word);
            removeWord(word);
            addWord(newWord, defin);

            }
        else{
                removeWord(word);
                addWord(newWord,newDefinition);
            }
    }


}