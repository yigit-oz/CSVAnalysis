/**
 * a class for analyzing .csv files with the headings "Country", "Exports" and "Value (dollars)".
 * 
 * apache commons and coursera libraries are used for this class  
 * https://www.dukelearntoprogram.com/course2/doc/javadoc/index.html?course=2 
 * 
 * Yiğit Özarslan
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.Scanner;

public class AnalyzeExports {
    // for testing methods
    public void tester() {
        Scanner input = new Scanner(System.in);
        FileResource fr = new FileResource();
        
        CSVParser cv = fr.getCSVParser();
        System.out.println(countryInfo(cv));

        cv = fr.getCSVParser();
        listExportersTwoProducts(cv);

        cv = fr.getCSVParser();
        int nOfExporters = numberOfExporters(cv);
        System.out.println(nOfExporters);

        cv = fr.getCSVParser();
        bigExporters(cv, "$999,999,999,999");
    }
    // returns a string of information about the country or returns “NOT FOUND” if there is no information.
    public String countryInfo(CSVParser parser) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a country for information: ");
        String country = input.nextLine();
        for(CSVRecord record: parser) {
            String name = record.get("Country");
            if(name.equalsIgnoreCase(country)){
                return name + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }    
        }
        return "NOT FOUND";
    }
    // prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
    public void listExportersTwoProducts(CSVParser parser) {
        Scanner input = new Scanner(System.in);
        System.out.println("Find the countries which export both of the items");
        System.out.println("Enter first export item: ");
        String exportItem1 = input.nextLine();
        System.out.println("Enter second export item: ");
        String exportItem2 = input.nextLine();
        System.out.println("Countries which export both:");
        for(CSVRecord record: parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    // returns the number of countries that export exportItem
    public int numberOfExporters(CSVParser parser) {
        int count = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an export item to find the total number of exporters:");
        String exportItem = input.nextLine();
        for(CSVRecord record: parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem)) {
                count++;
            } 
        }
        return count;
    }
    // prints the names of countries and their Value amount for all countries whose "Value (dollars)" string is longer than the amount string
    // amount is a String in the format of a dollar sign, followed by an integer number with a comma separator every three digits from the right 
    public void bigExporters(CSVParser parser, String amount) {
        System.out.println("Countries which has more value than " + amount);
        for(CSVRecord record: parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }

    public static void main(String[] args) {
        AnalyzeExports ex = new AnalyzeExports();
        ex.tester();
    }

}
