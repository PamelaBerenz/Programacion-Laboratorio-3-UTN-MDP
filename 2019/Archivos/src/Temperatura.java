import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Temperatura {
	public static void escribir() {
		String dia = "11 Agosto 1988";
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream("genesis.tmp"));
			dos.writeUTF(dia); // escribe registro inicial
			for (int hora = 0; hora < 24; hora++) 
			{
				double presion, temp;
				presion = presHora();
				temp = tempHora();
				// escribe seg�n la estructura de cada registro
				dos.writeInt(hora);
				dos.writeDouble(presion);
				dos.writeDouble(temp);
			}
		} catch (IOException e) {
			System.out.println(" Anomal�a en el flujo de salida " + e.getMessage());
		} finally {
			try {
				dos.close();
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
	}

	// m�todos auxiliares para generar temperatura y presi�n
	static private double presHora() {
		final double PREINF = 680.0;
		final double PRESUP = 790.0;
		return (Math.random() * (PRESUP - PREINF) + PREINF);
	}

	static private double tempHora() {
		final double TEMINF = 5.0;
		final double TEMSUP = 40.0;
		return (Math.random() * (TEMSUP - TEMINF) + TEMINF);
	}

	public static void leer() {
		String dia;
		double mxt = -11.0; // valor m�nimo para encontrar m�ximo
		FileInputStream f;
		DataInputStream dis = null;
		try {
			f = new FileInputStream("genesis.tmp");
			dis = new DataInputStream(f);
		} catch (IOException io) {
			System.out.println("Anomal�a al crear flujo de entrada, " + io.getMessage());
			return; // termina la ejecuci�n
		}
		// proceso del flujo
		try {
			int hora;
			boolean mas = true;
			double p, temp;
			dia = dis.readUTF();
			System.out.println(dia);
			while (mas) 
			{
				hora = dis.readInt();
				p = dis.readDouble();
				temp = dis.readDouble();
				System.out.println("Hora: " + hora + "\t Presi�n: " + p + "\t Temperatura: " + temp);
				mxt = Math.max(mxt, temp);
			}
		} catch (EOFException eof) {
			System.out.println("Fin de lectura del archivo.\n");
		} catch (IOException io) {
			System.out.println("Anomal�a al leer flujo de entrada, " + io.getMessage());
			return; // termina la ejecuci�n
		} finally {
			try {
				dis.close();
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
		// termina el proceso, escribe la temperatura m�xima
		System.out.println("\n La temperatura m�xima: " + (float) mxt);
	}

}
