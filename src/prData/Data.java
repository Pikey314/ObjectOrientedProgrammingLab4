package prData;

public class Data {

    private double[] data;
    private String[] errors;
    private double min;
    private double max;
    private boolean exceptionFlag = false;

    public Data(String[] arguments, double min, double max) {
        this.min = min;
        this.max = max;
        double [] dataTemp = new double[arguments.length];
        String [] errorsTemp = new String[arguments.length];
        int dataIndex = 0;
        int errorsIndex = 0;

        for (int i = 0; i<arguments.length; i++) {
            try {
                dataTemp[i] = Double.parseDouble(arguments[i]);
                dataIndex++;
            } catch (NumberFormatException ex) {
                errorsTemp[errorsIndex] = arguments[i];
                errorsIndex++;
            }
        }
        this.data = new double[dataIndex];
        this.errors = new String[errorsIndex];
        System.arraycopy(dataTemp, 0, this.data, 0, dataIndex);
        System.arraycopy(errorsTemp, 0, this.errors, 0, errorsIndex);


    }

    public double averageCalculation() {

        double average = 0.0;
        boolean dataInRangeFlag = false;
        int elementsNumber = 0;

        for(int i=0; i<this.data.length; i++) {
            if (this.data[i] >= min && this.data[i] <= max) {
                dataInRangeFlag = true;
                average += this.data[i];
                elementsNumber++;
            }
        }

        if (elementsNumber != 0)
            average = average/elementsNumber;

        if(!dataInRangeFlag)
            throw new DataException("No data in the range for average calculation");
        else
            return average;
    }

    public double standardDeviationCalculation() {

        try {
            double average = averageCalculation();
            double standardDeviation = 0.0;
            int elementsNumber = 0;
            for(int i=0; i<this.data.length; i++) {
                if (this.data[i] >= min && this.data[i] <= max) {
                    standardDeviation += Math.pow((this.data[i]-average), 2);
                    elementsNumber++;
                }
            }
            standardDeviation = standardDeviation/elementsNumber;
            standardDeviation = Math.sqrt(standardDeviation);
            return standardDeviation;


        } catch (Exception e) {
            System.out.println("Can't compute standard Deviation");
            return -1.0;
        }
    }

    public void setRange(String minmax) throws DataException{
        String max = "";
        String min = "";
        try {
            min = minmax.split(";")[0];
            max = minmax.split(";")[1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error, no enough values");
            this.exceptionFlag = true;
        }
        try {
            this.min = Double.parseDouble(min);
        } catch (NumberFormatException e) {
            System.out.println("Error, prasing string to real number (For input string: \"" + min +"\")");
            this.exceptionFlag = true;
        }
        try {
            this.max = Double.parseDouble(max);
        } catch (NumberFormatException e) {
            System.out.println("Error, prasing string to real number (For input string: \"" + max +"\")");
            this.exceptionFlag = true;
        }

    }

    public double[] getData(){
        return this.data;
    }

    public String[] getErrors(){
        return this.errors;
    }

    @Override
    public String toString() {

        String stringToReturn = "Min: " + this.min + ", Max: " + this.max + ",\n[ ";
        for(double singleData : this.data)
            stringToReturn += singleData + ", ";
        stringToReturn = stringToReturn.substring(0, stringToReturn.length()-2) + " ],\n[ ";
        for(String singleError : this.errors)
            stringToReturn += singleError + ", ";
        stringToReturn = stringToReturn + /*.substring(0, stringToReturn.length()-2) +*/ " ],\n";

        if (!this.exceptionFlag)
            stringToReturn = stringToReturn + "Average: " + averageCalculation() + ", Standard Deviation: " + standardDeviationCalculation() + "\n";
        else
            stringToReturn = stringToReturn + "Average: " + "ERROR" + ", Standard Deviation: " + "ERROR" + "\n";


//        stringToReturn = stringToReturn + "Average: " + averageCalculation() + ", Standard Deviation: " + standardDeviationCalculation() + "\n";
        return stringToReturn;
    }
}
