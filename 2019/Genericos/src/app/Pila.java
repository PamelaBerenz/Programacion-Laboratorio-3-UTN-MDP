package app;

public class Pila<T> {
	final int TAM = 100;
	T datos[];
	int elementos;

	@SuppressWarnings("unchecked")
	public Pila() {
		elementos = 0;
		datos = (T[]) new Object[TAM];
	}

	// a�adir un elemento a la pila
	void insertar(T elem)  
	{
		if (elementos < TAM)
			datos[elementos++] = elem;
	}

	// obtener un elemento de la pila
	T quitar() {
		if (!vacia())
			return datos[--elementos];
		return null;
		
	}

	// n�mero de elementos reales en la pila
	public int numero() {
		return elementos;
	}

	// �est� la pila vac�a?
	public boolean vacia() {
		return elementos == 0;
	}

	// leer el elemento cima de la pila
	public T cima() {
		if (vacia())
			return null;
		int p = elementos;
		return datos[--p];
	}
}