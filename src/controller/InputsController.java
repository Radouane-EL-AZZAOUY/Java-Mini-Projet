package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class InputsController {

    public static void checkNumbersOnly(JTextField maxLimitField, int max) {
        String input = maxLimitField.getText();
        if (!input.matches("[0-9]+") || input.length() > max) {
            if (input.length() == 0) {
                maxLimitField.setText("");
            } else {
                maxLimitField.setText(input.substring(0, input.length() - 1));
            }
        }
    }

    public static void checkFloatsOnly(JTextField durationField, int max) {
        String input = durationField.getText().trim();
        if (input.matches(".*[a-zA-Z].*")) {
            //JOptionPane.showMessageDialog(null, "Saisir seulment des nombres comme (0.00)", "Alert", JOptionPane.WARNING_MESSAGE);
            durationField.setText("");
        } else if (!input.matches("[0-9]+[.]?[0-9]*") || input.length() > max) {
            if (input.length() == 0) {
                durationField.setText("");
            } else {
                durationField.setText(input.substring(0, input.length() - 1));
            }
        }
    }
    
    public static String getDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

}
