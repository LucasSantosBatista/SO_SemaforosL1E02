/**
 * 
 */
package controller;

import java.util.concurrent.Semaphore;

/**
 * @author Lucas Batista 24 de mar. de 2024
 */
public class ThreadCozimento extends Thread {
	private int id;
	private static final Semaphore controleCozimento = new Semaphore(5); // Semáforo para controlar o número máximo de
																			// pratos sendo cozidos
	private static final Semaphore entregaSopaCebola = new Semaphore(1); // Semáforo para garantir que apenas uma sopa
																			// de cebola seja entregue por vez
	private static final Semaphore entregaLasanhaBolonhesa = new Semaphore(1); // Semáforo para garantir que apenas uma
																				// lasanha bolonhesa seja entregue por
																				// vez

	public ThreadCozimento(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		try {
			controleCozimento.acquire(); 
			System.out.println("Prato " + id + " iniciado");

			// Simula o cozimento do prato
			double tempo = (id % 2 == 0) ? (0.6 + Math.random() * 0.6) : (0.5 + Math.random() * 0.3);
			for (int i = 0; i < 10; i++) {
				Thread.sleep((long) (tempo * 100));
				System.out.println("Prato " + id + " - " + (i + 1) * 10 + "% pronto");
			}

			if (id % 2 == 0) {
				entregaLasanhaBolonhesa.acquire(); 
			} else {
				entregaSopaCebola.acquire(); 
			}

			Thread.sleep(500); 
			System.out.println("Prato " + id + " pronto e entregue");

			if (id % 2 == 0) {
				entregaLasanhaBolonhesa.release(); 
			} else {
				entregaSopaCebola.release();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			controleCozimento.release(); 
		}
	}
}
