package engine.checker.signature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PNGSignatureChecker implements SignatureChecker<List<Character>> {

    private static final List<Character> SIGNATURE = new ArrayList<Character>();

    static {
        final Character[] CHARACTERS = new Character[] {0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
        for (Character character : CHARACTERS) {
            SIGNATURE.add(character);
        }
    }

    @Override
    public boolean check(List<Character> characters) {
        //Verifica se la dimensione del file è sufficiente per la checking
        boolean isValid = (characters.size() >= SIGNATURE.size());

        Iterator<Character> iterator = characters.iterator();
        Iterator<Character> signatureIterator = SIGNATURE.iterator();

        while (signatureIterator.hasNext() && isValid) {
            Character character = iterator.next();
            Character signatureCharacter = signatureIterator.next();
            isValid = character.equals(signatureCharacter);
        }

        return isValid;
    }
}
