package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javax.servlet.http.Part;

public class ConversorFoto {


    public static byte[] obterBytes(Part parteFoto) throws IOException {
        if (parteFoto == null) return null;
        try (InputStream inputStream = parteFoto.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }


    public static String gerarUrlBase64(byte[] foto, String extensao) {
        if (foto == null || extensao == null) return null;
        return "data:" + extensao + ";base64," + Base64.getEncoder().encodeToString(foto);
    }

 
    public static String obterExtensao(Part parteFoto) {
        if (parteFoto != null && parteFoto.getContentType() != null) {
            return parteFoto.getContentType();
        }
        return "image/*";
    }
}
