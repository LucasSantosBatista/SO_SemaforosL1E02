/**
 * 
 */
package view;

import controller.ThreadCozimento;

/**
 * @author Lucas Batista 24 de mar. de 2024
 */
public class Main {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {

			ThreadCozimento thread = new ThreadCozimento(i);
			thread.start();
		}
	}
}