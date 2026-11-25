import java.util.*;

public class Actividad3 {
    record Arista(String destino, int metros) {}
    record Estado(String nodo, int g, int f) {}
    static final Map<String, List<Arista>> grafo = new TreeMap<>();
    static final Map<String, Integer> h = Map.of("A",40,"B",20,"C",60,"D",40,"E",20,"F",0);

    static void conectar(String a, String b, int metros) {
        grafo.computeIfAbsent(a, k -> new ArrayList<>()).add(new Arista(b, metros));
        grafo.computeIfAbsent(b, k -> new ArrayList<>()).add(new Arista(a, metros));
    }
    static List<String> dfs(String a, String meta, Set<String> visitados, List<String> ruta) {
        visitados.add(a); ruta.add(a);
        if (a.equals(meta)) return new ArrayList<>(ruta);
        for (Arista e : grafo.get(a)) {
            if (!visitados.contains(e.destino())) {
                List<String> r = dfs(e.destino(), meta, visitados, ruta);
                if (!r.isEmpty()) return r;
            }
        }
        ruta.remove(ruta.size()-1);
        return List.of();
    }
    static List<String> reconstruir(Map<String,String> padre, String meta) {
        LinkedList<String> ruta = new LinkedList<>();
        for (String n=meta; n!=null; n=padre.get(n)) ruta.addFirst(n);
        return ruta;
    }
    static List<String> bfs(String inicio, String meta) {
        Queue<String> cola = new ArrayDeque<>();
        Set<String> vistos = new HashSet<>();
        Map<String,String> padre = new HashMap<>();
        cola.add(inicio); vistos.add(inicio);
        while (!cola.isEmpty()) {
            String n = cola.remove();
            if (n.equals(meta)) return reconstruir(padre, meta);
            for (Arista e : grafo.get(n)) if (vistos.add(e.destino())) {
                padre.put(e.destino(), n); cola.add(e.destino());
            }
        }
        return List.of();
    }
    // La heuristica de este ejemplo esta definida para la meta F.
    static List<String> aEstrella(String inicio) {
        String meta = "F";
        PriorityQueue<Estado> abiertos = new PriorityQueue<>(Comparator.comparingInt(Estado::f).thenComparing(Estado::nodo));
        Map<String,Integer> dist = new HashMap<>();
        Map<String,String> padre = new HashMap<>();
        dist.put(inicio,0); abiertos.add(new Estado(inicio,0,h.get(inicio)));
        while (!abiertos.isEmpty()) {
            Estado actual = abiertos.remove();
            if (actual.g()!=dist.get(actual.nodo())) continue;
            if (actual.nodo().equals(meta)) return reconstruir(padre,meta);
            for (Arista e : grafo.get(actual.nodo())) {
                int nuevo = actual.g()+e.metros();
                if (nuevo < dist.getOrDefault(e.destino(), Integer.MAX_VALUE)) {
                    dist.put(e.destino(),nuevo); padre.put(e.destino(),actual.nodo());
                    abiertos.add(new Estado(e.destino(),nuevo,nuevo+h.get(e.destino())));
                }
            }
        }
        return List.of();
    }
    static int costo(List<String> ruta) {
        int total=0;
        for (int i=1;i<ruta.size();i++) {
            String destino=ruta.get(i);
            total+=grafo.get(ruta.get(i-1)).stream().filter(e->e.destino().equals(destino)).findFirst().orElseThrow().metros();
        }
        return total;
    }
    static void mostrar(String nombre, List<String> ruta) {
        if (ruta.isEmpty()) System.out.println(nombre+": sin ruta");
        else System.out.println(nombre+": "+String.join(" -> ",ruta)+" | "+(ruta.size()-1)+" conexiones | "+costo(ruta)+" m");
    }
    public static void main(String[] args) {
        conectar("A","B",30); conectar("B","F",90);
        conectar("A","C",20); conectar("C","D",20);
        conectar("D","E",20); conectar("E","F",20); conectar("B","D",30);
        grafo.values().forEach(l->l.sort(Comparator.comparing(Arista::destino)));
        mostrar("DFS", dfs("A","F",new HashSet<>(),new ArrayList<>()));
        mostrar("BFS", bfs("A","F"));
        mostrar("A*", aEstrella("A"));
    }
}
