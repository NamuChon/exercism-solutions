import java.util.List;
import java.util.LinkedList;
class VariableLengthQuantity {

    List<String> encode(List<Long> numbers) {
        List<String> result = new LinkedList<>();
        numbers.stream().forEach(l -> {
            List<String> chunkResult = new LinkedList<>();
            boolean last = true;
            do {
                int chunk = (int) (l & 0x7F);
                l >>>= 7;
                if (last) {
                    last = false;
                } else {
                    chunk |= 0x80;
                }
                chunkResult.add(0, getHex(chunk));
            } while (l != 0);
            result.addAll(chunkResult);
        });
        return result;
    }

    List<String> decode(List<Long> bytes) {
        List<String> result = new LinkedList<>();
        int chunk = 0;
        for (int i = 0; i < bytes.size(); i++) {
            int currentByte = bytes.get(i).intValue();
            chunk = (chunk << 7) | (currentByte & 0x7F);
            if ((currentByte & 0x80) == 0) {
                result.add(getHex(chunk));
                chunk = 0;
            } else if (i == bytes.size() - 1) {
                throw new IllegalArgumentException("Invalid variable-length quantity encoding");
            }
        }
        return result;
    }

    private String getHex(int value) {
        return "0x" + Integer.toHexString(value);
    }
}
