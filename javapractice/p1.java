 //math

 import static java.lang.Math.*;
 import static java.lang.System.*;


 class p1{

    public static void main(String args[]){
        double val = 90.7552;
        out.println("round("+val+")="+round(val)); //반올림

        val *= 100; //곱하기100
        out.println("round("+val+")="+round(val)); //반올림
        out.println("round("+val+")/100=" + round(val)/100); //반올림
        out.println("round("+val+")/100.0=" + round(val)/100.0); //반올림
        out.println();

        out.printf("ceil(%3.1f)=%3.1f%n", 1.1, ceil(1.1)); // 올림
        out.printf("floor(%3.1f)=%3.1f%n", 1.5, floor(1.5)); // 버림

        out.printf("round(%3.1f)=%d%n", 1.1, round(1.1)); // 반올림
        out.printf("round(%3.1f)=%d%n", 1.5, round(1.5)); // 반올림

        out.printf("rint(%3.1f)=%f%n", 1.5, rint(1.5)); //반올림 - 소수점 첫자리에서 반올림하지만 반환값이 double
        out.printf("round(%3.1f)=%d%n", -1.5, round(-1.5)); // 반올림

        out.printf("rint(%3.1f)=%f%n", -1.5, rint(-1.5)); // 반올림
        out.printf("ceil(%3.1f)=%3.1f%n", -1.5, ceil(-1.5)); // 올림
        out.printf("floor(%3.1f)=%3.1f%n", -1.5, floor(-1.5)); // 버림

    }
 }