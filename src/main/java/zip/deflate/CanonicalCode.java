package zip.deflate;

import java.util.ArrayList;
import java.util.List;


/**
 * Un codice canonico di Huffman. Immutabile. Codice di lunghezza 0 vuol dire nessun codice.
 */
/*
 * A canonical Huffman code only describes the code length of each symbol. The codes can be reconstructed from this information.
 * Un codice canonico di Huffman descrive solamente la lunghezza del codice di ciascun simbolo.
 * I codice possono essere ricostruiti da questa informazione.
 * In questa implementazione, i simboli con una lunghezza di codice minore, vengono assegnati codici più piccoli lessicograficamente.
 * Esempio:
 *   Lunghezza dei codici (codice canonico):
 *     Simbolo A: 1
 *     Simbolo B: 3
 *     Simbolo C: 0 (no code)
 *     Simbolo D: 2
 *     Simbolo E: 3
 *   Codici di Huffman (generati dal codice canonico):
 *     Simbolo A: 0
 *     Simbolo B: 110
 *     Simbolo C: None
 *     Simbolo D: 10
 *     Simbolo E: 111
 */
final class CanonicalCode {
	
	private int[] codeLengths;
	
	
	
    // Il costruttore non verifica che l'array delle lunghezze dei codici in un albero completo di Huffman sia sottoriempito o sovrariempito.
	public CanonicalCode(int[] codeLengths) {
		if (codeLengths == null)
			throw new NullPointerException("Argument is null");
		this.codeLengths = codeLengths.clone();
		for (int x : codeLengths) {
			if (x < 0)
				throw new IllegalArgumentException("Illegal code length");
		}
	}
	
	
    // Costruisce un codice canonico dall'albero di codice in input
	public CanonicalCode(CodeTree tree, int symbolLimit) {
		codeLengths = new int[symbolLimit];
		buildCodeLengths(tree.root, 0);
	}
	
	
	private void buildCodeLengths(Node node, int depth) {
		if (node instanceof InternalNode) {
			InternalNode internalNode = (InternalNode)node;
			buildCodeLengths(internalNode.leftChild , depth + 1);
			buildCodeLengths(internalNode.rightChild, depth + 1);
		} else if (node instanceof Leaf) {
			int symbol = ((Leaf)node).symbol;
			if (codeLengths[symbol] != 0)
				throw new AssertionError("Symbol has more than one code"); // Perché il CodeTree ha un controllo di vincolo che non permette di avere un simbolo in diverse foglie
			if (symbol >= codeLengths.length)
				throw new IllegalArgumentException("Symbol exceeds symbol limit");
			codeLengths[symbol] = depth;
		} else {
			throw new AssertionError("Illegal node type");
		}
	}
	
	
	
	public int getSymbolLimit() {
		return codeLengths.length;
	}
	
	
	public int getCodeLength(int symbol) {
		if (symbol < 0 || symbol >= codeLengths.length)
			throw new IllegalArgumentException("Symbol out of range");
		return codeLengths[symbol];
	}
	
	
	public CodeTree toCodeTree() {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = max(codeLengths); i >= 1; i--) {  // Decresce a partire da lunghezze di codice positive
			List<Node> newNodes = new ArrayList<Node>();
			
            // Aggiunge nuove foglie per i simboli con lunghezza di codice i
			for (int j = 0; j < codeLengths.length; j++) {
				if (codeLengths[j] == i)
					newNodes.add(new Leaf(j));
			}
			
            // Unisce nodi del livello più basso precedente
			for (int j = 0; j < nodes.size(); j += 2)
				newNodes.add(new InternalNode(nodes.get(j), nodes.get(j + 1)));
			
			nodes = newNodes;
			if (nodes.size() % 2 != 0)
				throw new IllegalStateException("This canonical code does not represent a Huffman code tree");
		}
		
		if (nodes.size() != 2)
			throw new IllegalStateException("This canonical code does not represent a Huffman code tree");
		return new CodeTree(new InternalNode(nodes.get(0), nodes.get(1)), codeLengths.length);
	}
	
	
	private static int max(int[] array) {
		int result = array[0];
		for (int x : array)
			result = Math.max(x, result);
		return result;
	}
	
}
