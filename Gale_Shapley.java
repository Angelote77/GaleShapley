import java.util.*;
public class Gale_Shapley {
	//Poner una cantidad de hombres y mujeres predefinida
	public static int Cant=5;
	
	public static void main(String[] args) {
		int prefeHombres[][] = new int[][] {{7, 5, 6, 8, 9},
            						   		{5, 8, 6, 7, 9},
            						   		{9, 5, 6, 7, 8},
            						   		{8, 5, 6, 7, 9},
            						   		{9, 8, 7, 6, 5},
            						   		{1, 2, 3, 4, 0},
            						   		{0, 1, 2, 3, 4},
            						   		{0, 1, 2, 3, 4},
            						   		{0, 1, 2, 3, 4},
            						   		{0, 1, 2, 3, 4}};
            						   		
        int prefeMujeres[][] = new int[][] {{6, 7, 8, 9, 5},
	   										{5, 6, 7, 8, 9},
	   										{5, 6, 7, 8, 9},
	   										{5, 6, 7, 8, 9},
	   										{5, 6, 7, 8, 9},
	   										{2, 0, 1, 3, 4},
            						   		{0, 3, 1, 2, 4},
            						   		{4, 0, 1, 2, 3},
            						   		{3, 0, 1, 2, 4},
            						   		{4, 3, 2, 1, 0}};
            						   		
        System.out.println("- Preferencia de hombres");
        System.out.println("Mujeres Hombres");
        establecer_Parejas(prefeHombres);
        System.out.println("\n- Preferencias de mujeres");
        System.out.println("Hombres Mujeres");
        establecer_Parejas(prefeMujeres);
	}
	
	//método para retornar verdadero si la mujer prefiere al nuevo sobre el anterior hombre
	//y también para conocer si el hombre prefiere a la nueva sobre la anterior mujer
	public static boolean Preferencia(int arreglo[][], int elige,int nuevo,int actual) {	
		//Comprobar si el que elige prefiere al nuevo antes que al actual
		for(int i=0; i<Cant; i++) {
			//si el nuevo se encuentra antes en la lista que el actual entonces hay preferencia por el nuevo
			if(arreglo[elige][i]==actual) 
				return true;
			//si el actual se encuentra antes que el nuevo en la lista entonces hay preferencia por actual
			if(arreglo[elige][i]==nuevo)
				return false;
		}
		return false;
	}
	
	public static void establecer_Parejas(int[][]arreglo) {
		int pareja[] = new int[Cant];
		boolean solteros[] = new boolean[Cant];
		
		//inicializar a hombre y mujeres como solteros/as
		Arrays.fill(pareja, -1);
		int cantSolteros = Cant;
		
		//mientras los hombres o las mujeres sean solteros(as)
		while(cantSolteros>0) {
			
			//Tomar al primero(a) soltero(a) de la lista
			int nuevo;
			for(nuevo=0; nuevo<Cant; nuevo++) {
				if(solteros[nuevo]==false)
					break;
			}
			//uno(a) a uno(a) van llendo a las mujeres u hombres de acuerdo a la preferencia del tomado(a) anteriormente en x
			for(int i=0; i<Cant && solteros[nuevo]==false; i++) {
				int elige =arreglo[nuevo][i];
				//la mujer o el hombre de preferencia es soltera(o) por lo que aux y x pueden estar juntos por el momento
				if(pareja[elige-Cant]==-1) {
					pareja[elige-Cant] = nuevo;
					solteros[nuevo]=true;
					cantSolteros--;
				}else { // si no es soltero(a) 
					//encontrar la pareja actual
					int actual = pareja[elige-Cant];
					//llamamos al método de preferencia
					if(Preferencia(arreglo,elige,nuevo,actual)==false) {
						pareja[elige-Cant] = nuevo;
						solteros[nuevo] = true;
						solteros[actual] = false;
					}
				}
			}
		}
		for (int i = 0; i < Cant; i++){
		    System.out.print(" ");
		    System.out.println(" " + (i+Cant) + "        " + pareja[i]);
		}
	}
}
