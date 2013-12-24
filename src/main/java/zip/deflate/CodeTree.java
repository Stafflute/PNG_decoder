package zip.deflate;

import java.util.ArrayList;
import java.util.List;


/**
 * Un albero binario dove ogni foglia rappresenta il codice di un simbolo, per rappresentare i codici di Huffman. Non modificabile.
 */
/*
 * Ci sono due principali usi per un CodeTree
 * - Leggi il campo 'root' e percorri l'albero per estrarre l'informazione desiderata.
 * - Chiama getCode() per avere il codice di un simbolo, supposto che il simbolo abbia un codice associato.
 * 
 * Il percorso per raggiungere una foglia determina il codice del simbolo associato alla foglia.
 * Partendo dalla radice, andando al figlio di sinistra si rappresenta uno 0, mentre andando a destra si rapppresenta un 1.
 * Vincoli:
 * - L'albero deve essere completo, cioè ogni foglia deve essere associata ad un simbolo.
 * - Nessun simbolo è ripetuto in due o più foglie.
 * - Ma non tutti i simboli devono necessariamente essere nell'albero.
 * - La radice non può essere una foglia.
 * Esempio:
 *   Codici di Huffman:
 *     0: Simbolo A
 *     10: Simbolo B
 *     110: Simbolo C
 *     111: Simbolo D
 *   Albero dei Codici:
 *       .
 *      / \
 *     A   .
 *        / \
 *       B   .
 *          / \
 *         C   D
 */
final class CodeTree {
	
	public final InternalNode root;  // Not null
	
    // Memorizza il codice per ogni simbolo. È null se il simbolo non è stato codificato.
    // Per esempio, se il simbolo è ha codice 10011, allora codes.get(5) è la lista [1, 0, 0, 1, 1].
	private List<List<Integer>> codes;
	
	
	
    // ogni simbolo nella radice dell'albero deve essere strettamente minore di 'symbolLimit'.
	public CodeTree(InternalNode root, int symbolLimit) {
		if (root == null)
			throw new NullPointerException("Argument is null");
		this.root = root;
		
		codes = new ArrayList<List<Integer>>();  // Inizialmente tutto null
		for (int i = 0; i < symbolLimit; i++)
			codes.add(null);
		buildCodeList(root, new ArrayList<Integer>());  // Riempie 'codes' con i dati appropriati
	}
	
	
	private void buildCodeList(Node node, List<Integer> prefix) {
		if (node instanceof InternalNode) {
			InternalNode internalNode = (InternalNode)node;
			
			prefix.add(0);
			buildCodeList(internalNode.leftChild , prefix);
			prefix.remove(prefix.size() - 1);
			
			prefix.add(1);
			buildCodeList(internalNode.rightChild, prefix);
			prefix.remove(prefix.size() - 1);
			
		} else if (node instanceof Leaf) {
			Leaf leaf = (Leaf)node;
			if (leaf.symbol >= codes.size())
				throw new IllegalArgumentException("Symbol exceeds symbol limit");
			if (codes.get(leaf.symbol) != null)
				throw new IllegalArgumentException("Symbol has more than one code");
			codes.set(leaf.symbol, new ArrayList<Integer>(prefix));
			
		} else {
			throw new AssertionError("Illegal node type");
		}
	}
	
	
	
	public List<Integer> getCode(int symbol) {
		if (symbol < 0)
			throw new IllegalArgumentException("Illegal symbol");
		else if (codes.get(symbol) == null)
			throw new IllegalArgumentException("No code for given symbol");
		else
			return codes.get(symbol);
	}
	
	
    // Ritorna una stringa contenente tutti i codice dell'albero.
    // Il formato è soggetto a cambiamenti. Utile per il debug.
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString("", root, sb);
		return sb.toString();
	}
	
	
	private static void toString(String prefix, Node node, StringBuilder sb) {
		if (node instanceof InternalNode) {
			InternalNode internalNode = (InternalNode)node;
			toString(prefix + "0", internalNode.leftChild , sb);
			toString(prefix + "1", internalNode.rightChild, sb);
		} else if (node instanceof Leaf) {
			sb.append(String.format("Code %s: Symbol %d%n", prefix, ((Leaf)node).symbol));
		} else {
			throw new AssertionError("Illegal node type");
		}
	}
	
}



/**
 * Un nodo in un code tree. Questa classe ha esattamente due sottoclassi: InternalNode, Leaf
 */
abstract class Node {
	
	public Node() {}
}


/**
 * Un nodo interno in un code tree. Ha due nodi come figli. Non modificabile.
 */
final class InternalNode extends Node {
	
	public final Node leftChild;  // Not null	
	public final Node rightChild;  // Not null
	
	public InternalNode(Node leftChild, Node rightChild) {
		if (leftChild == null || rightChild == null)
			throw new NullPointerException("Argument is null");
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
}


/**
 * Una foglia un un code tree. Ha un simbolo come valore. Immutabile.
 */
final class Leaf extends Node {
	
	public final int symbol;
	
	public Leaf(int symbol) {
		if (symbol < 0)
			throw new IllegalArgumentException("Illegal symbol value");
		this.symbol = symbol;
	}
}
