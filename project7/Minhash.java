package project7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Minhash {
	private int numPermutations = 200;
	public Set<String> s = new HashSet<String>();
	public int numTerms;
	public int mod;
	public List<Pair> AB ;

	public double jaccard(String fA, String fB) throws IOException  {
		// System.out.println("exact jaccard:" + exactJaccard(fA, fB));
		
		try {
			numTerms += numUnique(s, fA);
			numTerms += numUnique(s, fB);
		} catch (IOException e) {
			System.out.println("Read file exception");
			e.printStackTrace();
		}
		
		mod = nextPrime(numTerms);
		AB = generateCoefficient(mod);
		int[] hash1 = new int[numPermutations];
		int[] hash2  = new int[numPermutations];

		try {
			hash1 = minHashSig(fA);
			hash2 = minHashSig(fB);
		} catch (IOException e1) {
			System.out.println("minHash failed");
			e1.printStackTrace();
		}
		return(approximateJaccard(hash1, hash2));
	}
	
	public double approximateJaccard(int[] d1, int[] d2) {
		double numMatch = 0.0;
		for(int i = 0; i < numPermutations; i++) {
			if(d1[i] == d2[i]) numMatch ++;
		}
		return (numMatch / numPermutations );
	}
	public double exactJaccard(String fA, String fB) throws IOException {
		Set<String> words1 = uniqueWordList(fA);
		Set<String> words2 = uniqueWordList(fB);
		int a = words1.size();
		int b = words2.size();
		words1.retainAll(words2);
		int intersect = words1.size();
		return((double) intersect / (a + b - intersect));
	}	

  	public static Set<String> uniqueWordList(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader b = new BufferedReader(fr);
		Set<String> s = new HashSet<String>();
		String line;
		String[] words;
		while((line = b.readLine()) != null) {
			words = line.replaceAll("[.,:;']", "").toLowerCase().split("\\s+");
			for(int i = 0; i < words.length; i++) {
				s.add(words[i]);
			}
		}
		b.close();
		return(s);
	}
	public int[] minHashSig(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader b = new BufferedReader(fr);
		String line;
		String[] words;
		int hashVal;
		int[] minHashVals = new int[numPermutations];
		Arrays.fill(minHashVals, Integer.MAX_VALUE);
		while((line = b.readLine()) != null) { //iterate through lines
			words = line.replaceAll("[.,:;']", "").toLowerCase().split("\\s+"); //remove punctuation
			for(int j = 0; j < words.length; j++) { //iterate through words//apple
				for(int i = 0; i < numPermutations; i++) { //hash through k-functions//
					hashVal = word2int(words[j], AB.get(i).a, AB.get(i).b, mod);//
					if(hashVal < minHashVals[i]) minHashVals[i] = hashVal;
				}
			}
		}
		b.close();
	
		return(minHashVals);
	}

	private int word2int(String s, int a, int b, int mod) {
		return (s.hashCode() * a + b) % mod;
	}

	public static int nextPrime(int n) {
		boolean isPrime = false;
		int m = n;
		while(!isPrime) {
			isPrime = isPrime(++m);
		}	
		return(m);
	}

	public static boolean isPrime(int n) {
		if(n == 1) return(false);
		else if(n == 2 || n == 3) return(true);
		else if(n % 2 == 0 || n % 3 == 0) return(false);
		else {
			for(int i = 5; i*i < n + 1; i += 6) {
				if(n % i == 0 || n % (i + 2) == 0) {
					return(false);
				}
			}
			return(true);
		}
	}

	public List<Pair> generateCoefficient(int mod) {
		Random r = new Random();
		List<Pair> coef = new ArrayList<Pair>();
		
		Pair p = new Pair(r.nextInt(mod), r.nextInt(mod));
		for(int i = 0; i < numPermutations; i++) {
			while(coef.contains(p)) {
				p = new Pair(r.nextInt(mod), r.nextInt(mod));
			}
			coef.add(p);
		}
		return(coef);
	}

	public static int numUnique(Set<String> s, String fileName) throws IOException {
		FileReader fr;
		BufferedReader b;
		fr = new FileReader("./" + fileName);
		b = new BufferedReader(fr);
		
		String line;
		String[] words;
		while((line = b.readLine()) != null) { //iterate through lines
			words = line.replaceAll("[.,:;']", "").toLowerCase().split("\\s+"); //remove punctuation
			for(int j = 0; j < words.length; j++) { //iterate through words
				s.add(words[j]);
			}
		}
		b.close();
		return(s.size());
	}

	public class Pair {
		int a, b;
		
		/**
		 * Creates a new coefficient pair container.
		 * @param a The first coefficient.
		 * @param b The second coefficient.
		 */
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		/**
		 * Checks if another pair container is equal to this one.
		 * @param other The other pair to check for equality.
		 * @return true if both coefficients are equal.  Otherwise false.
		 */
		@Override
		public boolean equals(Object other) {
			if(other == null) return(false);
			if(other == this) return(true);
			if(!(other instanceof Pair)) return(false);
			
			Pair p = (Pair) other;
			return(a == p.a && b == p.b);
		}
	}
}

