import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

public class New {
    public static void main(String[] args) throws IOException {

        int step = 0;
        final double l = 0.8;
        final double S = 25E-4;
        final double N = 22; // Варіант в групі
        final double G = 4; // номер групи
        double f = 35 + (5 * G);

        final double R = 1 + N;
        double B, w, h, T;
        double t, psi = 0, i = 0;
        w = 400 + N;
        h = 1 / (f * 360);
        B = l / (w * S);
        T = 1 / f;
        double e ;

        FileWriter fileWriter = new FileWriter("zx.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.format("%4s" + "%5s" + "  %11s" + "%7s" + "  %10s" + "%11s" + " \n","Крок","t","psi(t)", "i(t)","e(t)", "psi(t+dt)");
        for (t = 0; t <= 2.25 * T; t = t + h) {
//            e = (200 + 2 * N) * Math.sin((2 * Math.PI * f * t) + Math.toRadians(10 * G));
            if (step == 0){
                e = (200 + 2 * N) * Math.sin((2 * Math.PI * f * 0.02) + Math.toRadians(10 * G));
            }else {
                e = (200 + 2 * N) * Math.sin((2 * Math.PI * f * t) + Math.toRadians(10 * G));
            }
            String roundedE = String.format("%.5f", e);
            psi = psi + h * (e - (R * i));
            String roundedPsi = String.format("%.4f", psi);
            i = (0.12 * psi * B) +  (1.25 * Math.pow(B,5) * Math.pow(psi, 5));
            String roundedI = String.format("%.4f", i);
            printWriter.printf("%4s  %s  %7s   %s  %s   %s \n", step, String.format("%.5f", t), roundedPsi, roundedI, roundedE, roundedPsi);
            step ++;
        }


    }

}
