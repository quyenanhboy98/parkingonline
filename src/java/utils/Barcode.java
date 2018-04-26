/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.barcodelib.barcode.Linear;
import java.io.OutputStream;
import java.io.Serializable;

/**
 *
 * @author Yuu
 */
public class Barcode implements Serializable {

    public static void CreateBarCode(String code, OutputStream out) throws Exception {
        Linear linear = new Linear();
        linear.setData(code);
        linear.setType(Linear.CODE128);
        linear.renderBarcode(out);
    }
}
