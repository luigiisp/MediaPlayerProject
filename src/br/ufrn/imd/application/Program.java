package br.ufrn.imd.application;

import java.io.BufferedReader;
import java.io.FileReader;

public class Program {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(""));
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
