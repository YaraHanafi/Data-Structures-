public class ProblemSet1 {
    public static void main(String[] args){
        
        // for(int i=0; i<5; i++){
           // for(int j=0; j<=i; j++){
            //    System.out.print("* ");
            // }
            // System.out.println();
        //}

        double [][] scores = new double[5][3];
        for(int r=0; r<5; r++){
            for (int c=0; c<3; c++){
                scores [r][c] =  Math.round(Math.random()*101);
            }
        }
            
        for (int r=0; r<5; r++){ 
            System.out.print("Student" + r);
            for (int e=0; e<3;e++){ 
                System.out.print("  " + scores[r][e]);
            }
            System.out.println();
        }

        for(int r=0; r<5; r++){
            double sum =0;
            for(int c=0; c<3; c++){
                sum = sum + scores[r][c];
            }
            System.out.println("Student" +r + " " + sum);
        }

        for(int i=0; i<3; i++){
            double a = 0;
            for(int j=0; j<5; j++){
                a = a + scores [j][i];
            }
        System.out.println("a" + i + " " + a/5);
        }
    }
    
}