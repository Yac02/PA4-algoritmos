public class OrdenamientoCampus 
{
    public static void mergeSort(Ubicacion[] arr, int left, int right) 
    {
        if (left < right) 
        {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    public static void merge(Ubicacion[] arr, int left, int mid, int right) 
    {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Ubicacion[] L = new Ubicacion[n1];
        Ubicacion[] R = new Ubicacion[n2];

        // Copiar datos a los arreglos temporales
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        // Índices iniciales para recorrer los subarreglos temporales
        int i = 0, j = 0;
        // Índice inicial para el arreglo original
        int k = left;

        // Comparar elementos de L y R y colocarlos en orden en el arreglo original
        while (i < n1 && j < n2) {
            if (L[i].getDistancia() <= R[j].getDistancia()) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copiar los elementos restantes de L (si quedan)
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copiar los elementos restantes de R (si quedan)
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Método principal para probar el algoritmo
    public static void main(String[] args) {
        System.out.println("=== MÓDULO INTELIGENTE DE ORIENTACIÓN DEL CAMPUS ===");
        System.out.println("Ordenando ubicaciones por cercanía (Merge Sort)...\n");

        // Arreglo desordenado de ubicaciones
        Ubicacion[] ubicaciones = {
            new Ubicacion("Laboratorio de IA", 450),
            new Ubicacion("Biblioteca Central", 120),
            new Ubicacion("Cafetería", 300),
            new Ubicacion("Aula 101", 80),
            new Ubicacion("Oficina de Bienestar", 200),
            new Ubicacion("Auditorio", 150)
        };

        System.out.println("--- Ubicaciones ANTES de ordenar ---");
        for (Ubicacion u : ubicaciones) {
            System.out.println(u);
        }

        // Llamada al algoritmo recursivo
        mergeSort(ubicaciones, 0, ubicaciones.length - 1);

        System.out.println("\n--- Ubicaciones DESPUÉS de ordenar (Más cercanas primero) ---");
        for (Ubicacion u : ubicaciones) {
            System.out.println(u);
        }
        
        System.out.println("\nExito: El sistema ya puede recomendar la ruta mas corta.");
    }
}
