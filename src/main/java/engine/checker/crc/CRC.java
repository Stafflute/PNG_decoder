package engine.checker.crc;

import java.util.List;

/**
 * Classe che controlla se è stato verificato una perdita dati su una sequenza di interi.
 *
 * @param <NumeralType> Intero di k cifre
 * @param <Sequence> Lista di una qualsiasi sequenza di cifre
 */
public abstract class CRC<NumeralType, Sequence extends List<?>> {

    /**
     * Crea una signature CRC.
     *
     * La sequenza non deve essere vuota o nulla.
     *
     * @param sequence Sequenza di interi
     * @return Signature CRC
     */
    public abstract NumeralType encode(Sequence sequence);

    /**
     * Verifica se la sequenza si priva di errori.
     *
     * La sequenza non deve essere vuota o nulla.
     * La signature non deve essere vuota o nulla.
     *
     * @param sequence Sequenza di interi
     * @param crc Signature crc da controllare
     * @return l'esito della verifica
     */
    public boolean check(Sequence sequence, NumeralType crc) {
        NumeralType currentCRC = encode(sequence);
        return currentCRC.equals(crc);
    }

}
