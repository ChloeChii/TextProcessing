package Project4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) throws Exception {
    //ran algo round patternLength_m, 
    if (args.length != 4) {
        System.out.println("Please use 3 arguments as input:  [Random or Ensglish String] [algorithm] [length of pattern]");
        System.out.println("alg should be one of ['rk', 'bitap', 'brute', 'kmp', 'bmh']");  
    }
    int m = Integer.parseInt(args[3]);
    String text = "";
    String pattern = "";
    int size = 10000;
    int round = Integer.parseInt(args[2]);
    long totalTime = 0L;

    //parse first input
    if(args[0].equals("ran")){
        //create random Text and create random pattern
        for(int i = 0 ; i < round; i++){
            text = genAlphaNumericString(size, true);
            pattern = genAlphaNumericString(m, true);
            totalTime += parseSecond(args[1], text, pattern, totalTime, m);
        }

    } else if(args[0].equals("eng")) {
        //read file as a long string
        //split strings by delimitor, space, comma, etc
        //find random english words 
        for(int i = 0 ; i < round; i++){
            text = readFile("./Project4/Speech.txt");
            pattern = getText(splitFile(readFile("./Project4/words1.txt")), m);
            totalTime += parseSecond(args[1], text, pattern, totalTime, m);
      }

    }
    else{
        System.out.println("Please use 3 arguments as input: [Random or Ensglish String] [algorithm] [length of pattern]");
        System.out.println("alg should be one of ['rk', 'bitap', 'brute', 'kmp', 'bmh']");  
    }
    totalTime /= 1000;
    System.out.println("avg time : " + totalTime/round);
    
  }
  static long parseSecond(String algo, String text, String pattern, long totalTime, int m){
    //parse 2nd input
    if (algo.equals("rk")) {
        long start = System.nanoTime();
        RK rk = new RK();
        int rslt = rk.match(text, pattern);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        return timeElapsed;

    } else if (algo.equals("bitap")) {
        long start = System.nanoTime();
        Bitap bitap = new Bitap();
        text = getLongerText(splitFile(text), m);
        int rslt = bitap.match(text, pattern);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        return timeElapsed;

    } else if (algo.equals("kmp")) {
        long start = System.nanoTime();
        KMP kmp = new KMP();
        int rslt = kmp.match(text, pattern);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        return timeElapsed;
    } else if (algo.equals("bmh")) {
        long start = System.nanoTime();
        BMH bmh = new BMH();
        text = getLongerText(splitFile(text), m);
        int rslt = bmh.match(text, pattern);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        return timeElapsed;
    } else if (algo.equals("brute")) {
        long start = System.nanoTime();
        Brute brute = new Brute();
        int rslt = brute.match(text, pattern);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        return timeElapsed;
    }
    else{
        System.out.println("Please specify pattern matching algorithm. One of ['rk', 'bitap'].");
    }
    return 0L;
  }
  static String genAlphaNumericString(int size, boolean isNumeric){
      String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "abcdefghijklmnopqrstuvxyz";
      if (isNumeric) AlphaNumericString += "0123456789";

      StringBuilder sb = new StringBuilder();
      for(int i = 0 ; i < size ; i++){
          int index = (int)(AlphaNumericString.length() * Math.random());
          sb.append(AlphaNumericString.charAt(index));
      }
      return sb.toString();
  }
  static String getText(String[] file, int m){
      while(true){
          int idx = (int) (file.length * Math.random());
          //find the Strings that are longer than size
          if(file[idx].length() == m ) { 
            return file[idx];
          }
      }
  }
  static String getLongerText(String[] file, int m){
    while(true){
        int idx = (int) (file.length * Math.random());
        //find the Strings that are longer than size
        if(file[idx].length() >= m ) { 
          return file[idx];
        }
    }
}
  static String readFile(String p) throws Exception{
      Path path = Paths.get(p);
      return Files.readString(path);
  }
  static String[] splitFile(String content){
    return content.split("[\\—\\s+;:.,\\\\]");
  }
}

