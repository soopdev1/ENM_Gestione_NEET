package it.refill.cf;

import org.joda.time.DateTime;

public class DataPanel {

    public static String getCF(String nameField, String surnameField, String fm, DateTime birtDate, String town) {

        String name = nameField.toUpperCase();
        String surname = surnameField.toUpperCase();

        String birthday = birtDate.dayOfMonth().getAsString();
        String month = String.valueOf(birtDate.monthOfYear().get());
        String year = birtDate.year().getAsString();

        String fiscalCode = ComputeFiscalCode.computeSurname(surname);
        fiscalCode += ComputeFiscalCode.computeName(name);
        fiscalCode += ComputeFiscalCode.computeDateOfBirth(birthday, month, year, fm.toLowerCase());
        fiscalCode += town;
        try {
            fiscalCode += ComputeFiscalCode.computeControlChar(fiscalCode);
            if (fiscalCode.length() == 16) {
                return fiscalCode;
            }
        } catch (InterruptedException e) {
            System.out.println("Error in calcListener");
        }

        return "";
    }

}
