package ex2018;

import java.util.*;
import static java.nio.file.StandardOpenOption.*;
import static java.nio.file.StandardCopyOption.*;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class MainControl {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int opc;
		LocalDate fec, fechaActual = LocalDate.now();
		Path salida = Paths.get("log");
		Path crea= Paths.get("total.txt");

		System.out.println("Anota el nombre de la carpeta:");
		String nombre = sc.nextLine();
		Path file = Paths.get(nombre);
		Charset charset = Charset.forName("UTF-8");
		if (!Files.exists(file) || !Files.isDirectory(file))
			System.out.println("Error");
		else {
			do {
				System.out.println("1.Añadir Extension\n2.Crear total.txt\n3.incidencias");
				opc = sc.nextInt();
				switch (opc) {
				case 1:
					sc.nextLine();
					System.out.println("Anota el nombre del archivo:");
					String archivo = sc.nextLine();
					Path archi = Paths.get(archivo);

					if (Files.isRegularFile(archi)) {
						try (BufferedWriter writer = Files.newBufferedWriter(salida, charset, CREATE, WRITE, APPEND)) {
							do {
								System.out.println("Anota la fecha de incidencia:");
								String fecha = sc.nextLine();
								fec = LocalDate.parse(fecha);
							} while (fec.isAfter(fechaActual) || fec.isEqual(fechaActual));
							System.out.println("Anota el codigo de incidencia:");
							String cod = sc.nextLine();
							System.out.println("Anota la incidencia:");
							String incidencia = sc.nextLine();
							writer.newLine();
							writer.close();
						} catch (IOException x) {
							System.err.format("IOException: %s%n", x);
						}
					}
					break;
				case 2:
					sc.nextLine();
					
					try {
						Files.copy(salida, crea,REPLACE_EXISTING);			
					} 
					catch (NoSuchFileException x)
					{
						System.err.format("%s no existe ",x.getMessage());
					}
					catch (IOException x) {
						System.err.println(x);
					}
					break;
				case 3:
					sc.nextLine();
					charset=Charset.forName("UTF-8");
					BufferedReader reader=null;
					try {
						reader=Files.newBufferedReader(crea,charset);
						System.out.println("Anota el codigo de error:");
						String cod=sc.nextLine();
						String line=null;
						while ((line = reader.readLine()) != null) {
							for (int i = 0; i <= line.length(); i++) {
								}
							}
						
					}catch(IOException e) {
						System.err.format("IOException: %s%n", e);
					}
					break;
				case 4:
					break;
				default:
					System.out.println("opcion incorrecta");
				}
			} while (opc != 4);
		}

	}

}
