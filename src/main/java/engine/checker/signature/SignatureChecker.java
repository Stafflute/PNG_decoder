package engine.checker.signature;

import java.util.List;

/**
 * Classe che controlla se la firma risulta corretta.
 *
 * @param <Sequence> Sequenza di numeri
 */
public interface SignatureChecker<Sequence extends List<?>> {
    /**
     * Verifica se il file abbia una firma corretta.
     *
     * @param sequence Sequenza di numeri
     * @return l'esito del controllo di tale sequenza
     */
    public boolean check(Sequence sequence);
}
