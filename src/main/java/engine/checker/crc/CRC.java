package engine.checker.crc;

import java.util.List;

/**
 * Classe che controlla se è stato verificato una perdita dati su una sequenza di interi.
 *
 * @param <NumeralType> Intero di k cifre
 * @param <Array> Lista di una qualsiasi sequenza di cifre
 */
public abstract class CRC<NumeralType, Array> {

    /**
     * Crea una signature CRC.
     *
     * La sequenza non deve essere vuota o nulla.
     *
     * @param array Sequenza di interi
     * @return Signature CRC
     */
    public abstract NumeralType encode(Array[] array);

    /**
     * Verifica se la sequenza si priva di errori.
     *
     * La sequenza non deve essere vuota o nulla.
     * La signature non deve essere vuota o nulla.
     *
     * @param array Sequenza di interi
     * @param crc Signature crc da controllare
     * @return l'esito della verifica
     */
    public boolean check(Array[] array, NumeralType crc) {
        NumeralType currentCRC = encode(array);
        return currentCRC.equals(crc);
    }

}
