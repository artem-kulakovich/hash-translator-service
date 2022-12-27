package by.bntu.fitr.hashtranslatorservice.client;

import org.springframework.http.ResponseEntity;

public interface MD5HashDecoderClient {

    ResponseEntity<String> decodeMD5Hash(final String md5Hash);
}
