/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer;

/**
 *
 * @author pguan
 */
public class Experiments implements Runnable{
    
    private DataSet data;

    public DataSet getData() {
        return data;
    }

    public void setData(DataSet data) {
        this.data = data;
    }
    

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
